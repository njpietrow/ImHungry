package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListManagerTest {

	@Test
	public void testFavorites() {
		ListManager.getInstance().reset();
		IHSearch search = new IHSearch();
		Recipe myRecipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/"));
		Restaurant myRestaurant = search.doRestaurantSearch("spaghetti", "1").get(0);
		ListManager.getInstance().addToFavorites(myRecipe);
		ListManager.getInstance().addToFavorites(myRestaurant);
		assertEquals(ListManager.getInstance().getFavorites().size(),2);
		assertEquals(ListManager.getInstance().getFavorites().get(0), myRecipe);
		assertEquals(ListManager.getInstance().getFavorites().get(1), myRestaurant);
		assertTrue(ListManager.getInstance().favoritesContains(myRecipe));
		assertTrue(ListManager.getInstance().favoritesContains(myRestaurant));
		ListManager.getInstance().removeFromFavorites(0);
		ListManager.getInstance().removeFromFavorites(myRestaurant);
		ListManager.getInstance().removeFromFavorites(20);
		ListManager.getInstance().removeFromFavorites(myRecipe);
		assertEquals(ListManager.getInstance().getFavorites().size(),0);
		assertFalse(ListManager.getInstance().favoritesContains(myRecipe));
		assertFalse(ListManager.getInstance().favoritesContains(myRestaurant));
	}
	
	@Test
	public void testToExplore() {
		ListManager.getInstance().reset();
		IHSearch search = new IHSearch();
		Recipe myRecipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/"));
		Restaurant myRestaurant = search.doRestaurantSearch("spaghetti", "1").get(0);
		ListManager.getInstance().addToToExplore(myRecipe);
		ListManager.getInstance().addToToExplore(myRestaurant);
		assertEquals(ListManager.getInstance().getToExplore().size(),2);
		assertEquals(ListManager.getInstance().getToExplore().get(0), myRecipe);
		assertEquals(ListManager.getInstance().getToExplore().get(1), myRestaurant);
		assertTrue(ListManager.getInstance().toExploreContains(myRecipe));
		assertTrue(ListManager.getInstance().toExploreContains(myRestaurant));
		ListManager.getInstance().removeFromToExplore(0);
		ListManager.getInstance().removeFromToExplore(myRestaurant);
		ListManager.getInstance().removeFromToExplore(20);
		ListManager.getInstance().removeFromToExplore(myRecipe);
		assertEquals(ListManager.getInstance().getToExplore().size(),0);
		assertFalse(ListManager.getInstance().toExploreContains(myRecipe));
		assertFalse(ListManager.getInstance().toExploreContains(myRestaurant));
	}
	
	@Test
	public void testDoNotShow() {
		ListManager.getInstance().reset();
		IHSearch search = new IHSearch();
		Recipe myRecipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/"));
		Restaurant myRestaurant = search.doRestaurantSearch("spaghetti", "1").get(0);
		ListManager.getInstance().addToDoNotShow(myRecipe);
		ListManager.getInstance().addToDoNotShow(myRestaurant);
		assertEquals(ListManager.getInstance().getDoNotShow().size(),2);
		assertEquals(ListManager.getInstance().getDoNotShow().get(0), myRecipe);
		assertEquals(ListManager.getInstance().getDoNotShow().get(1), myRestaurant);
		assertTrue(ListManager.getInstance().doNotShowContains(myRecipe));
		assertTrue(ListManager.getInstance().doNotShowContains(myRestaurant));
		ListManager.getInstance().removeFromDoNotShow(0);
		ListManager.getInstance().removeFromDoNotShow(myRestaurant);
		ListManager.getInstance().removeFromDoNotShow(20);
		ListManager.getInstance().removeFromDoNotShow(myRecipe);
		assertEquals(ListManager.getInstance().getDoNotShow().size(),0);
		assertFalse(ListManager.getInstance().doNotShowContains(myRecipe));
		assertFalse(ListManager.getInstance().doNotShowContains(myRestaurant));
	}

}
