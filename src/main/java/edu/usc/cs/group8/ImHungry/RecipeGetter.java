package edu.usc.cs.group8.ImHungry;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

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
		    ex.printStackTrace();
		}
		
		int jsonStart = 0;
		int jsonEnd = 0;
		boolean jsonFlag = false;
		String recipeJson = "";
		for (int i = 0; i < content.length() - 8; i++) {
			if (content.substring(i, i+8).equals("\"Recipe\"")) {
				for (int j = i; j > 0; j--) {
					if (content.charAt(j) == '{') {
						jsonStart = j;
						jsonFlag = true;
						break;
					}
				}
			}
			if (content.substring(i, i+9).equals("</script>") && jsonFlag) {
				jsonEnd = i;
				break;
			}
		}
		System.out.println(content.substring(jsonStart,jsonEnd));
		return content.substring(jsonStart,jsonEnd);
	}

	public static Recipe parseRecipe(String recipe) {
		return new Recipe("",0,0,"",new ArrayList<String>(),new ArrayList<String>());
	}
}
