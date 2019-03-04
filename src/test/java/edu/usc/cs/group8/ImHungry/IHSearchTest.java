package edu.usc.cs.group8.ImHungry;

import java.util.ArrayList;

import org.junit.Test;

public class IHSearchTest {

	@Test
	public void test() {
		IHSearch search = new IHSearch();
		
//		ArrayList<Recipe> stewArray = search.doRecipeSearch("Stew", "10");
//		search.sortRecipes(stewArray);
//		System.out.println(stewArray);
		
//		System.out.println(search.doImageSearch("Hamburger"));
//		
//		System.out.println(search.doRecipeSearch("Pizza", "10"));
		
		ArrayList<Restaurant> rests = search.doRestaurantSearch("Stew", "30");
		for (int i =0; i<rests.size();i++) {
			System.out.println(rests.get(i).getName() 
					+ "\n  phoneNum=" + rests.get(i).getPhoneNum()
					+ "\n  drivetime="+ rests.get(i).getDriveTime()  
					+ "\n  rating=" + rests.get(i).getRating() 
					+ "\n  price_level=" + rests.get(i).getPriceRange()
					+ "\n  website=" + rests.get(i).getWebsiteURL()
					+ "\n  address=" + rests.get(i).getAddress()
					);
		}
	}

}
