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
		Recipe gross = user.getRecipe("http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList(gross);
		assertTrue(user.getLists().groceryListContains("Like 48 red hots"));
		assertFalse(user.getLists().groceryListContains("Green beans"));
		ArrayList<String> moreIngredients = new ArrayList<String>();
		moreIngredients.add("Green beans");
		Recipe greenBeans = user.getRecipe("http://localhost:8080/ImHungry/testrecipe2.html");
		assertFalse(user.getLists().groceryListContains("Green beans"));
		user.getLists().addToGroceryList(greenBeans);
		assertTrue(user.getLists().groceryListContains("Green beans"));
		assertTrue(user.getLists().groceryListContains("Like 48 red hots"));
	}
	
	@Test
	public void removeFromListTest() {
		User user = new User("GJHalfond");
		user.clearGroceryList();
		ArrayList<String> ingredients = new ArrayList<String>();
		Recipe emptyRecipe = new Recipe("Empty","0","0","",new ArrayList<String>(), new ArrayList<String>());
		user.removeFromGroceryList("Not an ingredient.");
		user.removeFromGroceryList(RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html")));
		user.addToGroceryList("Delete this.","http://localhost:8080/ImHungry/testrecipe.html");
		user.removeFromGroceryList(emptyRecipe);
		user.removeFromGroceryList(RecipeGetter.parseRecipe(RecipeGetter.readRecipe("http://localhost:8080/ImHungry/testrecipe.html")));
		user.addToGroceryList("Bad1","http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList("Bad2","http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList("Bad3","http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList("Bad4","http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList("Bad5","http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList("Bad6","http://localhost:8080/ImHungry/testrecipe.html");
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
		Recipe gross = user.getRecipe("http://localhost:8080/ImHungry/testrecipe.html");
		user.getLists().addToGroceryList(gross);
		gross.setURL("http://localhost:8080/ImHungry/testrecipe.html");
		ArrayList<String> moreIngredients = new ArrayList<String>();
		moreIngredients.add("Green beans");
		moreIngredients.add("Like 48 red hots");
		Recipe greenBeans = user.getRecipe("http://localhost:8080/ImHungry/testrecipe2.html");
		user.getLists().addToGroceryList(greenBeans);
		System.out.println(user.getGroceries());
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
		System.out.println(user.getGroceries());
		user.removeFromGroceryList("A whole bunch of flour");
		System.out.println(user.getGroceries());
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
		Recipe gross = user.getRecipe("http://localhost:8080/ImHungry/testrecipe.html");
		user.addToGroceryList(gross);
		ArrayList<String> moreIngredients = new ArrayList<String>();
		moreIngredients.add("Green beans");
		moreIngredients.add("Like 48 red hots");

		Recipe greenBeans = user.getRecipe("http://localhost:8080/ImHungry/testrecipe2.html");
		user.getLists().addToGroceryList(greenBeans);
		user.getLists().clearGroceryList();
		assertTrue(user.getLists().getGroceries().size() == 0);
	}

}
