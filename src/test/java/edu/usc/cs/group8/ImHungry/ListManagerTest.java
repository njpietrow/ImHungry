package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListManagerTest {

	@Test
	public void testFavorites() {
		IHSearch search = new IHSearch();
		User currUser = new User("GJHalfond");
		Recipe myRecipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/"));
		Restaurant myRestaurant = search.doRestaurantSearch("spaghetti", "1", "5000",currUser).get(0);
		currUser.addToFavorites(myRecipe);
		currUser.addToFavorites(myRestaurant);
		assertEquals(currUser.getFavorites().size(),2);
		assertEquals(currUser.getFavorites().get(0), myRecipe);
		assertEquals(currUser.getFavorites().get(1), myRestaurant);
		assertTrue(currUser.favoritesContains(myRecipe));
		assertTrue(currUser.favoritesContains(myRestaurant));
		currUser.removeFromFavorites(0);
		currUser.removeFromFavorites(myRestaurant);
		currUser.removeFromFavorites(20);
		currUser.removeFromFavorites(myRecipe);
		assertEquals(currUser.getFavorites().size(),0);
		assertFalse(currUser.favoritesContains(myRecipe));
		assertFalse(currUser.favoritesContains(myRestaurant));
	}
	
	@Test
	public void testToExplore() {
		IHSearch search = new IHSearch();
		User currUser = new User("GJHalfond");
		Recipe myRecipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/"));
		Restaurant myRestaurant = search.doRestaurantSearch("spaghetti", "1","5000", currUser).get(0);
		currUser.addToToExplore(myRecipe);
		currUser.addToToExplore(myRestaurant);
		assertEquals(currUser.getToExplore().size(),2);
		assertEquals(currUser.getToExplore().get(0), myRecipe);
		assertEquals(currUser.getToExplore().get(1), myRestaurant);
		assertTrue(currUser.toExploreContains(myRecipe));
		assertTrue(currUser.toExploreContains(myRestaurant));
		currUser.removeFromToExplore(0);
		currUser.removeFromToExplore(myRestaurant);
		currUser.removeFromToExplore(20);
		currUser.removeFromToExplore(myRecipe);
		assertEquals(currUser.getToExplore().size(),0);
		assertFalse(currUser.toExploreContains(myRecipe));
		assertFalse(currUser.toExploreContains(myRestaurant));
	}
	
	@Test
	public void testDoNotShow() {
		IHSearch search = new IHSearch();
		User currUser = new User("GJHalfond");
		Recipe myRecipe = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/"));
		Restaurant myRestaurant = search.doRestaurantSearch("spaghetti", "1","5000", currUser).get(0);
		currUser.addToDoNotShow(myRecipe);
		currUser.addToDoNotShow(myRestaurant);
		assertEquals(currUser.getDoNotShow().size(),2);
		assertEquals(currUser.getDoNotShow().get(0), myRecipe);
		assertEquals(currUser.getDoNotShow().get(1), myRestaurant);
		assertTrue(currUser.doNotShowContains(myRecipe));
		assertTrue(currUser.doNotShowContains(myRestaurant));
		currUser.removeFromDoNotShow(0);
		currUser.removeFromDoNotShow(myRestaurant);
		currUser.removeFromDoNotShow(20);
		currUser.removeFromDoNotShow(myRecipe);
		assertEquals(currUser.getDoNotShow().size(),0);
		assertFalse(currUser.doNotShowContains(myRecipe));
		assertFalse(currUser.doNotShowContains(myRestaurant));
	}

}
