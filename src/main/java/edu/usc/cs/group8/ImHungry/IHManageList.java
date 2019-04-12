package edu.usc.cs.group8.ImHungry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet: IHManageList
 * This servlet takes care of any of the list management processes the front end needs to take care of.
 * Author: Kevin Calaway
 * USC ID: 9724507315
 * Email: calaway@usc.edu
 */
@WebServlet("/IHManageList")
public class IHManageList extends HttpServlet {

	/**
	 * search_query for text
	 * num_results for number of results
	 */
	private static final long serialVersionUID = 1L;
	
	public IHManageList() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/*
	 * This function determines what function is being called, gathers the necessary parameters, and passes them along.
	 * The display functionality determines what object is being presented and therefore needs access to the response
	 * object.
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Recipe> recipes;
		ArrayList<Restaurant> restaurants;
		User currUser = new User();
		try {
			currUser = (User)request.getSession().getAttribute("user");
		}
		catch (Exception e){
			currUser = null;
		}
		if (currUser == null) {
			response.setStatus(response.SC_BAD_GATEWAY);
			response.getWriter().println("Please log in to use the list management feature!");
			response.getWriter().flush();
			return;
		}
		try{
			recipes = (ArrayList<Recipe>)(request.getSession().getAttribute("recipes"));
			restaurants = (ArrayList<Restaurant>)(request.getSession().getAttribute("restaurants"));
		} catch (Exception e){
			response.setStatus(response.SC_BAD_GATEWAY);
			response.getWriter().println("Unknown error occurred.");
			response.getWriter().flush();
			return;
		}
		String listID = request.getParameter("list_id");
		String action = request.getParameter("action");
		
		//based on request parameter, will add the recipe or restaurant to the list
		if (action.equals("ADD")) {
			String restaurantName;
			String recipeID = request.getParameter("recipe_id");
			String restaurantID = request.getParameter("restaurant_id");
			addToList(currUser,listID,recipeID,restaurantID,recipes,restaurants);
			if (recipeID != "") {
				request.getRequestDispatcher("recipe_page.jsp?recipe_id=" + recipeID).forward(request, response);
			} else {
				request.getRequestDispatcher("restaurant_page.jsp?restaurant_id=" + restaurantID).forward(request, response);
			}
		}
		
		if (action.equals("REMOVE")) {
			String itemID = request.getParameter("item_id");
			removeFromList(currUser,listID,itemID);
			request.getRequestDispatcher("list_management_page.jsp?list_id=" + listID).forward(request, response);
		}
		
		else if (action.equals("MOVE")) {
			String itemID = request.getParameter("item_id");
			String destinationID = request.getParameter("destination_id");
			moveToList(currUser,listID,destinationID,itemID);
			request.getRequestDispatcher("list_management_page.jsp?list_id=" + listID).forward(request, response);
		}
		
		/*
		 * This chunk of code cannot call out because it relies on the request object;
		 * However, all it does is display an item when clicked on a list.
		 */
		else if (action.equals("DISPLAY")) {
			String itemID = request.getParameter("item_id");
			if (itemID != null && !itemID.equals("")) {
				int index = Integer.parseInt(itemID);
				if (listID.equals("FAVORITES")) {
					if (currUser.getLists().getFavorites().get(index) instanceof Recipe) {
						request.getRequestDispatcher("recipe_page.jsp?list_id=FAVORITES&item_id=" + index).forward(request, response);
					}
					else if (currUser.getLists().getFavorites().get(index) instanceof Restaurant) {
						request.getRequestDispatcher("restaurant_page.jsp?list_id=FAVORITES&item_id=" + index).forward(request, response);
					}
				}
				if (listID.equals("TO_EXPLORE")) {
					if (currUser.getLists().getToExplore().get(index) instanceof Recipe) {
						request.getRequestDispatcher("recipe_page.jsp?list_id=TO_EXPLORE&item_id=" + index).forward(request, response);
					}
					else if (currUser.getLists().getToExplore().get(index) instanceof Restaurant) {
						request.getRequestDispatcher("restaurant_page.jsp?list_id=TO_EXPLORE&item_id=" + index).forward(request, response);
					}
				}
				if (listID.equals("DO_NOT_SHOW")) {
					if (currUser.getLists().getDoNotShow().get(index) instanceof Recipe) {
						request.getRequestDispatcher("recipe_page.jsp?list_id=DO_NOT_SHOW&item_id=" + index).forward(request, response);
					}
					else if (currUser.getLists().getDoNotShow().get(index) instanceof Restaurant) {
						request.getRequestDispatcher("restaurant_page.jsp?list_id=DO_NOT_SHOW&item_id=" + index).forward(request, response);
					}
				}
			}
		}
	}
	
	
	
	
	
	
	/*
	 * Based on the provided source and destination lists, this function moves the specified item.
	 */
	public void moveToList(User currUser, String listID, String destinationID, String itemID) {
		if (itemID != null && !itemID.equals("")) {
			int index = Integer.parseInt(itemID);
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			if (listID.equals("FAVORITES") && !destinationID.equals("FAVORITES")) {
				Result r = currUser.getLists().getFavorites().get(index);
				currUser.getLists().removeFromFavorites(r);
				if (destinationID.equals("TO_EXPLORE")) {
					currUser.getLists().addToToExplore(r);
				}
				if (destinationID.equals("DO_NOT_SHOW")) {
					currUser.getLists().addToDoNotShow(r);
				}
			}
			if (listID.equals("TO_EXPLORE") && !destinationID.equals("TO_EXPLORE")) {
				Result r = currUser.getLists().getToExplore().get(index);
				currUser.getLists().removeFromToExplore(r);
				if (destinationID.equals("FAVORITES")) {
					currUser.getLists().addToFavorites(r);
				}
				if (destinationID.equals("DO_NOT_SHOW")) {
					currUser.getLists().addToDoNotShow(r);
				}
			}
			if (listID.equals("DO_NOT_SHOW") && !destinationID.equals("DO_NOT_SHOW")) {
				Result r = currUser.getLists().getDoNotShow().get(index);
				currUser.getLists().removeFromDoNotShow(r);
				if (destinationID.equals("FAVORITES")) {
					currUser.getLists().addToFavorites(r);
				}
				if (destinationID.equals("TO_EXPLORE")) {
					currUser.getLists().addToToExplore(r);
				}
			}
		}
		
	}

	/*
	 * Based on the specified list and item number, it removes it from the list.
	 */
	public void removeFromList(User currUser, String listID, String itemID) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		if (itemID != null && !itemID.equals("")) {
			int index = Integer.parseInt(itemID);
			if (listID.equals("FAVORITES")) {
				currUser.getLists().removeFromFavorites(index);
			}
			if (listID.equals("TO_EXPLORE")) {
				currUser.getLists().removeFromToExplore(index);
			}
			if (listID.equals("DO_NOT_SHOW")) {
				currUser.getLists().removeFromDoNotShow(index);
			}
		}
		
	}

	/*
	 * Based on the specified list and item number (gathered from the "recipeID" or "restaurantID" parameters)
	 * it adds an item to a list.
	 */
	public void addToList(User currUser, String listID, String recipeID, String restaurantID, ArrayList<Recipe> recipes, ArrayList<Restaurant> restaurants) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		System.out.println(recipeID);
		if (recipeID != null && !recipeID.equals("")) {
			String token = "";
			recipeID = recipeID.substring(1,recipeID.length()-1);
    		for (int i = 0; i < recipeID.length() - 3; i++){
    			if (recipeID.substring(i,i+3).equals("%20")){
    				token += ' ';
    			}
    			else if (recipeID.substring(i,i+3).equals("%3A")){
    				token += ':';
    			}
    			else if (recipeID.substring(i,i+3).equals("%2F")){
    				token += '/';
    			}
    			else if (recipeID.substring(i,i+3).equals("%2D")){
    				token += '-';
    			}
    			else token += recipeID.charAt(i);
    			
    		}
    		if (recipeID.charAt(recipeID.length()-3)!='%') {
    			for (int i = recipeID.length() - 3; i < recipeID.length(); i++){
    				token += recipeID.charAt(i);
    			}
    		}
			Recipe r = null;
			for (int i = 0; i < recipes.size(); i++) {
				if (recipes.get(i).getURL().equals(recipeID)) {
					r = recipes.get(i);
					break;
				}
			}
			if (r == null) {
				for (int i = 0; i < currUser.getLists().getFavorites().size(); i++) {
					if (currUser.getLists().getFavorites().get(i) instanceof Recipe) {
						if (((Recipe)(currUser.getLists().getFavorites().get(i))).getURL().equals(recipeID)) {
							r = (Recipe) currUser.getLists().getFavorites().get(i);
							break;
						}
					}
				}
			}
			if (r == null) {
				for (int i = 0; i < currUser.getLists().getToExplore().size(); i++) {
					if (currUser.getLists().getToExplore().get(i) instanceof Recipe) {
						if (((Recipe)(currUser.getLists().getToExplore().get(i))).getURL().equals(recipeID)) {
							r = (Recipe) currUser.getLists().getToExplore().get(i);
							break;
						}
					}
				}
			}
			if (r == null) {
				for (int i = 0; i < currUser.getLists().getDoNotShow().size(); i++) {
					if (currUser.getLists().getDoNotShow().get(i) instanceof Recipe) {
						if (((Recipe)(currUser.getLists().getDoNotShow().get(i))).getURL().equals(recipeID)) {
							r = (Recipe) currUser.getLists().getToExplore().get(i);
							break;
						}
					}
				}
			}
			if (r == null) {
				r = (Recipe)currUser.get(recipeID);
			}
			System.out.println(r);
			if (listID.equals("FAVORITES")) {
				currUser.getLists().addToFavorites(r);
			}
			else if (listID.equals("TO_EXPLORE")) {
				currUser.getLists().addToToExplore(r);
			}
			else if (listID.equals("DO_NOT_SHOW")) {
				currUser.getLists().addToDoNotShow(r);
			}
		} else if (restaurantID != null && !restaurantID.equals("")) {
			Restaurant r = null;
			for (int i = 0; i < restaurants.size(); i++) {
				if (restaurants.get(i).getId().equals(restaurantID)) {
					r = restaurants.get(i);
					break;
				}
			}
			if (r == null) {
				for (int i = 0; i < currUser.getLists().getFavorites().size(); i++) {
					if (currUser.getLists().getFavorites().get(i) instanceof Restaurant) {
						if (((Restaurant)(currUser.getLists().getFavorites().get(i))).getId().equals(restaurantID)) {
							r = (Restaurant) currUser.getLists().getFavorites().get(i);
							break;
						}
					}
				}
			}
			if (r == null) {
				for (int i = 0; i < currUser.getLists().getToExplore().size(); i++) {
					if (currUser.getLists().getToExplore().get(i) instanceof Restaurant) {
						if (((Restaurant)(currUser.getLists().getToExplore().get(i))).getId().equals(restaurantID)) {
							r = (Restaurant) currUser.getLists().getFavorites().get(i);
							break;
						}
					}
				}
			}
			if (r == null) {
				for (int i = 0; i < currUser.getLists().getDoNotShow().size(); i++) {
					if (currUser.getLists().getDoNotShow().get(i) instanceof Restaurant) {
						if (((Restaurant)(currUser.getLists().getDoNotShow().get(i))).getId().equals(restaurantID)) {
							r = (Restaurant) currUser.getLists().getFavorites().get(i);
							break;
						}
					}
				}
			}
			if (r == null) {
				r = (Restaurant)currUser.get(restaurantID,"");
			}
			else if (listID.equals("FAVORITES")) {
				currUser.getLists().addToFavorites(r);
			}
			else if (listID.equals("TO_EXPLORE")) {
				currUser.getLists().addToToExplore(r);
			}
			else if (listID.equals("DO_NOT_SHOW")) {
				currUser.getLists().addToDoNotShow(r);
			}
		}
		return;
	}
}	