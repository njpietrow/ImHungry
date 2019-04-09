package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class IHManageListTest {
	
	@Test
	public void testDoGet() throws Exception {
		
		
		IHSearch search = new IHSearch();
		User currUser = new User("GJHalfond");
		currUser.getLists().reset();
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher RD = mock(RequestDispatcher.class);


        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("FAVORITES");
        when(request.getParameter("recipe_id")).thenReturn("0");
        when(request.getParameter("restaurant_id")).thenReturn("");
        
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("recipes")).thenReturn(search.doRecipeSearch("spaghetti", "5",currUser));
        when(request.getSession().getAttribute("restaurants")).thenReturn(search.doRestaurantSearch("spaghetti", "5", "5000", currUser));
        when(request.getRequestDispatcher("recipe_page.jsp?list_id=FAVORITES&item_id=0")).thenReturn(RD);
        when(request.getRequestDispatcher("restaurant_page.jsp?list_id=FAVORITES&item_id=1")).thenReturn(RD);
        when(request.getRequestDispatcher("recipe_page.jsp?list_id=TO_EXPLORE&item_id=0")).thenReturn(RD);
        when(request.getRequestDispatcher("restaurant_page.jsp?list_id=TO_EXPLORE&item_id=1")).thenReturn(RD);
        when(request.getRequestDispatcher("recipe_page.jsp?list_id=DO_NOT_SHOW&item_id=0")).thenReturn(RD);
        when(request.getRequestDispatcher("restaurant_page.jsp?list_id=DO_NOT_SHOW&item_id=1")).thenReturn(RD);
        when(request.getRequestDispatcher("recipe_page.jsp?recipe_id=0")).thenReturn(RD);
        when(request.getRequestDispatcher("restaurant_page.jsp?restaurant_id=0")).thenReturn(RD);
		when(request.getRequestDispatcher("list_management_page.jsp?list_id=FAVORITES")).thenReturn(RD);
		when(request.getRequestDispatcher("list_management_page.jsp?list_id=TO_EXPLORE")).thenReturn(RD);
		when(request.getRequestDispatcher("list_management_page.jsp?list_id=DO_NOT_SHOW")).thenReturn(RD);
        IHManageList manager = new IHManageList();
        when(request.getSession().getAttribute("user")).thenReturn(new User("GJHalfond"));
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("MOVE");
        when(request.getParameter("list_id")).thenReturn("FAVORITES");
        when(request.getParameter("destination_id")).thenReturn("TO_EXPLORE");
        when(request.getParameter("item_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("REMOVE");
        when(request.getParameter("list_id")).thenReturn("TO_EXPLORE");
        when(request.getParameter("item_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        currUser.getLists().reset();
        
        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("FAVORITES");
        when(request.getParameter("recipe_id")).thenReturn("0");
        when(request.getParameter("restaurant_id")).thenReturn("");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("DISPLAY");
        when(request.getParameter("list_id")).thenReturn("FAVORITES");
        when(request.getParameter("item_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("FAVORITES");
        when(request.getParameter("recipe_id")).thenReturn("");
        when(request.getParameter("restaurant_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("DISPLAY");
        when(request.getParameter("list_id")).thenReturn("FAVORITES");
        when(request.getParameter("item_id")).thenReturn("1");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("TO_EXPLORE");
        when(request.getParameter("recipe_id")).thenReturn("0");
        when(request.getParameter("restaurant_id")).thenReturn("");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("DISPLAY");
        when(request.getParameter("list_id")).thenReturn("TO_EXPLORE");
        when(request.getParameter("item_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("TO_EXPLORE");
        when(request.getParameter("recipe_id")).thenReturn("");
        when(request.getParameter("restaurant_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("DISPLAY");
        when(request.getParameter("list_id")).thenReturn("TO_EXPLORE");
        when(request.getParameter("item_id")).thenReturn("1");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("DO_NOT_SHOW");
        when(request.getParameter("recipe_id")).thenReturn("0");
        when(request.getParameter("restaurant_id")).thenReturn("");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("DISPLAY");
        when(request.getParameter("list_id")).thenReturn("DO_NOT_SHOW");
        when(request.getParameter("item_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("ADD");
        when(request.getParameter("list_id")).thenReturn("DO_NOT_SHOW");
        when(request.getParameter("recipe_id")).thenReturn("");
        when(request.getParameter("restaurant_id")).thenReturn("0");
        
        manager.doGet(request, response);
        
        when(request.getParameter("action")).thenReturn("DISPLAY");
        when(request.getParameter("list_id")).thenReturn("DO_NOT_SHOW");
        when(request.getParameter("item_id")).thenReturn("1");
        
        manager.doGet(request, response);
	}

	@Test
	public void testAddToList() {
		User currUser = new User("GJHalfond");
		currUser.getLists().reset();
		IHSearch search = new IHSearch();
		ArrayList<Recipe> testRecipes = search.doRecipeSearch("falafel", "3",currUser);
		ArrayList<Restaurant> testRestaurants = search.doRestaurantSearch("ramen", "3", "5000", currUser);
		search.sortRecipes(testRecipes,currUser);
		search.sortRestaurants(testRestaurants,currUser);
		
		IHManageList manager = new IHManageList();
		manager.addToList(currUser,"DO_NOT_SHOW", "0", "", testRecipes, testRestaurants);
		manager.addToList(currUser,"DO_NOT_SHOW", "", "0", testRecipes, testRestaurants);
		manager.addToList(currUser,"TO_EXPLORE", "1", "", testRecipes, testRestaurants);
		manager.addToList(currUser,"TO_EXPLORE", "", "1", testRecipes, testRestaurants);
		manager.addToList(currUser,"FAVORITES", "2", "", testRecipes, testRestaurants);
		manager.addToList(currUser,"FAVORITES", "", "2", testRecipes, testRestaurants);
		
		ArrayList<Recipe> newRecipes = search.doRecipeSearch("falafel", "3",currUser);
		ArrayList<Restaurant> newRestaurants = search.doRestaurantSearch("ramen", "3", "5000", currUser);
		search.sortRecipes(newRecipes,currUser);
		search.sortRestaurants(newRestaurants,currUser);
		
		assertEquals(newRecipes.get(0), testRecipes.get(2));
		assertFalse(newRecipes.contains(testRecipes.get(0)));
		assertEquals(newRestaurants.get(0), testRestaurants.get(2));
		assertFalse(newRestaurants.contains(testRestaurants.get(0)));
	}
	
	@Test
	public void testRemoveFromList() {
		User currUser = new User("GJHalfond");
		currUser.getLists().reset();
		IHSearch search = new IHSearch();
		ArrayList<Recipe> oldRecipes = search.doRecipeSearch("falafel", "3",currUser);
		ArrayList<Restaurant> oldRestaurants = search.doRestaurantSearch("ramen", "3", "5000", currUser);

		ArrayList<Recipe> testRecipes = oldRecipes;
		ArrayList<Restaurant> testRestaurants = oldRestaurants;
		search.sortRecipes(testRecipes,currUser);
		search.sortRestaurants(testRestaurants,currUser);
		
		IHManageList manager = new IHManageList();
		manager.addToList(currUser,"DO_NOT_SHOW", "0", "", testRecipes, testRestaurants);
		manager.addToList(currUser,"DO_NOT_SHOW", "", "0", testRecipes, testRestaurants);
		manager.addToList(currUser,"TO_EXPLORE", "1", "", testRecipes, testRestaurants);
		manager.addToList(currUser,"TO_EXPLORE", "", "1", testRecipes, testRestaurants);
		manager.addToList(currUser,"FAVORITES", "2", "", testRecipes, testRestaurants);
		manager.addToList(currUser,"FAVORITES", "", "2", testRecipes, testRestaurants);
		
		manager.removeFromList(currUser,"FAVORITES", "1");
		manager.removeFromList(currUser,"TO_EXPLORE", "1");
		manager.removeFromList(currUser,"DO_NOT_SHOW", "1");
		
		ArrayList<Recipe> newRecipes = search.doRecipeSearch("falafel", "3",currUser);
		ArrayList<Restaurant> newRestaurants = search.doRestaurantSearch("ramen", "3", "5000", currUser);
		search.sortRecipes(newRecipes,currUser);
		search.sortRestaurants(newRestaurants,currUser);
		
		assertEquals(newRecipes.get(0), testRecipes.get(2));
		assertFalse(newRecipes.contains(testRecipes.get(0)));
		assertEquals(newRestaurants.get(0), oldRestaurants.get(0));
		assertEquals(newRestaurants.get(1), oldRestaurants.get(1));
		assertEquals(newRestaurants.get(2), oldRestaurants.get(2));
	}
	
	@Test
	public void testMoveToList() {
		
		IHManageList manager = new IHManageList();
		IHSearch search = new IHSearch();
		User currUser = new User("GJHalfond");

		currUser.getLists().reset();
		ArrayList<Recipe> recipe = search.doRecipeSearch("falafel", "1",currUser);
		ArrayList<Restaurant> restaurant = search.doRestaurantSearch("ramen", "1", "5000", currUser);
		
		manager.addToList(currUser,"DO_NOT_SHOW", "0", "", recipe, restaurant);
		manager.addToList(currUser,"DO_NOT_SHOW", "", "0", recipe, restaurant);
		
		manager.moveToList(currUser,"DO_NOT_SHOW", "FAVORITES", "1");
		manager.moveToList(currUser,"DO_NOT_SHOW", "TO_EXPLORE", "0");
		
		assertEquals(currUser.getLists().getDoNotShow().size(),0);
		assertEquals(currUser.getLists().getToExplore().get(0),recipe.get(0));
		assertEquals(currUser.getLists().getFavorites().get(0),restaurant.get(0));
		
		manager.removeFromList(currUser,"FAVORITES", "0");
		manager.removeFromList(currUser,"TO_EXPLORE", "0");
		manager.addToList(currUser,"FAVORITES", "0", "", recipe, restaurant);
		manager.addToList(currUser,"FAVORITES", "", "0", recipe, restaurant);
		manager.moveToList(currUser,"FAVORITES", "DO_NOT_SHOW", "1");
		manager.moveToList(currUser,"FAVORITES", "TO_EXPLORE", "0");
		
		assertEquals(currUser.getLists().getFavorites().size(),0);
		assertEquals(currUser.getLists().getToExplore().get(0),recipe.get(0));
		assertEquals(currUser.getLists().getDoNotShow().get(0),restaurant.get(0));
		
		manager.removeFromList(currUser,"DO_NOT_SHOW", "0");
		manager.removeFromList(currUser,"TO_EXPLORE", "0");
		manager.addToList(currUser,"TO_EXPLORE", "0", "", recipe, restaurant);
		manager.addToList(currUser,"TO_EXPLORE", "", "0", recipe, restaurant);
		manager.moveToList(currUser,"TO_EXPLORE", "DO_NOT_SHOW", "1");
		manager.moveToList(currUser,"TO_EXPLORE", "FAVORITES", "0");
		
		assertEquals(currUser.getLists().getToExplore().size(),0);
		assertEquals(currUser.getLists().getFavorites().get(0),recipe.get(0));
		assertEquals(currUser.getLists().getDoNotShow().get(0),restaurant.get(0));
		
		
	}

}
