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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class IHSearchTest {
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock 
	HttpSession session;
	
	@Mock 
	RequestDispatcher RD;
	
	@Mock 
	IHSearch MockSearch;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void testDoGet() throws Exception {
		when(request.getParameter("search_query")).thenReturn("spaghetti");
		when(request.getParameter("num_results")).thenReturn("10");
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        IHSearch IHS = spy(new IHSearch());
        Mockito.when(IHS.doImageSearch("spaghetti")).thenReturn(new ArrayList<String>());
        Mockito.when(IHS.doRestaurantSearch("spaghetti","10")).thenReturn(new ArrayList<Restaurant>());
        Mockito.when(IHS.doRecipeSearch("spaghetti","10")).thenReturn(new ArrayList<Recipe>());
         
        when(response.getWriter()).thenReturn(pw);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);

        IHS.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        //assertEquals(result, new String("Full Name: Vinod Kashyap"));
    }
	
	@Test
    public void testDoGetSearchesReturnNull() throws Exception {
		when(request.getParameter("search_query")).thenReturn("spaghetti");
		when(request.getParameter("num_results")).thenReturn("10");
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        IHSearch IHS = spy(new IHSearch());
        Mockito.when(IHS.doImageSearch("spaghetti")).thenReturn(null);
        Mockito.when(IHS.doRestaurantSearch("spaghetti","10")).thenReturn(null);
        Mockito.when(IHS.doRecipeSearch("spaghetti","10")).thenReturn(null);
         
        when(response.getWriter()).thenReturn(pw);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
 
        IHS.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        //assertEquals(result, new String("Full Name: Vinod Kashyap"));
    }
	
	@Test
    public void testDoGetImageSearchReturnsNull() throws Exception {
		when(request.getParameter("search_query")).thenReturn("spaghetti");
		when(request.getParameter("num_results")).thenReturn("10");
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        IHSearch IHS = spy(new IHSearch());
        Mockito.when(IHS.doImageSearch("spaghetti")).thenReturn(null);
        when(MockSearch.doRestaurantSearch("spaghetti","10")).thenReturn(new ArrayList<Restaurant>());
        when(MockSearch.doRecipeSearch("spaghetti","10")).thenReturn(new ArrayList<Recipe>());
         
        when(response.getWriter()).thenReturn(pw);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
 
        IHS.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        //assertEquals(result, new String("Full Name: Vinod Kashyap"));
    }
	
	@Test
    public void testDoGetRestaurantSearchReturnsNull() throws Exception {
		when(request.getParameter("search_query")).thenReturn("spaghetti");
		when(request.getParameter("num_results")).thenReturn("10");
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        IHSearch IHS = spy(new IHSearch());
        Mockito.when(IHS.doImageSearch("spaghetti")).thenReturn(new ArrayList<String>());
        when(MockSearch.doRestaurantSearch("spaghetti","10")).thenReturn(null);
        when(MockSearch.doRecipeSearch("spaghetti","10")).thenReturn(new ArrayList<Recipe>());
         
        when(response.getWriter()).thenReturn(pw);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
 
        IHS.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        //assertEquals(result, new String("Full Name: Vinod Kashyap"));
    }
	
	@Test
    public void testDoGetRecipeSearchReturnsNull() throws Exception {
		when(request.getParameter("search_query")).thenReturn("spaghetti");
		when(request.getParameter("num_results")).thenReturn("10");
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        IHSearch IHS = spy(new IHSearch());
        Mockito.when(IHS.doImageSearch("spaghetti")).thenReturn(new ArrayList<String>());
        when(MockSearch.doRestaurantSearch("spaghetti","10")).thenReturn(new ArrayList<Restaurant>());
        when(MockSearch.doRecipeSearch("spaghetti","10")).thenReturn(null);
         
        when(response.getWriter()).thenReturn(pw);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("results_page.jsp")).thenReturn(RD);
 
        IHS.doGet(request, response);
        String result = sw.getBuffer().toString().trim();
        //assertEquals(result, new String("Full Name: Vinod Kashyap"));
    }

	
	@Test
    public void testRestaurantSearchSorted() throws Exception {
       IHSearch IHS = new IHSearch();
       ArrayList<Restaurant> restaurants = IHS.doRestaurantSearch("pizza", "33");
       IHS.sortRestaurants(restaurants);
       assertEquals(restaurants.size(),20);
       for(int i =0;i<restaurants.size()-1; i++ ) {
    	   assertTrue(restaurants.get(i).getDriveTime()<=restaurants.get(i+1).getDriveTime());
       }

    }
	
	@Test
    public void testRecipeSearchSorted() throws Exception {
       IHSearch search = new IHSearch();
       ArrayList<Recipe> recipes = search.doRecipeSearch("spaghetti", "10");
       search.sortRecipes(recipes);
       assertEquals(recipes.size(),10);
       for(int i =0;i<recipes.size()-1; i++ ) {
    	   assertTrue(recipes.get(i).getPrepTime() <= recipes.get(i+1).getPrepTime() || recipes.get(i+1).getPrepTime() == 0);
       }
    }
	
	
	



}
