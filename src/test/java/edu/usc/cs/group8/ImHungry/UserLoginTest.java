package edu.usc.cs.group8.ImHungry;

import org.junit.Test;

public class UserLoginTest {
	
	@Test
	public void createNewAccount()
	{
		LoginHelper helper = new LoginHelper();
		assertTrue(helper.createAccount("KevinCalaway","Doopadoop"));
		assertFalse(helper.createAccount("KevinCalaway","Boopadoop"));
	}
		
	@Test
	public void passwordIncorrect()
	{
		String username = "GJHalfond";
		String wrongPassword = "Waterfall";
		LoginHelper helper = new LoginHelper();
		helper.createAccount("GJHalfond","Scrum");
		User currUser = new User();
		assertFalse(helper.login(username,wrongPassword,currUser));
	}
	
	@Test
	public void passwordCorrect()
	{
		String username = "GJHalfond";
		String rightPassword = "Scrum";
		LoginHelper helper = new LoginHelper();
		helper.createAccount("GJHalfond","Scrum");
		User currUser = new User();
		assertTrue(helper.login(username,rightPassword,currUser));
	}
	
	

}
