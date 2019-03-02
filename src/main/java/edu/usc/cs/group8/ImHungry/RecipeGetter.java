package edu.usc.cs.group8.ImHungry;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RecipeGetter {
	
	public static String readRecipe(String url) {
		
		/*
		 * This code snippet taken from https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
		 */
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		  scanner.close();
		}catch ( Exception ex ) {
		    return null;
		}
		
		int jsonStart = 0;
		int jsonEnd = 0;
		boolean jsonFlag = false;
		String recipeJson = "";
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
				while (content.charAt(i-1) != '}') {
					i--;
				}
				jsonEnd = i;
				break;
			}
		}
		return content.substring(jsonStart,jsonEnd);
	}

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
