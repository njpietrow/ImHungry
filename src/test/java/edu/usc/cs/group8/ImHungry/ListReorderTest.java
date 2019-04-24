package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListReorderTest {

	@Test
	public void testSwap() {
		User myUser = new User("GJHalfond");
		myUser.reset();
		
		myUser.addToFavorites(myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		myUser.addToFavorites(myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		assertEquals(myUser.getFavorites().get(0),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getFavorites().get(1),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapFavorites(0,1);
		
		LoginHelper lh = new LoginHelper();
		lh.logout(myUser);
		lh.login("GJHalfond", "Scrum", myUser);
		
		assertEquals(myUser.getFavorites().get(1),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getFavorites().get(0),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		
		myUser.swapFavorites(0,1);
		assertEquals(myUser.getFavorites().get(0),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getFavorites().get(1),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		
		myUser.reset();
		
		myUser.addToToExplore(myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		myUser.addToToExplore(myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		assertEquals(myUser.getToExplore().get(0),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getToExplore().get(1),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapToExplore(0,1);
		
		lh.logout(myUser);
		lh.login("GJHalfond", "Scrum", myUser);
		assertEquals(myUser.getToExplore().get(1),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getToExplore().get(0),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapToExplore(0,1);
		assertEquals(myUser.getToExplore().get(0),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getToExplore().get(1),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		
		myUser.reset();
		
		myUser.addToDoNotShow(myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		myUser.addToDoNotShow(myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		assertEquals(myUser.getDoNotShow().get(0),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getDoNotShow().get(1),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapDoNotShow(0,1);
		lh.logout(myUser);
		lh.login("GJHalfond", "Scrum", myUser);
		assertEquals(myUser.getDoNotShow().get(1),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getDoNotShow().get(0),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapDoNotShow(0,1);
		assertEquals(myUser.getDoNotShow().get(0),myUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getDoNotShow().get(1),myUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM"));
	}
	
	@Test
	public void testServlet() {
		IHManageList swapper = new IHManageList();
		LoginHelper lh = new LoginHelper();
		User currUser = new User();
		lh.login("GJHalfond", "Scrum", currUser);
		currUser.reset();
		
		Restaurant r1 = currUser.getRestaurant("ChIJwajStEu5woAR9O3sDMuI1UM");
		Restaurant r2 = currUser.getRestaurant("ChIJJewhXDol6IARKGxyEnT4sIA");
		Recipe r3 = currUser.getRecipe("http://localhost:8080/ImHungry/testrecipe.html");
		Recipe r4 = currUser.getRecipe("http://localhost:8080/ImHungry/testrecipe2.html");
		
		currUser.addToFavorites(r1);
		currUser.addToFavorites(r2);
		currUser.addToFavorites(r3);
		currUser.addToFavorites(r4);

		currUser.addToToExplore(r1);
		currUser.addToToExplore(r2);
		currUser.addToToExplore(r3);
		currUser.addToToExplore(r4);
		
		currUser.addToDoNotShow(r1);
		currUser.addToDoNotShow(r2);
		currUser.addToDoNotShow(r3);
		currUser.addToDoNotShow(r4);
		
		assertEquals(currUser.getFavorites().size(),4);
		assertEquals(currUser.getToExplore().size(),4);
		assertEquals(currUser.getDoNotShow().size(),4);
		
		swapper.swapItems(currUser, "FAVORITES", "-1","0");
		swapper.swapItems(currUser, "FAVORITES", "0","-1");
		swapper.swapItems(currUser, "FAVORITES", "0", "1");
		swapper.swapItems(currUser, "FAVORITES", "2", "3");
		swapper.swapItems(currUser, "FAVORITES", "1", "2");
		swapper.swapItems(currUser, "FAVORITES", "1", "2");
		swapper.swapItems(currUser, "FAVORITES", "3", "4");
		swapper.swapItems(currUser, "FAVORITES", "4","3");
		
		assertEquals(currUser.getFavorites().get(3),r3);
		assertEquals(currUser.getFavorites().get(1),r1);
		
		swapper.swapItems(currUser, "TO_EXPLORE", "-1","0");
		swapper.swapItems(currUser, "TO_EXPLORE", "0","-1");
		swapper.swapItems(currUser, "TO_EXPLORE", "0", "1");
		swapper.swapItems(currUser, "TO_EXPLORE", "2", "3");
		swapper.swapItems(currUser, "TO_EXPLORE", "1", "2");
		swapper.swapItems(currUser, "TO_EXPLORE", "1", "2");
		swapper.swapItems(currUser, "TO_EXPLORE", "3", "4");
		swapper.swapItems(currUser, "TO_EXPLORE", "4","3");
		
		assertEquals(currUser.getToExplore().get(3),r3);
		assertEquals(currUser.getToExplore().get(1),r1);
		
		swapper.swapItems(currUser, "DO_NOT_SHOW", "-1","0");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "0","-1");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "0", "1");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "2", "3");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "1", "2");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "1", "2");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "3", "4");
		swapper.swapItems(currUser, "DO_NOT_SHOW", "4","3");
		
		assertEquals(currUser.getDoNotShow().get(3),r3);
		assertEquals(currUser.getDoNotShow().get(1),r1);
		
		currUser.reset();
	}

}