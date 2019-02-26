package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import classes.Recipe;

class RecipeTest {
	
	public static Recipe recipe = new Recipe("Food", 20, 10, "food.jpg", new ArrayList<String>(10), new ArrayList<String>(5));
	
	@Test
	void testConstructor() {
		Recipe myRecipe = new Recipe("Food", 20, 10, "food.jpg", new ArrayList<String>(10), new ArrayList<String>(5));
		assertEquals(myRecipe.getName(),"Food");
		assertEquals(myRecipe.getPrepTime(),20);
		assertEquals(myRecipe.getCookTime(),10);
		assertEquals(myRecipe.getImgURL(),"food.jpg");
		assertEquals(myRecipe.getIngredients().size(),0);
		assertEquals(myRecipe.getInstructions().size(),0);
	}
	
	@Test
	void testName() {
		recipe.setName("Spaghetti");
		assertEquals(recipe.getName(),"Spaghetti");
	}
	
	@Test
	void testPrepTime() {
		recipe.setPrepTime(50);
		assertEquals(recipe.getPrepTime(),50);
	}

	@Test
	void testCookTime() {
		recipe.setCookTime(60);
		assertEquals(recipe.getCookTime(),60);
	}
	
	@Test
	void testImgURL() {
		recipe.setImgURL("spaghetti.png");
		assertEquals(recipe.getImgURL(),"spaghetti.png");
	}
	
	@Test
	void testIngredients() {
		recipe.getIngredients().add("Spaghetti");
		recipe.getIngredients().add("Meatballs");
		assertEquals(recipe.getIngredients().size(),2);
		assertEquals(recipe.getIngredients().get(0),"Spaghetti");
		assertEquals(recipe.getIngredients().get(1),"Meatballs");
	}
	
	@Test
	void testInstructions() {
		recipe.getInstructions().add("Mix thoroughly.");
		recipe.getInstructions().add("Serve warm.");
		assertEquals(recipe.getInstructions().size(),2);
		assertEquals(recipe.getInstructions().get(0),"Mix thoroughly.");
		assertEquals(recipe.getInstructions().get(1),"Serve warm.");
	}
}
