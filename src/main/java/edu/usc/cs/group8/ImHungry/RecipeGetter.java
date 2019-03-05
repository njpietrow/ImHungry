package edu.usc.cs.group8.ImHungry;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * RecipeGetter.java
 * This class goes to websites and returns recipe objects from them. :)
 * Author: Kevin Calaway
 * USC ID: 9724507315
 * Email: calaway@usc.edu
 */
public class RecipeGetter {
	
	/*
	 * This function takes in a web address as an input, and tries to return
	 * a JSON containing a recipe from the website. If there is no JSON on the
	 * website, it returns null.
	 */
	public static String readRecipe(String url) {
		
		/*
		 * This code snippet taken from https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
		 * to parse an HTML file in Java.
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
		}catch ( Exception ex ) {
		    return null;
		}
		
		/*
		 * These are flags we will use to identify where the Json has started
		 * where it ends, and if it exists
		 */
		int jsonStart = 0;
		int jsonEnd = 0;
		boolean jsonFlag = false;
		String recipeJson = "";
		
		/*
		 * Every recipe json is of "@type":"Recipe" and runs until the closing HTML tag that begins <\
		 * So we look for that chunk of text in a website
		 * 
		 */
		for (int i = 0; i < content.length() - 16; i++) {
			if (content.substring(i, i+16).equals("\"@type\":\"Recipe\"") ||
			    content.substring(i, i+16).equals("\"@type\": \"Recipe") ) {
				for (int j = i; j > 0; j--) {
					if (content.charAt(j) == '{') {
						jsonStart = j;
						jsonFlag = true;
						break;
					}
				}
			}
			if (content.substring(i, i+2).equals("</") && jsonFlag) {
				while (content.charAt(i-1) != '}' && i > jsonStart) {
					i--;
				}
				jsonEnd = i;
				break;
			}
		}
		return content.substring(jsonStart,jsonEnd);
	}

	/*
	 * This function takes the JSON file the website created and parses it
	 * according to https://developers.google.com/search/docs/data-types/recipe.
	 * A few options are provided for instructions and ingredients due to differences
	 * in how those are presented by the webmasters of the recipe websites.
	 * 
	 * It then returns a Recipe object with all relevant information for
	 * I'm Hungry.
	 */
	public static Recipe parseRecipe(String recipe) {
		JsonParser parser = new JsonParser();
		JsonObject bigRecipe;
		try{
			bigRecipe = parser.parse(recipe).getAsJsonObject();
		} catch (Exception e) {
			return null;
		}
		String name, prepTime, cookTime, img;
		try {
			name = bigRecipe.get("name").getAsString();
		} catch (Exception e) {
			name = "Yummy food.";
		}
		try {
			prepTime = bigRecipe.get("prepTime").getAsString();
		} catch (Exception e) {
			prepTime = "Unknown prep time.";
		}
		try {
			cookTime = bigRecipe.get("cookTime").getAsString();
		} catch (Exception e) {
			cookTime = "Unknown cook time.";
		}
		try {
			img = bigRecipe.get("image").getAsJsonArray().get(0).getAsString();
		} catch (Exception e) {
			img = "";
		}
		
		JsonArray jsonIngredients, jsonInstructions; 
		try{
			jsonIngredients = bigRecipe.get("recipeIngredient").getAsJsonArray();
		} catch (Exception e) {
			jsonIngredients = new JsonArray();
			try {
				jsonIngredients.add(bigRecipe.get("recipeIngredient"));
			} catch (Exception f) {
				jsonIngredients.add("No ingredients available.");
			}
		}
		try {
			jsonInstructions = bigRecipe.get("recipeInstructions").getAsJsonArray();
		} catch (Exception e) {
			jsonInstructions = new JsonArray();
			try {
				jsonInstructions.add(bigRecipe.get("recipeInstructions"));
			} catch (Exception f) {
				jsonInstructions.add("No instructions available.");
			}
		}
		
		ArrayList<String> ingredients = new ArrayList<String>();
		for (int i = 0; i < jsonIngredients.size(); i++) {
			try {
				ingredients.add(jsonIngredients.get(i).getAsString());
			}
			catch (Exception e) {
				ingredients.add("Ingredients could not be parsed.");
			}
		}
		ArrayList<String> instructions = new ArrayList<String>();
		for (int i = 0; i < jsonInstructions.size(); i++) {
			try {
				instructions.add(jsonInstructions.get(i).getAsString());
			}
			catch (Exception e) {
				try {
					instructions.add(jsonInstructions.get(i).getAsJsonObject().get("text").getAsString());
				}
				catch (Exception f) {
					instructions.add("Instructions could not be parsed.");
					break;
				}
			}
		}
		return new Recipe(name,prepTime,cookTime,img,ingredients,instructions);
	}
	
	
}
