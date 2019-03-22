package edu.usc.cs.group8.ImHungry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

public class RestaurantGetterTest {
	
	public static Restaurant restaurant = mock(Restaurant.class);
	public static RestaurantGetter getter = mock(RestaurantGetter.class);

	@Test
	public void restaurantGetterTestJsonNull() {

		Restaurant r = new Restaurant();
		r.setName("Soy Test");
		String SoyId = "ChIJj2qdPuHHwoARihm6qZwi0Gg";
		r.setId(SoyId);
		r = RestaurantGetter.getContactInfo(r);
		assertEquals("3335 S Figueroa St, Los Angeles, CA 90007, USA",r.getAddress());
		
	}
	
	@Test
	public void restaurantGetterTestNormal() {    
		Restaurant r = new Restaurant();
		r.setName("Ebaes Test");
		String EbaesId = "ChIJn7GRXOzHwoARNOHThxF8Kp4";
		r.setId(EbaesId);
		r = RestaurantGetter.getContactInfo(r);
		assertEquals("(213) 747-6888",r.getPhoneNum());
	}
	


	
	
}
