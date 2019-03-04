package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RestaurantTest {
	
	public static Restaurant restaurant = new Restaurant("Food", 20, "food.com", "123 Main Street", "555-555-5585", 1);

	@Test
	public void testConstructor() {
		Restaurant myRestaurant = new Restaurant("Food", 20, "food.com", "123 Main Street", "555-555-5585", 1);
		assertEquals(myRestaurant.getName(),"Food");
		assertEquals(myRestaurant.getDriveTime(),20);
		assertEquals(myRestaurant.getWebsiteURL(),"food.com");
		assertEquals(myRestaurant.getAddress(),"123 Main Street");
		assertEquals(myRestaurant.getPhoneNum(),"555-555-5585");
		assertEquals(myRestaurant.getPriceRange(),1);
	}
	
	@Test
	public void testName() {
		restaurant.setName("Spaghetti");
		assertEquals(restaurant.getName(),"Spaghetti");
	}
	
	@Test
	public void testDriveTime() {
		restaurant.setDriveTime(50);
		assertEquals(restaurant.getDriveTime(),50);
	}

	
	@Test
	public void testWebsiteURL() {
		restaurant.setWebsiteURL("spaghetti.png");
		assertEquals(restaurant.getWebsiteURL(),"spaghetti.png");
	}
	
	@Test
	public void testAddress() {
		restaurant.setAddress("P. Sherman 42 Wallaby Way Sydney");
		assertEquals(restaurant.getAddress(),"P. Sherman 42 Wallaby Way Sydney");
	}
	
	@Test
	public void testPhoneNum() {
		restaurant.setPhoneNum("800-588-2300");
		assertEquals(restaurant.getPhoneNum(),"800-588-2300");
	}
	
	@Test
	public void testPriceRange() {
		restaurant.setPriceRange(4);
		assertEquals(restaurant.getPriceRange(),4);
	}
}
