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
		RestaurantGetter RG  = Mockito.spy(new RestaurantGetter());

		Restaurant r = mock(Restaurant.class);
		r.setName("Soy Roll & Katsu");
		r.setId("123abc");
		String url = "https://maps.googleapis.com/maps/api/place/details/json?"
				+ "placeid=" + r.getId()
				+ "&fields=name,rating,formatted_phone_number,formatted_address,website,price_level"
				+ "&key=AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA\n";
		
		doReturn("").when(RG).readWebsite(url);
		r = getter.getContactInfo(r);
	}
	
	@Test
	public void restaurantGetterTestNormal() {    
		Restaurant r = mock(Restaurant.class);
		r.setName("Soy Roll & Katsu");
		r.setId("123abc");
		String url = "https://maps.googleapis.com/maps/api/place/details/json?"
				+ "placeid=" + r.getId()
				+ "&fields=name,rating,formatted_phone_number,formatted_address,website,price_level"
				+ "&key=AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA\n";
		
		when(getter.readWebsite(url)).thenReturn("");
		JSONObject mainObj = mock(JSONObject.class);
		when(mainObj.get("result")).thenReturn(null);
		

		
		//when();
		r = getter.getContactInfo(r);

		assertEquals(r.getRating(),4.5,0.001);
		assertEquals(r.getAddress(),"5, 48 Pirrama Rd, Pyrmont NSW 2009, Australia");
		assertEquals(r.getPhoneNum(),"(02) 9374 4000");
		assertEquals(r.getWebsiteURL(),"https://www.google.com.au/about/careers/locations/sydney/");
	}
	
}
