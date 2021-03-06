package edu.usc.cs.group8.ImHungry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

	private static String MAPS_API_KEY = "AIzaSyCGsxCmVveyEYtob-Uy5HbH0HX9ukxuT_M";
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
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String keyword = request.getParameter("search_query");
		String number = request.getParameter("num_results");
		String radius = request.getParameter("num_miles");
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
		ArrayList<Recipe> recipes = doRecipeSearch(keyword,number,currUser);
		ArrayList<Restaurant> restaurants = doRestaurantSearch(keyword,number,radius,currUser);
//		ArrayList<Restaurant> restaurants = doRestaurantSearch(keyword,number,radius);

		if (recipes != null && restaurants != null) {
			addToQuickAccess(currUser,keyword,number,radius);
			if (currUser == null) currUser = new User();
			sortRecipes(recipes,currUser);
			sortRestaurants(restaurants,currUser);
			if (currUser.getLastSearch() != null) {
				request.getSession().setAttribute("images", currUser.getLastSearch().getImages());
			} else {
				request.getSession().setAttribute("images", new Query(keyword,number,radius).getImages());
			}
			request.getSession().setAttribute("recipes", recipes);
			request.getSession().setAttribute("restaurants", restaurants);
			request.getSession().setAttribute("query", keyword);
			request.getSession().setAttribute("username", currUser.getName());
			request.getRequestDispatcher("results_page.jsp").forward(request, response);
		}

		response.setStatus(response.SC_BAD_GATEWAY);
		response.getWriter().println("Unknown error occurred.");
		response.getWriter().flush();
	}

	private void addToQuickAccess(User currUser, String keyword, String number, String radius) {
		if (currUser == null) return;
		if (currUser.getName() == null) return;
		if (currUser.getName().equals("")) return;
		currUser.getLists().addToQuickAccess(new Query(keyword,number,radius));
		
	}

	/*
	 * Sorts restaurants according to the comparator RestaurantComparator below
	 */
	public void sortRestaurants(ArrayList<Restaurant> restaurants, User currUser) {

		restaurants.sort((r1,r2) -> {
			if (currUser.getLists().favoritesContains(r1) && !currUser.getLists().favoritesContains(r2)) {
			return Integer.MIN_VALUE;
		} else if (currUser.getLists().favoritesContains(r2) && !currUser.getLists().favoritesContains(r1)) {
			return Integer.MAX_VALUE;
		}
		else return r1.getDriveTime() - r2.getDriveTime();});

	}

	/*
	 * Sorts recipes according to the comparator RecipeComparator below
	 */
	public void sortRecipes(ArrayList<Recipe> recipes, User currUser) {
		recipes.sort((r1,r2) -> {

			if (currUser.getLists().favoritesContains(r1) && !currUser.getLists().favoritesContains(r2)) {
				return Integer.MIN_VALUE;
		} else if (currUser.getLists().favoritesContains(r2) && !currUser.getLists().favoritesContains(r1)) {
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
	public ArrayList<Restaurant> doRestaurantSearch(String keyword, String number, String radius, User currUser) {
		if (currUser == null) currUser = new User();
//		public ArrayList<Restaurant> doRestaurantSearch(String keyword, String number, String radius) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		//add "+" to keyword string
		keyword = keyword.replaceAll(" ", "+").toLowerCase();
		int rmeters = Integer.parseInt(radius) * 1609;
		//set url for Google Nearby Search API request
		// TODO
		//need to pass in radius parameter taken from the form
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
				+ "location=" + TOMMY_TROJAN_LOC
				+ "&type=restaurant"
//				+ "&radius=5000"
				+ "&radius=" + rmeters
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
				if (currUser == null) currUser = new User();
				if (temp != null && !currUser.getLists().doNotShowContains(temp) && !restaurants.contains(temp)) {
					restaurants.add(temp);
				}
			}

			//Populate the array of Restaurant objects with the rest of the required info
			for (int i =0; i < restaurants.size();i++) {
				Restaurant curr_restaurant = restaurants.get(i);
				curr_restaurant = currUser.getRestaurant(curr_restaurant.getId());
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
		if (currUser == null) currUser = new User();
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
						Recipe recipe = (Recipe)currUser.getRecipe(results.substring(j,i));
						if (recipe == null) recipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe(results.substring(j,i)));

						if (currUser == null) currUser = new User();
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