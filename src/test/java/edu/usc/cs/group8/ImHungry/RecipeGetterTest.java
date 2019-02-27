package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.Test;

import junit.framework.Assert;

public class RecipeGetterTest {

	@Test
	public void recipeGetterTest() {
		String harissaJson = RecipeGetter.readRecipe("https://www.thespruceeats.com/classic-moroccan-harira-soup-2394920");
		System.out.println(harissaJson);
		assertEquals(harissaJson.charAt(0), '{');
		assertEquals(harissaJson.charAt(harissaJson.length()-1), '}');
	}
	
	@Test
	public void errorTest() {
		String badJson = RecipeGetter.readRecipe("fart");
	}

}
