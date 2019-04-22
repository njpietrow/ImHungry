package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListReorderTest {

	@Test
	public void testSwap() {
		User myUser = new User("GJHalfond");
		myUser.reset();
		
		myUser.addToFavorites(myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		myUser.addToFavorites(myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		assertEquals(myUser.getFavorites().get(0),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getFavorites().get(1),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapFavorites(0,1);
		assertEquals(myUser.getFavorites().get(1),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getFavorites().get(0),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapFavorites(0,1);
		assertEquals(myUser.getFavorites().get(0),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getFavorites().get(1),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		
		myUser.reset();
		
		myUser.addToToExplore(myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		myUser.addToToExplore(myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		assertEquals(myUser.getToExplore().get(0),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getToExplore().get(1),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapToExplore(0,1);
		assertEquals(myUser.getToExplore().get(1),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getToExplore().get(0),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapToExplore(0,1);
		assertEquals(myUser.getToExplore().get(0),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getToExplore().get(1),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		
		myUser.reset();
		
		myUser.addToDoNotShow(myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		myUser.addToDoNotShow(myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		assertEquals(myUser.getDoNotShow().get(0),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getDoNotShow().get(1),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapDoNotShow(0,1);
		assertEquals(myUser.getDoNotShow().get(1),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getDoNotShow().get(0),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
		myUser.swapDoNotShow(0,1);
		assertEquals(myUser.getDoNotShow().get(0),myUser.get("http://localhost:8080/ImHungry/testrecipe.html"));
		assertEquals(myUser.getDoNotShow().get(1),myUser.get("ChIJwajStEu5woAR9O3sDMuI1UM"));
	}

}
