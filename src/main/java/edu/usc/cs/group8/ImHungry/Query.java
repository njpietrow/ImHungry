package edu.usc.cs.group8.ImHungry;

public class Query {
	
	public String keyword;
	public String numResults; 

	public Query(String keyword, String numResults) {
		// TODO Auto-generated constructor stub
		this.keyword = keyword;
		this.numResults = numResults; 
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
	
	

}
