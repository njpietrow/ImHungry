package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DataPersistenceTest {

	@Test
	public void testListsStillThere() {
		LoginHelper helper = new LoginHelper();
		helper.logout(new User());
		User currUser = new User();
		helper.login("GJHalfond","Scrum",currUser);
		Recipe r = RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		r.setURL("http://localhost:8080/ImHungry/testrecipe.html");
		Restaurant p = currUser.getRestaurant("ChIJ_xMfDefHwoAR9ho1OxKZ850");
		currUser.addToFavorites(r);
		currUser.addToFavorites(p);
		currUser.addToToExplore(r);
		currUser.addToToExplore(p);
		currUser.addToDoNotShow(r);
		currUser.addToDoNotShow(p);
		helper.logout(currUser);
		helper.login("GJHalfond","Scrum",currUser);
		assertTrue(currUser.getLists().favoritesContains(r));
		assertTrue(currUser.getLists().toExploreContains(r));
		assertTrue(currUser.getLists().doNotShowContains(r));
		assertTrue(currUser.getLists().favoritesContains(p));
		assertTrue(currUser.getLists().toExploreContains(p));
		assertTrue(currUser.getLists().doNotShowContains(p));
		currUser.reset();

		
		
	}
	
	@Test
	public void testResultsStillThere() {
		LoginHelper helper = new LoginHelper(); 
		User currUser = new User(); 
		helper.login("GJHalfond","Scrum",currUser);
		IHSearch searchy = new IHSearch(); 
		searchy.doRestaurantSearch("Curry", "3", "5000", currUser); 
		currUser.addToQuickAccess(new Query("Curry","3","5000"));
		helper.logout(currUser);
		helper.login("GJHalfond","Scrum",currUser);
		assertEquals(currUser.getLastSearch(),new Query("Curry","3", "5000" ));
	}
	
	@Test
	public void testQuickAccessStillThere() {
		LoginHelper helper = new LoginHelper(); 
		User currUser = new User(); 
		helper.login("GJHalfond","Scrum",currUser);
		IHSearch searchy = new IHSearch(); 
		searchy.doRestaurantSearch("Curry", "3", "5000", currUser); 
		helper.logout(currUser);
		helper.login("GJHalfond","Scrum",currUser);
		assertTrue(currUser.getLists().quickAccessContains(new Query("Curry","3", "5000")));
	}
	
	@Test
	public void testListsPrivateToUser() {
		LoginHelper helper = new LoginHelper();
		User currUser = new User();
		helper.login("GJHalfond","Scrum",currUser);
		Recipe r =RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		r.setURL("http://localhost:8080/ImHungry/testrecipe.html");
		currUser.addToFavorites(r);
		helper.logout(currUser);
		helper.login("KevinCalaway","Doopadoop",currUser);
		assertFalse(currUser.getLists().favoritesContains(new Recipe("Curry", "20", "10", "food.jpg", new ArrayList<String>(10), new ArrayList<String>(5))));
	}
	
	@Test
	public void testResultsPrivateToUser() {
		LoginHelper helper = new LoginHelper(); 
		User currUser = new User(); 
		helper.login("GJHalfond","Scrum",currUser);
		IHSearch searchy = new IHSearch(); 
		searchy.doRestaurantSearch("Curry", "3", "5000", currUser); 
		helper.logout(currUser);
		helper.login("KCalaway","Doopadoop",currUser);
		assertNotEquals(currUser.getLastSearch(),new Query("Curry","3", "5000"));
	}

	@Test
	public void testQuickAccessPrivateToUser() {
		LoginHelper helper = new LoginHelper(); 
		User currUser = new User(); 
		helper.login("GJHalfond","Scrum",currUser);
		IHSearch searchy = new IHSearch(); 
		searchy.doRestaurantSearch("Curry", "3", "5000", currUser); 
		helper.logout(currUser);
		helper.login("KCalaway","Doopadoop",currUser);
		assertFalse(currUser.getLists().quickAccessContains(new Query("Curry","3", "5000")));
	}
}