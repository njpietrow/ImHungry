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

@WebServlet("/IHGroceryList")
public class IHGroceryList extends HttpServlet {

	/**
	 * search_query for text
	 * num_results for number of results
	 */
	private static final long serialVersionUID = 1L;
	
	public IHGroceryList() {
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
		Recipe r; 
		ArrayList<String>ingredients; 
		User currUser = new User();
		try {
			currUser = (User)request.getSession().getAttribute("user");
		}
		catch (Exception e){
			currUser = null;
		}
		if (currUser == null) {
			response.setStatus(response.SC_BAD_GATEWAY);
			response.getWriter().println("Please log in to use the grocery list feature!");
			response.getWriter().flush();
			return;
		}
		
		String action = request.getParameter("action");
			//GOT TO PASS RECIPE NOT INGREDIENTS 
		String recipe_url = request.getParameter("recipe_url");
		if (recipe_url != null)
		{
			r = currUser.get(recipe_url);
			ingredients = r.getIngredients();
			if (action.equals("ADD")) {
				for (int i = 0; i < ingredients.size(); i++)
				{
					String ingred = ingredients.get(i);
					addToList(currUser, ingred, r.getURL());
				}
				request.getRequestDispatcher("recipe_page.jsp?recipe_id=" + recipe_url).forward(request, response);
			}
		}
		
		
		//based on request parameter, will add the recipe or restaurant to the list
		
		
		if (action.equals("REMOVE")) {
			String ingred = request.getParameter("ingredient");
			int ing = Integer.parseInt(ingred);
			String ingredient = currUser.getGroceries().get(ing);
			removeFromList(currUser, ingredient);
			System.out.println("deleted");
			request.getRequestDispatcher("grocery_list.jsp").forward(request, response);
		}

	}
	
	/*
	 * Based on the provided source and destination lists, this function moves the specified item.
	 */
	
	/*
	 * Based on the specified list and item number, it removes it from the list.
	 */
	public void removeFromList(User currUser, String ingred) {
		currUser.removeFromGroceryList(ingred);
		return; 
		
	}

	/*
	 * Based on the specified list and item number (gathered from the "recipeID" or "restaurantID" parameters)
	 * it adds an item to a list.
	 */
	public void addToList(User currUser, String ingred, String recipe_url) {
		currUser.addToGroceryList(ingred, recipe_url);
		
		return;
	}
}	
