package edu.usc.cs.group8.ImHungry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestaurantGetter {
	
	public static Restaurant getContactInfo(Restaurant r) {
		String url = "https://maps.googleapis.com/maps/api/place/details/json?"
				+ "placeid=" + r.getId()
				+ "&fields=name,rating,formatted_phone_number,formatted_address,website,price_level"
				+ "&key=AIzaSyCe6MRPk3bmzAC476OWtgbH91rJ8hWwRyA\n";

		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine; 
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			String json_string = response.toString();
//			System.out.println(json_string);

			//Parse the JSON object to retrieve necessary Restaurant info
			JSONObject mainObj= new JSONObject(json_string);
			JSONObject result = (JSONObject) mainObj.get("result");
			String address = "",phoneNum = "",website = "";
			int price_level = -1;
			double rating = -1;
			
			if (result.has("formatted_address") && !result.isNull("formatted_address")) {
				address = result.getString("formatted_address");
		     }
			if (result.has("formatted_phone_number") && !result.isNull("formatted_phone_number")) {
				phoneNum = result.getString("formatted_phone_number");
		     }
			if (result.has("website") && !result.isNull("website")) {
				website = result.getString("website");
		     }
			if (result.has("price_level") && !result.isNull("price_level")) {
				price_level = result.getInt("price_level");
			}
			if (result.has("rating") && !result.isNull("rating")) {
				rating = result.getDouble("rating");
			}
	
			r.setAddress(address);
			r.setPhoneNum(phoneNum);
			r.setWebsiteURL(website);
			r.setPriceRange(price_level);
			r.setRating(rating);

		} 
		catch(Exception ex) {
			System.out.println("exception");
		}
		return r;
	}
	
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
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine; 
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			String json_string = response.toString();
//			System.out.println(json_string);
			
			JSONObject mainObj= new JSONObject(json_string);
			
			JSONArray dist=(JSONArray)mainObj.get("rows");
	        JSONObject obj2 = (JSONObject)dist.get(0);
	        JSONArray disting=(JSONArray)obj2.get("elements");
	        JSONObject obj3 = (JSONObject)disting.get(0);
	        JSONObject obj5=(JSONObject)obj3.get("duration");
	        
			int driving_time = 0;
			driving_time = obj5.getInt("value");
			driving_time = driving_time/60;
			
			
			r.setDriveTime(driving_time);
			
			
		} catch(Exception ex) {
			System.out.println("exception");
		}
		return r;
	}

}
