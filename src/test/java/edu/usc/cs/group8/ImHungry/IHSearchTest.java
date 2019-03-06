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
import org.mockito.Mockito;

public class IHSearchTest {
	
	@Test
    public void testRestaurantSearch() throws Exception {
       IHSearch IHS = new IHSearch();
       ArrayList<Restaurant> restaurants = IHS.doRestaurantSearch("spaghetti", "10");
       assertEquals(restaurants.get(0).getName(),"Lemonade - USC");
       assertEquals(restaurants.get(1).getName(),"Viztango Cafe");
    }
	
	@Test
    public void testRecipeSearch() throws Exception {
       IHSearch IHS = new IHSearch();
       ArrayList<Recipe> recipes = IHS.doRecipeSearch("spaghetti", "10");
       assertEquals(recipes.get(0).getName(),"Easy Meaty Spaghetti");
       assertEquals(recipes.get(1).getName(),"Mozzarella Baked Spaghetti");
    }
	
	
//	@Test
//    public void testServletNullLists() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);       
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher RD = mock(RequestDispatcher.class);
//
//
//        when(request.getParameter("search_query")).thenReturn("spaghetti");
//        when(request.getParameter("num_results")).thenReturn("10");
//        when(request.getSession()).thenReturn(session);
//        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
//        
//        IHSearch search = mock(IHSearch.class);
//        ArrayList<String> images = null;
//		ArrayList<Recipe> recipes = null;
//		ArrayList<Restaurant> restaurants = null;
//        
//        when(search.doImageSearch("spaghetti")).thenReturn(images);
//        when(search.doRecipeSearch("spaghetti", "10")).thenReturn(recipes);
//        when(search.doRestaurantSearch("spaghetti", "10")).thenReturn(restaurants);
//        
//        search.doGet(request, response);      
//    }
	


}
