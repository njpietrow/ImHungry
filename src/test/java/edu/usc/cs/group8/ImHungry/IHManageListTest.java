package edu.usc.cs.group8.ImHungry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

public class IHManageListTest {

	@Test
	public void testAddToList() {
		ListManager.getInstance().reset();

		IHSearch search = new IHSearch();
		ArrayList<Recipe> testRecipes = search.doRecipeSearch("falafel", "3");
		ArrayList<Restaurant> testRestaurants = search.doRestaurantSearch("ramen", "3");
		search.sortRecipes(testRecipes);
		search.sortRestaurants(testRestaurants);
		
		IHManageList manager = new IHManageList();
		manager.addToList("DO_NOT_SHOW", "0", "", testRecipes, testRestaurants);
		manager.addToList("DO_NOT_SHOW", "", "0", testRecipes, testRestaurants);
		manager.addToList("TO_EXPLORE", "1", "", testRecipes, testRestaurants);
		manager.addToList("TO_EXPLORE", "", "1", testRecipes, testRestaurants);
		manager.addToList("FAVORITES", "2", "", testRecipes, testRestaurants);
		manager.addToList("FAVORITES", "", "2", testRecipes, testRestaurants);
		
		ArrayList<Recipe> newRecipes = search.doRecipeSearch("falafel", "3");
		ArrayList<Restaurant> newRestaurants = search.doRestaurantSearch("ramen", "3");
		search.sortRecipes(newRecipes);
		search.sortRestaurants(newRestaurants);
		
		assertEquals(newRecipes.get(0), testRecipes.get(2));
		assertFalse(newRecipes.contains(testRecipes.get(0)));
		assertEquals(newRestaurants.get(0), testRestaurants.get(2));
		assertFalse(newRestaurants.contains(testRestaurants.get(0)));
	}
	
	@Test
	public void testRemoveFromList() {
		ListManager.getInstance().reset();
		IHSearch search = new IHSearch();
		ArrayList<Recipe> oldRecipes = search.doRecipeSearch("falafel", "3");
		ArrayList<Restaurant> oldRestaurants = search.doRestaurantSearch("ramen", "3");
		
		System.out.println(ListManager.getInstance().getDoNotShow() + "In Do Not Show");

		ArrayList<Recipe> testRecipes = oldRecipes;
		ArrayList<Restaurant> testRestaurants = oldRestaurants;
		search.sortRecipes(testRecipes);
		search.sortRestaurants(testRestaurants);
		
		IHManageList manager = new IHManageList();
		manager.addToList("DO_NOT_SHOW", "0", "", testRecipes, testRestaurants);
		manager.addToList("DO_NOT_SHOW", "", "0", testRecipes, testRestaurants);
		manager.addToList("TO_EXPLORE", "1", "", testRecipes, testRestaurants);
		manager.addToList("TO_EXPLORE", "", "1", testRecipes, testRestaurants);
		manager.addToList("FAVORITES", "2", "", testRecipes, testRestaurants);
		manager.addToList("FAVORITES", "", "2", testRecipes, testRestaurants);
		
		manager.removeFromList("FAVORITES", "1");
		manager.removeFromList("TO_EXPLORE", "1");
		manager.removeFromList("DO_NOT_SHOW", "1");
		
		ArrayList<Recipe> newRecipes = search.doRecipeSearch("falafel", "3");
		ArrayList<Restaurant> newRestaurants = search.doRestaurantSearch("ramen", "3");
		search.sortRecipes(newRecipes);
		search.sortRestaurants(newRestaurants);
		
		assertEquals(newRecipes.get(0), testRecipes.get(2));
		assertFalse(newRecipes.contains(testRecipes.get(0)));
		assertEquals(newRestaurants.get(0), oldRestaurants.get(0));
		assertEquals(newRestaurants.get(1), oldRestaurants.get(1));
		assertEquals(newRestaurants.get(2), oldRestaurants.get(2));
	}
	
	@Test
	public void testMoveToList() {
		ListManager.getInstance().reset();
		
		IHManageList manager = new IHManageList();
		IHSearch search = new IHSearch();
		ArrayList<Recipe> recipe = search.doRecipeSearch("falafel", "1");
		ArrayList<Restaurant> restaurant = search.doRestaurantSearch("ramen", "1");
		System.out.println(ListManager.getInstance().getDoNotShow());
		System.out.println(restaurant);
		
		manager.addToList("DO_NOT_SHOW", "0", "", recipe, restaurant);
		manager.addToList("DO_NOT_SHOW", "", "0", recipe, restaurant);
		
		System.out.println(ListManager.getInstance().getDoNotShow());
		manager.moveToList("DO_NOT_SHOW", "FAVORITES", "1");
		manager.moveToList("DO_NOT_SHOW", "TO_EXPLORE", "0");
		
		assertEquals(ListManager.getInstance().getDoNotShow().size(),0);
		assertEquals(ListManager.getInstance().getToExplore().get(0),recipe.get(0));
		assertEquals(ListManager.getInstance().getFavorites().get(0),restaurant.get(0));
		
		manager.removeFromList("FAVORITES", "0");
		manager.removeFromList("TO_EXPLORE", "0");
		manager.addToList("FAVORITES", "0", "", recipe, restaurant);
		manager.addToList("FAVORITES", "", "0", recipe, restaurant);
		manager.moveToList("FAVORITES", "DO_NOT_SHOW", "1");
		manager.moveToList("FAVORITES", "TO_EXPLORE", "0");
		
		assertEquals(ListManager.getInstance().getFavorites().size(),0);
		assertEquals(ListManager.getInstance().getToExplore().get(0),recipe.get(0));
		assertEquals(ListManager.getInstance().getDoNotShow().get(0),restaurant.get(0));
		
		manager.removeFromList("DO_NOT_SHOW", "0");
		manager.removeFromList("TO_EXPLORE", "0");
		manager.addToList("TO_EXPLORE", "0", "", recipe, restaurant);
		manager.addToList("TO_EXPLORE", "", "0", recipe, restaurant);
		manager.moveToList("TO_EXPLORE", "DO_NOT_SHOW", "1");
		manager.moveToList("TO_EXPLORE", "FAVORITES", "0");
		
		assertEquals(ListManager.getInstance().getToExplore().size(),0);
		assertEquals(ListManager.getInstance().getFavorites().get(0),recipe.get(0));
		assertEquals(ListManager.getInstance().getDoNotShow().get(0),restaurant.get(0));
		
		
	}

}
