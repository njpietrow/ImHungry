/** package edu.usc.cs.group8.ImHungry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * ListManager.java
 * Class that contains the three predefined lists as well as specific functions for accessing them.
 * Author: Kevin Calaway
 * USC ID: 9724507315
 * Email: calaway@usc.edu
 
public class ListManager {
	
	private ArrayList<Result> favorites;
	private ArrayList<Result> toExplore;
	private ArrayList<Result> doNotShow;
	private ArrayList<Query> quickAccess;
	private ArrayList<String> groceryList; 
	
	private static ListManager singleton;
	
	public ListManager() {
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
		quickAccess = new ArrayList<Query>();
		groceryList = new ArrayList<String>();
	}
	
	/*
	 * There is one list manager for the whole website.
	 
	public static ListManager getInstance() {
		if (singleton == null) {
			singleton = new ListManager();
		}
		return singleton;
	}
	
	public void addToFavorites(Result r) {
		if (!favorites.contains(r)) favorites.add(r);
	}
	
	public void addToToExplore(Result r) {
		if (!toExplore.contains(r)) toExplore.add(r);
	}
	
	public void addToDoNotShow(Result r) {
		if (!doNotShow.contains(r)) doNotShow.add(r);
	}
	
	public void removeFromFavorites(Result r) {
		if (favoritesContains(r))
			favorites.remove(r);
	}
	
	public void removeFromToExplore(Result r) {
		if (toExploreContains(r)){
			toExplore.remove(r);
		}
	}
	
	public void removeFromDoNotShow(Result r) {
		if (doNotShowContains(r)) {
			doNotShow.remove(r);
		}
	}
	
	public void removeFromFavorites(int index){
		if (favorites.size() > index)
			favorites.remove(index);
	}
	
	public void removeFromToExplore(int index){
		if (toExplore.size() > index)
			toExplore.remove(index);
	}
	
	public void removeFromDoNotShow(int index){
		if (doNotShow.size() > index)
			doNotShow.remove(index);
	}
	
	public boolean favoritesContains(Result r) {
		return favorites.contains(r);
	}
	
	public boolean toExploreContains(Result r) {
		return toExplore.contains(r);
	}
	
	public boolean doNotShowContains(Result r) {
		return doNotShow.contains(r);
	}
	
	public ArrayList<Result> getFavorites(){
		return favorites;
	}
	
	public ArrayList<Result> getToExplore(){
		return toExplore;
	}
	
	public ArrayList<Result> getDoNotShow(){
		return doNotShow;
	}
	
	public void reset() {
		favorites.clear();
		toExplore.clear();
		doNotShow.clear();
	}

	public boolean quickAccessContains(Query query) {
		return quickAccess.contains(query);
	}

	public ArrayList<Query> getQuickAccess() {
		return quickAccess;
	}
	
	public void addToQuickAccess(Query q) {
		quickAccess.add(q);
	}
	
	public void removeFromQuickAccess(Query q) {
		quickAccess.remove(q);
	}
	
	public void removeFromQuickAccess(int index) {
		quickAccess.remove(index);
	}

	public void setFavorites(ArrayList<Result> favorites) {
		this.favorites = favorites;
	}
	
	public void setToExplore(ArrayList<Result> toExplore) {
		this.toExplore = toExplore;
	}
	
	public void setDoNotShow(ArrayList<Result> doNotShow) {
		this.doNotShow = doNotShow;
	}
	
	public void setQuickAccess(ArrayList<Query> quickAccess) {
		this.quickAccess = quickAccess;
	}

	
	public void addToGroceryList(String r) {
		// TODO Auto-generated method stub
		groceryList.add(r);
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;	
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                    "user=root&password=root&useSSL=false");
			st = conn.prepareStatement("INSERT INTO Groceries(username, ingredient) VALUES(?,?)");
			String username = "";
			st.setString(1, username);
			st.setString(2, r);
			st.execute();
		}
		catch (SQLException ex) {
	        // handle any errors
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    }
		finally {
			try {
				conn.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	public void addToGroceryList(Recipe r) {
		ArrayList<String> ingreds = r.getIngredients();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		for (int i = 0; i < ingreds.size(); i++)
		{
			groceryList.add(ingreds.get(i));
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
	                    "user=root&password=root&useSSL=false");
				st = conn.prepareStatement("INSERT INTO Groceries(username, ingredient) VALUES(?,?)");
				String username = "";
				st.setString(1, username);
				st.setString(2, ingreds.get(i));
				st.execute();
			}
			catch (SQLException ex) {
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
			finally {
				try {
					conn.close();
					st.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}

	public boolean groceryListContains(String res) {
		// TODO Auto-generated method stub
		for (int i = 0; i < groceryList.size(); i++)
		{
			if (groceryList.get(i).equals(res))
				return true; 
		}
		return false; 
	}

	public boolean removeFromGroceryList(Recipe r) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		if (groceryList.size() < 1)
			return false; 
		
		ArrayList<String> ingreds = r.getIngredients();
		if (groceryList.size() < ingreds.size())
			return false; 
		if (ingreds.size() == 0)
			return true; 
		for (int i = groceryList.size()-1; i >= 0; i--)
		{
			for (int j = ingreds.size()-1; j >= 0; j--)
			{
				if (groceryList.get(i).equals(ingreds.get(j)))
				{
					try
					{
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
			                    "user=root&password=root&useSSL=false");
						st = conn.prepareStatement("DELETE FROM Groceries WHERE username =? and ingredient = ?");
						String username = "";
						st.setString(1, username);
						st.setString(2, ingreds.get(i));
						st.execute();
					}
					catch (SQLException ex) {
				        // handle any errors
				        System.out.println("SQLException: " + ex.getMessage());
				        System.out.println("SQLState: " + ex.getSQLState());
				        System.out.println("VendorError: " + ex.getErrorCode());
				    }
					finally {
						try {
							conn.close();
							st.close();
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
					groceryList.remove(i);
					ingreds.remove(j);
					if (ingreds.size() == 0)
						return true; 
					break; 
				}
			}
		}
		return false; 
		
	}

	public boolean removeFromGroceryList(String res) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		if (groceryList.size() < 1)
			return false; 
		for (int i = 0; i < groceryList.size(); i++)
		{
			if (groceryList.get(i).equals(res))
			{
				try
				{
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
		                    "user=root&password=root&useSSL=false");
					st = conn.prepareStatement("DELETE FROM Groceries WHERE username =? and ingredient = ?");
					String username = "";
					st.setString(1, username);
					st.setString(2, res);
					st.execute();
				}
				catch (SQLException ex) {
			        // handle any errors
			        System.out.println("SQLException: " + ex.getMessage());
			        System.out.println("SQLState: " + ex.getSQLState());
			        System.out.println("VendorError: " + ex.getErrorCode());
			    }
				finally {
					try {
						conn.close();
						st.close();
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				groceryList.remove(i);
				return true; 
			}
		}
		return false; 
		
	}

	public void clearGroceryList() {
		while(groceryList.size() > 0)
		{
			groceryList.remove(0);
		}
	}

	public ArrayList<String> getGroceries() {
		return groceryList; 
	}
}

*/