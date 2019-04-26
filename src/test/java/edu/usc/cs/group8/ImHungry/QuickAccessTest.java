package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickAccessTest {

	@Test
	public void testQuickAccessAddition() {
		LoginHelper helper = new LoginHelper(); 
		User currUser = new User(); 
		helper.login("GJHalfond","Scrum",currUser);
		IHSearch searchy = new IHSearch(); 
		currUser.addToQuickAccess(new Query("Curry", "3","5000")); 
		assertTrue(currUser.getLists().quickAccessContains(new Query("Curry","3", "5000")));
	}
	
	@Test
	public void testQuickAccessAccess() {
		LoginHelper helper = new LoginHelper(); 
		User currUser = new User(); 
		helper.login("GJHalfond","Scrum",currUser);
		IHSearch searchy = new IHSearch(); 
		currUser.addToQuickAccess(new Query("Curry", "3","5000")); 
		assertTrue(currUser.getLists().getQuickAccess().get(0).equals(new Query("Curry","3", "5000")));
	}

}