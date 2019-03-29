package edu.usc.cs.group8.ImHungry;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Servlet: IHSearch
 * This servlet performs the recipe, restaurant, and image searches to set up the results page and the session lookup.
 * Authors: Kevin Calaway & Nick Pietrow
 * USC ID: 9724507315 & 5425773820
 * Email: calaway@usc.edu, pietrow@usc.edu
 */
@WebServlet("/IHSearch")
public class IHSearch extends HttpServlet {

	/**
	 * search_query for text
	 * num_results for number of results
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String MAPS_API_KEY = "AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA";
	private static final String TOMMY_TROJAN_LOC = "34.020593,-118.285447";
	
	public IHSearch() {
        super();
    }
	
	/*
	 * This function takes the search query and requested number of results, and calls the three
	 * search functions to retrieve the results. The results are stored in session data.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		String keyword = request.getParameter("search_query");
		String number = request.getParameter("num_results");
		User currUser = new User();
		try {
			currUser = (User)request.getSession().getAttribute("user");
		}
		catch (Exception e){
			currUser = null;
		}
//		String radius = request.getParameter("radius");
//		TODO need to create radius parameter from input box
		
		// No session required for Search
		ArrayList<String> images = doImageSearch(keyword);
		ArrayList<Recipe> recipes = doRecipeSearch(keyword,number,currUser);
		ArrayList<Restaurant> restaurants = doRestaurantSearch(keyword,number, currUser);
//		ArrayList<Restaurant> restaurants = doRestaurantSearch(keyword,number,radius);
		
		if (images != null && recipes != null && restaurants != null) {
			addToQuickAccess(currUser,keyword,number);
			sortRecipes(recipes,currUser);
			sortRestaurants(restaurants,currUser);
			request.getSession().setAttribute("images", images);
			request.getSession().setAttribute("recipes", recipes);
			request.getSession().setAttribute("restaurants", restaurants);
			request.getSession().setAttribute("query", keyword);
			request.getRequestDispatcher("results_page.jsp").forward(request, response);
		}
		
		response.setStatus(response.SC_BAD_GATEWAY);
		response.getWriter().println("Unknown error occurred.");
		response.getWriter().flush();
	}
	
	private void addToQuickAccess(User currUser, String keyword, String number) {
		if (currUser == null) return;
		Connection conn = null;
		PreparedStatement st = null;
		currUser.getLists().addToQuickAccess(new Query(keyword,number));
		 try {
		        conn =
		           DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
		                                       "user=root&password=root&useSSL=false");
		        st = conn.prepareStatement("INSERT INTO QuickAccess(username,keyword,num_results) values (?,?,?)");
		        st.setString(1,  currUser.getName());
		        st.setString(2,  keyword);
		        st.setString(3, number);
		        st.execute();
		        // Do something with the Connection
		
		    } catch (SQLException ex) {
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
	}

	/*
	 * Sorts restaurants according to the comparator RestaurantComparator below
	 */
	public void sortRestaurants(ArrayList<Restaurant> restaurants, User currUser) {
		restaurants.sort((r1,r2) -> {
			if (currUser.getLists().favoritesContains(r1) && !ListManager.getInstance().favoritesContains(r2)) {
			return Integer.MIN_VALUE;
		} else if (currUser.getLists().favoritesContains(r2) && !ListManager.getInstance().favoritesContains(r1)) {
			return Integer.MAX_VALUE;
		}
		else return r1.getDriveTime() - r2.getDriveTime();});
		
	}

	/*
	 * Sorts recipes according to the comparator RecipeComparator below
	 */
	public void sortRecipes(ArrayList<Recipe> recipes, User currUser) {
		recipes.sort((r1,r2) -> {
			if (currUser.getLists().favoritesContains(r1) && !ListManager.getInstance().favoritesContains(r2)) {
				return Integer.MIN_VALUE;
		} else if (currUser.getLists().favoritesContains(r2) && !ListManager.getInstance().favoritesContains(r1)) {
			return Integer.MAX_VALUE;
		}
		if (r1.getPrepTime() == 0) {
			return Integer.MAX_VALUE;
		}
		if (r2.getPrepTime() == 0) {
			return Integer.MIN_VALUE;
		}
		else return r1.getPrepTime() - r2.getPrepTime();
		});
	}

	/*
	 * Restaurant search makes a nearby search request for restaurants related to the keyword
	 * It then makes 2 other separate request to return Contact information for the restaurant and driving time
	 */
	public ArrayList<Restaurant> doRestaurantSearch(String keyword, String number, User currUser) {
//	public ArrayList<Restaurant> doRestaurantSearch(String keyword, String number, String radius) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		addToQuickAccess(currUser,keyword,number);
		//add "+" to keyword string
		keyword = keyword.replaceAll(" ", "+").toLowerCase();
		
		//set url for Google Nearby Search API request
		// TODO
		//need to pass in radius parameter taken from the form
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
				+ "location=" + TOMMY_TROJAN_LOC
				+ "&type=restaurant"
				+ "&radius=5000"				
//				+ "&radius=" + radius
				+ "&keyword=" + keyword
				+ "&key=" + MAPS_API_KEY
				+ "\n";
		
			String json_string = readWebsite(url);
			if (json_string == null) return null;

			//Parse the JSON file to retrieve relevant Restaurants
			JSONObject mainObj= new JSONObject(json_string);
			JSONArray jsonArray = (JSONArray) mainObj.get("results");

			//Gets the number of results to returns and adds 3 for padding if some are in do not show list, stored in session
			int check = Integer.parseInt(number) + 3;
			//checks to see if the query returned less than the number of results to return.
			if (jsonArray.length()<check)
				check = jsonArray.length(); 
			
			for (int i = 0; restaurants.size() < Integer.parseInt(number) && i < check; i++) {
				JSONObject iterate_obj = (JSONObject) jsonArray.get(i);
				String id = (String) iterate_obj.get("place_id");
				String name = (String) iterate_obj.get("name");
				Restaurant temp = new Restaurant(name,id);
				if (temp != null && !currUser.getLists().doNotShowContains(temp) && !restaurants.contains(temp)) {
					restaurants.add(temp);
				}
			}

			//Populate the array of Restaurant objects with the rest of the required info
			for (int i =0; i < restaurants.size();i++) {
				Restaurant curr_restaurant = restaurants.get(i);
				curr_restaurant = RestaurantGetter.getContactInfo(curr_restaurant); 
				curr_restaurant = RestaurantGetter.getDriveTime(curr_restaurant);   
				restaurants.set(i, curr_restaurant);                                
			}
		return restaurants;
	}

	/*
	 * This sends a request out to Google for one hundred recipes, then parses in as many
	 * as requested, ignoring duplicates and those on the Do Not Show predefined list. It
	 * calls the RecipeGetter functions to get recipes from each of the web results.
	 * See: RecipeGetter.java
	 */
	public ArrayList<Recipe> doRecipeSearch(String keyword, String number, User currUser) {
		keyword = keyword.replaceAll(" ", "+").toLowerCase();
		String results = readWebsite("https://www.google.com/search?q=" + keyword + "%20recipe&num=100");
		if (results == null) return null;
		else {
			
			// "class="r"><a href= is the key that means a new result is starting.
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			int i = 0;
			while (recipes.size() < Integer.parseInt(number) && i < results.length() - 18 ) {
				for (int j = i; j < results.length() - 18; i++, j++) {
					if (results.substring(j, j+18).equals("class=\"r\"><a href=")) {
						j += 19;
						i = j;
						while (results.charAt(i) != '"' && i < results.length()) i++;
						Recipe recipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe(results.substring(j,i)));
						if (recipe == null || currUser.getLists().doNotShowContains(recipe) || recipes.contains(recipe)) {
							continue;
						}
						else {
							recipe.setURL(results.substring(j,i));
							recipes.add(recipe);
							break;
						}
					}
				}
			}
			return recipes;
		}
	}

	/*
	 * This sends a request out to Google to find images related to the keyword. It
	 * grabs the first ten and returns them in an ArrayList as raw URLs in Strings.
	 */
	public ArrayList<String> doImageSearch(String keyword) {
		keyword = keyword.replaceAll(" ", "+").toLowerCase();
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

	/*
	 * This code was borrowed from https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
     * to parse an HTML file in Java and contains also an added line setting the User-Agent to simulate
     * Chrome performing a search, because without it Google returns garbage results or Forbidden errors.
	 */
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
			ex.printStackTrace();
		    return null;
		}
		return content;
	}

	private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }

}

/*
 * If a restaurant is in Favorites, it appears first;
 * Otherwise, the list is sorted by drive time, lowest first.
 */
class RestaurantComparator implements Comparator<Restaurant>{
	public int compare(edu.usc.cs.group8.ImHungry.Restaurant r1, edu.usc.cs.group8.ImHungry.Restaurant r2) {
		if (ListManager.getInstance().favoritesContains(r1) && !ListManager.getInstance().favoritesContains(r2)) {
			return Integer.MIN_VALUE;
		} else if (ListManager.getInstance().favoritesContains(r2) && !ListManager.getInstance().favoritesContains(r1)) {
			return Integer.MAX_VALUE;
		}
		else return r1.getDriveTime() - r2.getDriveTime();
	}
}

/*
 * If a recipe is in Favorites, it appears first;
 * Otherwise, the list is sorted by prep time, lowest first.
 * 0 was used as a flag to mean that the recipe had no listed prep time,
 * and Group 8 decided that no prep time should be sorted behind rather
 * than ahead of recipes with listed prep times.
 */
class RecipeComparator implements Comparator<Recipe>{
	public int compare(edu.usc.cs.group8.ImHungry.Recipe r1, edu.usc.cs.group8.ImHungry.Recipe r2) {
		if (ListManager.getInstance().favoritesContains(r1) && !ListManager.getInstance().favoritesContains(r2)) {
			return Integer.MIN_VALUE;
		} else if (ListManager.getInstance().favoritesContains(r2) && !ListManager.getInstance().favoritesContains(r1)) {
			return Integer.MAX_VALUE;
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


