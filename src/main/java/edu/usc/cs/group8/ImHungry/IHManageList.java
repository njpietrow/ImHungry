package edu.usc.cs.group8.ImHungry;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArrayList<Result> recipes;
		ArrayList<Result> restaurants;
		try{
			recipes = (ArrayList<Result>)(request.getSession().getAttribute("recipes"));
			restaurants = (ArrayList<Result>)(request.getSession().getAttribute("restaurants"));
		} catch (Exception e){
			response.setStatus(response.SC_BAD_GATEWAY);
			response.getWriter().println("Unknown error occurred.");
			response.getWriter().flush();
			return;
		}
		String listID = request.getParameter("list_id");
		String action = request.getParameter("action");
		
		if (action.equals("ADD")) {
			String recipeID = request.getParameter("recipe_id");
			String restaurantID = request.getParameter("restaurant_id");
			if (recipeID != null && !recipeID.equals("")) {
				int index = Integer.parseInt(recipeID);
				if (listID.equals("FAVORITES")) {
					ListManager.getInstance().addToFavorites(recipes.get(index));
				}
				if (listID.equals("TO_EXPLORE")) {
					ListManager.getInstance().addToToExplore(recipes.get(index));
				}
				if (listID.equals("DO_NOT_SHOW")) {
					ListManager.getInstance().addToDoNotShow(recipes.get(index));
				}
			} else if (restaurantID != null && !restaurantID.equals("")) {
				int index = Integer.parseInt(restaurantID);
				if (listID.equals("FAVORITES")) {
					ListManager.getInstance().addToFavorites(restaurants.get(index));
				}
				if (listID.equals("TO_EXPLORE")) {
					ListManager.getInstance().addToToExplore(restaurants.get(index));
				}
				if (listID.equals("DO_NOT_SHOW")) {
					ListManager.getInstance().addToDoNotShow(restaurants.get(index));
				}
			}
			return;
		}
		
		if (action.equals("REMOVE")) {
			String itemID = request.getParameter("item_id");
			if (itemID != null && !itemID.equals("")) {
				int index = Integer.parseInt(itemID);
				if (listID.equals("FAVORITES")) {
					ListManager.getInstance().removeFromFavorites(index);
				}
				if (listID.equals("TO_EXPLORE")) {
					ListManager.getInstance().removeFromToExplore(index);
				}
				if (listID.equals("DO_NOT_SHOW")) {
					ListManager.getInstance().removeFromDoNotShow(index);
				}
			}
		}
		
		if (action.equals("MOVE")) {
			String itemID = request.getParameter("item_id");
			String destinationID = request.getParameter("destination_id");
			if (itemID != null && !itemID.equals("")) {
				int index = Integer.parseInt(itemID);
				if (listID.equals("FAVORITES")) {
					Result r = ListManager.getInstance().getFavorites().get(index);
					ListManager.getInstance().removeFromFavorites(r);
					if (destinationID.equals("TO_EXPLORE")) {
						ListManager.getInstance().addToToExplore(r);
					}
					if (destinationID.equals("DO_NOT_SHOW")) {
						ListManager.getInstance().addToDoNotShow(r);
					}
				}
				if (listID.equals("TO_EXPLORE")) {
					Result r = ListManager.getInstance().getToExplore().get(index);
					ListManager.getInstance().removeFromToExplore(r);
					if (destinationID.equals("FAVORITES")) {
						ListManager.getInstance().addToFavorites(r);
					}
					if (destinationID.equals("DO_NOT_SHOW")) {
						ListManager.getInstance().addToDoNotShow(r);
					}
				}
				if (listID.equals("DO_NOT_SHOW")) {
					Result r = ListManager.getInstance().getToExplore().get(index);
					ListManager.getInstance().removeFromDoNotShow(r);
					if (destinationID.equals("FAVORITES")) {
						ListManager.getInstance().addToFavorites(r);
					}
					if (destinationID.equals("TO_EXPLORE")) {
						ListManager.getInstance().addToToExplore(r);
					}
				}
			}
		}
		
		if (action.equals("DISPLAY")) {
			String itemID = request.getParameter("item_id");
			if (itemID != null && !itemID.equals("")) {
				int index = Integer.parseInt(itemID);
				if (listID.equals("FAVORITES")) {
					if (ListManager.getInstance().getFavorites().get(index) instanceof Recipe) {
						request.getRequestDispatcher("recipe_page.jsp?list_id=FAVORITES&item_id=" + index).forward(request, response);
					}
					else if (ListManager.getInstance().getFavorites().get(index) instanceof Restaurant) {
						request.getRequestDispatcher("restaurant_page.jsp?list_id=FAVORITES&item_id=" + index).forward(request, response);
					}
				}
				if (listID.equals("TO_EXPLORE")) {
					if (ListManager.getInstance().getToExplore().get(index) instanceof Recipe) {
						request.getRequestDispatcher("recipe_page.jsp?list_id=TO_EXPLORE&item_id=" + index).forward(request, response);
					}
					else if (ListManager.getInstance().getToExplore().get(index) instanceof Restaurant) {
						request.getRequestDispatcher("restaurant_page.jsp?list_id=TO_EXPLORE&item_id=" + index).forward(request, response);
					}
				}
				if (listID.equals("DO_NOT_SHOW")) {
					if (ListManager.getInstance().getDoNotShow().get(index) instanceof Recipe) {
						request.getRequestDispatcher("recipe_page.jsp?list_id=DO_NOT_SHOW&item_id=" + index).forward(request, response);
					}
					else if (ListManager.getInstance().getDoNotShow().get(index) instanceof Restaurant) {
						request.getRequestDispatcher("restaurant_page.jsp?list_id=DO_NOT_SHOW&item_id=" + index).forward(request, response);
					}
				}
			}
		}
	}
}	