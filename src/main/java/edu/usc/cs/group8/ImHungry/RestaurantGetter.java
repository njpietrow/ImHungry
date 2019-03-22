package edu.usc.cs.group8.ImHungry;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * RestaurantGetter.java
 * This class gets additional information from Google place restaurants
 * Author: Nick Pietrow 
 * USC ID: 5425773820
 * Email: pietrow@usc.edu
 */
public class RestaurantGetter {
	
	/* 
	 * this method gets all parameters except for driving time from Google Place Detail Search
	 */
	public static Restaurant getContactInfo(Restaurant r) {
		String url = "https://maps.googleapis.com/maps/api/place/details/json?"
				+ "placeid=" + r.getId()
				+ "&fields=name,rating,formatted_phone_number,formatted_address,website,price_level"
				+ "&key=AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA\n";

			String json_string = readWebsite(url);

			if (json_string == null) return null; 

			//Parse the JSON object to retrieve necessary Restaurant info
			JSONObject mainObj= new JSONObject(json_string);
			JSONObject result = (JSONObject) mainObj.get("result");
			
			String address = "",phoneNum = "",website = "";
			int price_level = -1;
			double rating = -1;
			
			if (result.has("formatted_address")) {
				address = result.getString("formatted_address");
		     }
			if (result.has("formatted_phone_number")) {
				phoneNum = result.getString("formatted_phone_number");
		     }
			if (result.has("website")) {
				website = result.getString("website");
		     }
			if (result.has("price_level")) {
				price_level = result.getInt("price_level");
			}
			if (result.has("rating")) {
				rating = result.getDouble("rating");
			}
			
			//set restaurant attributes taken from JSON file
			r.setAddress(address);
			r.setPhoneNum(phoneNum);
			r.setWebsiteURL(website);
			r.setPriceRange(price_level);
			r.setRating(rating);
			String name = r.getName();
			name = name.replaceAll(" ", "+");
			name = name.replaceAll(",", "");
			name = name.replaceAll("#", "");
			r.setMapURL("https://www.google.com/maps/dir/?api=1&origin=" 
					+ "801+Childs+Way+Los+Angeles+CA"
					+ "&destination="
					+ name
					+ "&destination_place_id="
					+ r.getId()
					+ "&travelmode=driving"
					);
		return r;
	}
	
	/*
	 * This method gets the driving time from Tommy Trojan to the Restaurant
	 * Uses the Google Distance Matrix API
	 */
	public static Restaurant getDriveTime(Restaurant r) {
		String address = r.getAddress();
		address = address.replaceAll(" ", "+");
		address = address.replaceAll(",", "");
		address = address.replaceAll("#", "");
		
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?"
				+ "units=imperial"
				+ "&origins=801+Childs+Way+Los+Angeles"
				+ "&destinations=" + address
				+ "&key=AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA\n";
		
			String json_string = readWebsite(url);
			if (json_string == null) return null;
			
			JSONObject mainObj= new JSONObject(json_string);
			
			JSONArray dist=(JSONArray)mainObj.get("rows");
	        JSONObject obj2 = (JSONObject)dist.get(0);
	        JSONArray disting=(JSONArray)obj2.get("elements");
	        JSONObject obj3 = (JSONObject)disting.get(0);
	        JSONObject obj5=(JSONObject)obj3.get("duration");
	        
			int driving_time = 0;
			driving_time = obj5.getInt("value");
			driving_time = driving_time/60;
			
			//set driving time attribute taken from JSON file
			r.setDriveTime(driving_time);
			
		return r;
	}
	public static String readWebsite(String url) {
		/*
		 * Copied from StackOverflow and from https://community.oracle.com/thread/1691281
		 */
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  connection.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		  scanner.close();
		} catch ( Exception ex ) {
		    return null;
		}
		return content;
	}

}
