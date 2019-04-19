package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GroceryListTest {

	@Test
	public void addToListTest() {
		User user = new User("GJHalfond");
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
		User user = new User("GJHalfond");
		ArrayList<String> ingredients = new ArrayList<String>();
		Recipe emptyRecipe = new Recipe("Empty","0","0","",new ArrayList<String>(), new ArrayList<String>());
		user.removeFromGroceryList("Not an ingredient.");
		user.removeFromGroceryList(RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html")));
		user.addToGroceryList("Delete this.");
		user.removeFromGroceryList(emptyRecipe);
		user.removeFromGroceryList(RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html")));
		user.addToGroceryList("Bad1");
		user.addToGroceryList("Bad2");
		user.addToGroceryList("Bad3");
		user.addToGroceryList("Bad4");
		user.addToGroceryList("Bad5");
		user.addToGroceryList("Bad6");
		user.removeFromGroceryList(RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html")));
		user.removeFromGroceryList("Not an ingredient.");
		user.removeFromGroceryList("Delete this.");
		user.removeFromGroceryList("Bad1");
		user.removeFromGroceryList("Bad2");
		user.removeFromGroceryList("Bad3");
		user.removeFromGroceryList("Bad4");
		user.removeFromGroceryList("Bad5");
		user.removeFromGroceryList("Bad6");
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
		user.addToGroceryList(gross);
		user.removeFromGroceryList("A whole bunch of flour");
		assertFalse(user.getLists().groceryListContains("A whole bunch of flour"));
		
	}
	
	@Test
	public void clearListTest() {
		User user = new User("GJHalfond");
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
