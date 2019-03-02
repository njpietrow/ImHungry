package edu.usc.cs.group8.ImHungry;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		response.setStatus(response.SC_BAD_GATEWAY);
		response.getWriter().println("Unknown error occurred.");
		response.getWriter().flush();
	}
	
	private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }

}
