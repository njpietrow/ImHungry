package edu.usc.cs.group8.ImHungry;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Query {
	
	public String keyword;
	public String numResults; 
	public String radius; 
	public ArrayList<String> images;

	public Query(String keyword, String numResults, String radius) {
		// TODO Auto-generated constructor stub
		this.keyword = keyword;
		this.numResults = numResults; 
		this.radius = radius;
		images = new ArrayList<String>();
		doImageSearch();
	}
	
	public String readWebsite(String url) {
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
		}catch ( Exception ex ) {
			ex.printStackTrace();
		    return null;
		}
		return content;
	}

	/*
	 * This sends a request out to Google to find images related to the keyword. It
	 * grabs the first ten and returns them in an ArrayList as raw URLs in Strings.
	 */
	private void doImageSearch() {
		String kword = keyword.replaceAll(" ", "+").toLowerCase();
		String results = readWebsite("https://www.google.com/search?q=" + kword + "&tbm=isch&gws_rd=ssl");
		if (results == null) return;
		else {
			int i = 0;
			while (images.size() < 10 && i < results.length() - 5) {
				for (int j = i; j < results.length() - 5; i++, j++) {
					if (results.substring(j, j+5).equals("\"ou\":")) {
						j += 6;
						i = j;
						while (results.charAt(i) != '"' && i < results.length()) i++;
						images.add(results.substring(j,i));
						break;
					}
				}
			}
			return;
		}
		
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNumResults() {
		return numResults;
	}

	public void setNumResults(String numResults) {
		this.numResults = numResults;
	}
	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((numResults == null) ? 0 : numResults.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Query other = (Query) obj;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (numResults == null) {
			if (other.numResults != null)
				return false;
		} else if (!numResults.equals(other.numResults))
			return false;
		return true;
	}
	
	public String toString() {
		return numResults + " " + keyword;
	}
	
	public ArrayList<String> getImages(){
		return images;
	}

}