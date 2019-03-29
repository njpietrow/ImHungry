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
	
	

}
