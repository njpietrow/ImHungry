package edu.usc.cs.group8.ImHungry;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IHSearch")
public class IHSearch extends HttpServlet {

	/**
	 * search_query for text
	 * num_results for number of results
	 */
	private static final long serialVersionUID = 1L;
	
	public IHSearch() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		String keyword = request.getParameter("search_query");
		String number = request.getParameter("num_results");
		// No session required for Search
		
		ArrayList<String> images = doImageSearch(keyword);
		ArrayList<Recipe> recipes = doRecipeSearch(keyword,number);
		ArrayList<Restaurant> restaurants = doRestaurantSearch(keyword,number);
		
		if (images != null && recipes != null && restaurants != null) {
			sortRecipes(recipes);
			sortRestaurants(restaurants);
			request.getSession().setAttribute("images", images);
			request.getSession().setAttribute("recipes", recipes);
			request.getSession().setAttribute("restaurants", restaurants);
			request.getRequestDispatcher("results_page.jsp").forward(request, response);
		}
		
		response.setStatus(response.SC_BAD_GATEWAY);
		response.getWriter().println("Unknown error occurred.");
		response.getWriter().flush();
	}
	
	public void sortRestaurants(ArrayList<Restaurant> restaurants) {
		restaurants.sort(new RestaurantComparator());
		
	}

	public void sortRecipes(ArrayList<Recipe> recipes) {
		recipes.sort(new RecipeComparator());
	}

	public ArrayList<Restaurant> doRestaurantSearch(String keyword, String number) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Recipe> doRecipeSearch(String keyword, String number) {
		String results = readWebsite("https://www.google.com/search?q=" + keyword + "%20recipe&num=100");
		if (results == null) return null;
		else {
			
			// "class="r"><a href=
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			int i = 0;
			while (recipes.size() < Integer.parseInt(number) && i < results.length() - 18 ) {
				for (int j = i; j < results.length() - 18; i++, j++) {
					if (results.substring(j, j+18).equals("class=\"r\"><a href=")) {
						j += 19;
						i = j;
						while (results.charAt(i) != '"' && i < results.length()) i++;
						Recipe recipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe(results.substring(j,i)));
						if (recipe == null || ListManager.getInstance().doNotShowContains(recipe)) {
							continue;
						}
						else {
							recipes.add(recipe);
							break;
						}
					}
				}
			}
			return recipes;
		}
	}

	public ArrayList<String> doImageSearch(String keyword) {
		String results = readWebsite("https://www.google.com/search?q=" + keyword + "&tbm=isch&gws_rd=ssl");
		if (results == null) return null;
		else {
			ArrayList<String> images = new ArrayList<String>();
			int i = 0;
			while (images.size() < 10 && i < results.length() - 5) {
				for (int j = i; j < results.length() - 5; i++, j++) {
					if (results.substring(j, j+5).equals("\"ou\":")) {
						j += 6;
						i = j;
						while (results.charAt(i) != '"' && i < results.length()) i++;
						images.add(results.substring(j,i));
						break;
					}
				}
			}
			return images;
		}
	}

	public String readWebsite(String url) {
		/*
		 * Copied from StackOverflow and from https://community.oracle.com/thread/1691281
		 */
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  connection.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		  scanner.close();
		}catch ( Exception ex ) {
		    return null;
		}
		return content;
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }

}

class RestaurantComparator implements Comparator<Restaurant>{
	public int compare(edu.usc.cs.group8.ImHungry.Restaurant r1, edu.usc.cs.group8.ImHungry.Restaurant r2) {
		if (ListManager.getInstance().favoritesContains(r1) && !ListManager.getInstance().favoritesContains(r2)) {
			return Integer.MAX_VALUE;
		} else if (ListManager.getInstance().favoritesContains(r2) && !ListManager.getInstance().favoritesContains(r1)) {
			return Integer.MIN_VALUE;
		}
		else return r1.getDriveTime() - r2.getDriveTime();
	}
}

class RecipeComparator implements Comparator<Recipe>{
	public int compare(edu.usc.cs.group8.ImHungry.Recipe r1, edu.usc.cs.group8.ImHungry.Recipe r2) {
		if (ListManager.getInstance().favoritesContains(r1) && !ListManager.getInstance().favoritesContains(r2)) {
			return Integer.MAX_VALUE;
		} else if (ListManager.getInstance().favoritesContains(r2) && !ListManager.getInstance().favoritesContains(r1)) {
			return Integer.MIN_VALUE;
		}
		if (r1.getPrepTime() == 0) {
			return Integer.MAX_VALUE;
		}
		if (r2.getPrepTime() == 0) {
			return Integer.MIN_VALUE;
		}
		else return r1.getPrepTime() - r2.getPrepTime();
	}
}


