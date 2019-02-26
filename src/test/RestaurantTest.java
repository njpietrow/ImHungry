package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import classes.Recipe;
import classes.Restaurant;

class RestaurantTest {
	
	public static Restaurant restaurant = new Restaurant("Food", 20, 10.0, "food.com", "123 Main Street", "555-555-5585", 1);

	@Test
	void testConstructor() {
		Restaurant myRestaurant = new Restaurant("Food", 20, 10.0, "food.com", "123 Main Street", "555-555-5585", 1);
		assertEquals(myRestaurant.getName(),"Food");
		assertEquals(myRestaurant.getDriveTime(),20);
		assertEquals(myRestaurant.getDistance(),10.0,0.1);
		assertEquals(myRestaurant.getWebsiteURL(),"food.com");
		assertEquals(myRestaurant.getAddress(),"123 Main Street");
		assertEquals(myRestaurant.getPhoneNum(),"555-555-5585");
		assertEquals(myRestaurant.getPriceRange(),1);
	}
	
	@Test
	void testName() {
		restaurant.setName("Spaghetti");
		assertEquals(restaurant.getName(),"Spaghetti");
	}
	
	@Test
	void testDriveTime() {
		restaurant.setDriveTime(50);
		assertEquals(restaurant.getDriveTime(),50);
	}

	@Test
	void testDistance() {
		restaurant.setDistance(60.0);
		assertEquals(restaurant.getDistance(),60.0,0.1);
	}
	
	@Test
	void testWebsiteURL() {
		restaurant.setWebsiteURL("spaghetti.png");
		assertEquals(restaurant.getWebsiteURL(),"spaghetti.png");
	}
	
	@Test
	void testAddress() {
		restaurant.setAddress("P. Sherman 42 Wallaby Way Sydney");
		assertEquals(restaurant.getAddress(),"P. Sherman 42 Wallaby Way Sydney");
	}
	
	@Test
	void testPhoneNum() {
		restaurant.setPhoneNum("800-588-2300");
		assertEquals(restaurant.getPhoneNum(),"800-588-2300");
	}
	
	@Test
	void testPriceRange() {
		restaurant.setPriceRange(4);
		assertEquals(restaurant.getPriceRange(),4);
	}
}
