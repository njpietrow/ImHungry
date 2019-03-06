package edu.usc.cs.group8.ImHungry;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class IHSearchTest {
	
	@Test
    public void testServletNormal() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher RD = mock(RequestDispatcher.class);


        when(request.getParameter("search_query")).thenReturn("spaghetti");
        when(request.getParameter("num_results")).thenReturn("10");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
        
        
        ArrayList<String> images = null;
		ArrayList<Recipe> recipes = null;
		ArrayList<Restaurant> restaurants = null;
        

		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
        new IHSearch().doGet(request, response);      
    }
	
	@Test
    public void testServletNullJsonString() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher RD = mock(RequestDispatcher.class);


        when(request.getParameter("search_query")).thenReturn("spaghetti");
        when(request.getParameter("num_results")).thenReturn("10");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
        
        IHSearch search = mock(IHSearch.class);
        String keyword = "spaghetti";
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
				+ "location=34.020593,-118.285447"
//				+ "&radius=2500"
				+ "&type=restaurant"
				+ "&rankby=distance"
				+ "&keyword=" + keyword
				+ "&key=AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA\n";
        when(search.readWebsite(url)).thenReturn(null);
        
        assertTrue(search.doRestaurantSearch(keyword, "10") == null);      
    }
	@Test
    public void testServletNullLists() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher RD = mock(RequestDispatcher.class);


        when(request.getParameter("search_query")).thenReturn("spaghetti");
        when(request.getParameter("num_results")).thenReturn("10");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
        
        IHSearch search = mock(IHSearch.class);
        ArrayList<String> images = null;
		ArrayList<Recipe> recipes = null;
		ArrayList<Restaurant> restaurants = null;
        
        when(search.doImageSearch("spaghetti")).thenReturn(images);
        when(search.doRecipeSearch("spaghetti", "10")).thenReturn(recipes);
        when(search.doRestaurantSearch("spaghetti", "10")).thenReturn(restaurants);
        
        search.doGet(request, response);      
    }
	


}
