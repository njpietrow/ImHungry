package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.*;

import org.junit.Test;

public class IHSearchTest {

	@Test
	public void test() {
		IHSearch search = new IHSearch();
		System.out.println(search.doImageSearch("Hamburger"));
		System.out.println(search.doRecipeSearch("Pizza", "10"));
	}

}
