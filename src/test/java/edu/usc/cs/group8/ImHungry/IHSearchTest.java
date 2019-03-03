package edu.usc.cs.group8.ImHungry;

import java.util.ArrayList;

import org.junit.Test;

public class IHSearchTest {

	@Test
	public void test() {
		IHSearch search = new IHSearch();
		
		ArrayList<Recipe> stewArray = search.doRecipeSearch("Stew", "10");
		search.sortRecipes(stewArray);
		System.out.println(stewArray);
		
		System.out.println(search.doImageSearch("Hamburger"));
		
		System.out.println(search.doRecipeSearch("Pizza", "10"));
	}

}
