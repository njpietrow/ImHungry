package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GroceryListTest {

	@Test
	public void addToListTest() {
		User user = new User("Buster",new ListManager());
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("10 eggs");
		ingredients.add("A whole bunch of flour");
		ingredients.add("Like 48 red hots");
		ingredients.add("A dollop of Daisy");
		ingredients.add("A can of chicken");
		ingredients.add("An entire bottle of Sriracha-brand sriracha");
		Recipe gross = new Recipe("Gross Recipe","10","15","https://i.imgur.com/bjnLBdq.jpg",ingredients,new ArrayList<String>());
		user.getLists().addToGroceryList(gross);
		assertTrue(user.getLists().groceryListContains("Like 48 red hots"));
		assertFalse(user.getLists().groceryListContains("Green beans"));
		ArrayList<String> moreIngredients = new ArrayList<String>();
		moreIngredients.add("Green beans");
		Recipe greenBeans = new Recipe("Gross Recipe","10","15","https://i.imgur.com/bjnLBdq.jpg",moreIngredients,new ArrayList<String>());
		assertFalse(user.getLists().groceryListContains("Green beans"));
		user.getLists().addToGroceryList(greenBeans);
		assertTrue(user.getLists().groceryListContains("Green beans"));
		assertTrue(user.getLists().groceryListContains("Like 48 red hots"));
	}
	
	@Test
	public void removeFromListTest() {
		User user = new User("Buster",new ListManager());
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("10 eggs");
		ingredients.add("A whole bunch of flour");
		ingredients.add("Like 48 red hots");
		ingredients.add("A dollop of Daisy");
		ingredients.add("A can of chicken");
		Recipe gross = new Recipe("Gross Recipe","10","15","https://i.imgur.com/bjnLBdq.jpg",ingredients,new ArrayList<String>());
		user.getLists().addToGroceryList(gross);
		ArrayList<String> moreIngredients = new ArrayList<String>();
		moreIngredients.add("Green beans");
		moreIngredients.add("Like 48 red hots");
		Recipe greenBeans = new Recipe("Gross Recipe","10","15","https://i.imgur.com/bjnLBdq.jpg",moreIngredients,new ArrayList<String>());
		user.getLists().addToGroceryList(greenBeans);
		assertTrue(user.getLists().groceryListContains("Green beans"));
		assertTrue(user.getLists().groceryListContains("Like 48 red hots"));
		user.getLists().removeFromGroceryList(greenBeans);
		assertFalse(user.getLists().groceryListContains("Green beans"));
		assertTrue(user.getLists().groceryListContains("Like 48 red hots"));
		assertTrue(user.getLists().groceryListContains("A can of chicken"));
		user.getLists().removeFromGroceryList("A can of chicken");
		assertFalse(user.getLists().groceryListContains("A can of chicken"));
		assertTrue(user.getLists().groceryListContains("A whole bunch of flour"));
		
	}
	
	@Test
	public void clearListTest() {
		User user = new User("Buster",new ListManager());
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("10 eggs");
		ingredients.add("A whole bunch of flour");
		ingredients.add("Like 48 red hots");
		ingredients.add("A dollop of Daisy");
		ingredients.add("A can of chicken");
		Recipe gross = new Recipe("Gross Recipe","10","15","https://i.imgur.com/bjnLBdq.jpg",ingredients,new ArrayList<String>());
		user.getLists().addToGroceryList(gross);
		ArrayList<String> moreIngredients = new ArrayList<String>();
		moreIngredients.add("Green beans");
		moreIngredients.add("Like 48 red hots");
		Recipe greenBeans = new Recipe("Gross Recipe","10","15","https://i.imgur.com/bjnLBdq.jpg",moreIngredients,new ArrayList<String>());
		user.getLists().addToGroceryList(greenBeans);
		user.getLists().clearGroceryList();
		assertTrue(user.getLists().getGroceries().size() == 0);
	}

}
