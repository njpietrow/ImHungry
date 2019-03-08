package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class RecipeTest {
	
	public static Recipe recipe = new Recipe("Food", "20", "10", "food.jpg", new ArrayList<String>(10), new ArrayList<String>(5));
	
	@Test
	public void testConstructor() {
		Recipe myRecipe = new Recipe("Food", "20", "10", "food.jpg", new ArrayList<String>(10), new ArrayList<String>(5));
		assertEquals(myRecipe.getName(),"Food");
		assertEquals(myRecipe.getPrepTime(),20);
		assertEquals(myRecipe.getCookTime(),10);
		assertEquals(myRecipe.getImgURL(),"food.jpg");
		assertEquals(myRecipe.getIngredients().size(),0);
		assertEquals(myRecipe.getInstructions().size(),0);
	}
	
	@Test
	public void testName() {
		recipe.setName("Spaghetti");
		assertEquals(recipe.getName(),"Spaghetti");
	}
	
	@Test
	public void testPrepTime() {
		recipe.setPrepTime("PT50M");
		assertEquals(recipe.getPrepTime(),50);
	}

	@Test
	public void testCookTime() {
		recipe.setCookTime("PT1H");
		assertEquals(recipe.getCookTime(),60);
	}
	
	@Test
	public void testImgURL() {
		recipe.setImgURL("spaghetti.png");
		assertEquals(recipe.getImgURL(),"spaghetti.png");
	}
	
	@Test
	public void testIngredients() {
		recipe.setIngredients(new ArrayList<String>());
		recipe.getIngredients().add("Spaghetti");
		recipe.getIngredients().add("Meatballs");
		assertEquals(recipe.getIngredients().size(),2);
		assertEquals(recipe.getIngredients().get(0),"Spaghetti");
		assertEquals(recipe.getIngredients().get(1),"Meatballs");
	}
	
	@Test
	public void testInstructions() {
		recipe.setInstructions(new ArrayList<String>());
		recipe.getInstructions().add("Mix thoroughly.");
		recipe.getInstructions().add("Serve warm.");
		assertEquals(recipe.getInstructions().size(),2);
		assertEquals(recipe.getInstructions().get(0),"Mix thoroughly.");
		assertEquals(recipe.getInstructions().get(1),"Serve warm.");
	}
	
	@Test
	public void testToString() {
		recipe.setInstructions(new ArrayList<String>());
		recipe.getInstructions().add("Mix thoroughly.");
		recipe.getInstructions().add("Serve warm.");
		assertEquals(recipe.toString(),"Spaghetti\n" + 
				"20\n" + 
				"10\n" + 
				"[]\n" + 
				"[Mix thoroughly., Serve warm.]\n");
	}
}
