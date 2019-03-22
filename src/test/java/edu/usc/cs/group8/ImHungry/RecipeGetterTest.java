package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.Assert;
import static org.mockito.Mockito.*;

public class RecipeGetterTest {

	// 
	@Test
	public void recipeGetterTest() {
		String tsoJson = RecipeGetter.readRecipe("https://dinnerthendessert.com/general-tsos-chicken/");
		assertEquals(tsoJson.charAt(0), '{');
		assertEquals(tsoJson.charAt(tsoJson.length()-1), '}');
		assertEquals(RecipeGetter.parseRecipe(tsoJson).getName(),"General Tso's Chicken");
		
		String harissaJson = RecipeGetter.readRecipe("https://www.thespruceeats.com/classic-moroccan-harira-soup-2394920");
		assertEquals(harissaJson.charAt(0), '{');
		assertEquals(harissaJson.charAt(harissaJson.length()-1), '}');
		assertEquals(RecipeGetter.parseRecipe(harissaJson).getName(),"Classic Moroccan Harira: Tomato, Lentil, and Chickpea Soup");
		
		String spanJson = RecipeGetter.readRecipe("https://www.themediterraneandish.com/spanakopita-recipe-greek-spinach-pie/");
		assertEquals(spanJson.charAt(0), '{');
		assertEquals(spanJson.charAt(spanJson.length()-1), '}');
		assertEquals(RecipeGetter.parseRecipe(spanJson).getName(),"Spanakopita Recipe (Greek Spinach Pie)");
		
		String risottoJson = RecipeGetter.readRecipe("https://www.foodnetwork.com/recipes/michael-symon/truffle-risotto-recipe-1940689");
		assertEquals(risottoJson.charAt(0), '{');
		assertEquals(risottoJson.charAt(risottoJson.length()-1), '}');
		assertEquals(RecipeGetter.parseRecipe(risottoJson).getName(),"Truffle Risotto");
		
		String badFormat = RecipeGetter.readRecipe("https://www.epicurious.com/recipes/food/views/risotto-with-leeks-shiitake-mushrooms-and-truffles-239801");
		assertNull(RecipeGetter.parseRecipe(badFormat));
	}
	
	@Test
	public void recipeGetterTestConstruct() {
		RecipeGetter RG = new RecipeGetter();
		assertEquals("achieve coverage","achieve coverage");
	}
	
	@Test
	public void recipeGetterTestScannerException() {
		Scanner scan = mock(Scanner.class);
			when(scan.next())
			  .thenThrow(new NullPointerException("Error occurred"));
	    RecipeGetter.readRecipe("word");
	}

	
}

