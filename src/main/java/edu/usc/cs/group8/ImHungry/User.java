package edu.usc.cs.group8.ImHungry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
	public String name; 
	
	private ArrayList<Result> favorites;
	private ArrayList<Result> toExplore;
	private ArrayList<Result> doNotShow;
	private ArrayList<Query> quickAccess;
	private ArrayList<String> groceryList; 
	
	private HashMap<String,Result> cache;

	public User(String name, ArrayList<Result> favorites, ArrayList<Result> toExplore, ArrayList<Result> doNotShow, ArrayList<Query> quickAccess, ArrayList<String> groceryList)
	{
		this.name = name;
		this.favorites = favorites;
		this.toExplore = toExplore;
		this.doNotShow = doNotShow;
		this.quickAccess = quickAccess;
		this.groceryList = groceryList;
		cache = new HashMap<String,Result>();
	}
	
	public User(String name)
	{
		name = name;
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
		quickAccess = new ArrayList<Query>();
		groceryList = new ArrayList<String>();
		cache = new HashMap<String,Result>();
	}
	
	public User()
	{
		name = null;
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
		quickAccess = new ArrayList<Query>();
		groceryList = new ArrayList<String>();
		cache = new HashMap<String,Result>();
	}
	
	public User getLists() {
		// TODO Auto-generated method stub
		 return this; 
	}
	
	public void addToFavorites(Result r) {
		if (!favorites.contains(r)) favorites.add(r);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;	
		
		if (r instanceof Restaurant)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				
				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1; 
				if (rs != null)
				{
					list_size = rs.getInt("list_size"); 
				}
				st.close();
				list_size = list_size+1;

				st = conn.prepareStatement("INSERT INTO Restaurant(restaurant_id, recipe_name) values(?,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, r.getName());
				st.execute();
				
				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,0,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, list_size);
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
		else if (r instanceof Recipe)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				
				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1; 
				if (rs.next())
				{
					list_size = rs.getInt("list_size"); 
				}
				st.close();
				list_size = list_size+1; 

				st = conn.prepareStatement("INSERT INTO Recipe(recipe_url, recipe_name) values(?,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, r.getName());
				st.execute();
				
				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,0,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, list_size);
				st.execute();
			}
			catch (SQLException ex) {
		        // handle any errors
				ex.printStackTrace();
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
	
	public void addToToExplore(Result r) {
		if (!toExplore.contains(r)) toExplore.add(r);
		
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;	
		
		if (r instanceof Restaurant)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				
				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1; 
				if (rs != null)
				{
					list_size = rs.getInt("list_size"); 
				}
				st.close();
				list_size = list_size+1;

				st = conn.prepareStatement("INSERT INTO Restaurant(restaurant_id, recipe_name) values(?,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, r.getName());
				st.execute();
				
				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,1,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, list_size);
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
		else if (r instanceof Recipe)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				
				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1; 
				if (rs.next())
				{
					list_size = rs.getInt("list_size"); 
				}
				st.close();
				list_size = list_size+1; 

				st = conn.prepareStatement("INSERT INTO Recipe(recipe_url, recipe_name) values(?,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, r.getName());
				st.execute();
				
				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,1,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, list_size);
				st.execute();
			}
			catch (SQLException ex) {
		        // handle any errors
				ex.printStackTrace();
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
	
	
	public void addToDoNotShow(Result r) {
		if (!doNotShow.contains(r)) doNotShow.add(r);
		
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;	
		
		if (r instanceof Restaurant)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				
				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1; 
				if (rs != null)
				{
					list_size = rs.getInt("list_size"); 
				}
				st.close();
				list_size = list_size+1;

				st = conn.prepareStatement("INSERT INTO Restaurant(restaurant_id, recipe_name) values(?,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, r.getName());
				st.execute();
				
				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,2,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, list_size);
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
		else if (r instanceof Recipe)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				
				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1; 
				if (rs.next())
				{
					list_size = rs.getInt("list_size"); 
				}
				st.close();
				list_size = list_size+1; 

				st = conn.prepareStatement("INSERT INTO Recipe(recipe_url, recipe_name) values(?,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, r.getName());
				st.execute();
				
				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,2,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, list_size);
				st.execute();
			}
			catch (SQLException ex) {
		        // handle any errors
				ex.printStackTrace();
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

	public String getName() {
		return name;
	}
	public void setName(String newname)
	{
		name = newname; 
	}
	
	public Query getLastSearch() {
		if (quickAccess.isEmpty()) {
			return null;
		}
		return quickAccess.get(quickAccess.size()-1);
	}
	
	public void removeFromFavorites(Result r) {
		Connection conn = null;
		PreparedStatement st = null;
		if (favoritesContains(r))
		{
		
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				if (r instanceof Restaurant)
				{
					String id = ((Restaurant) r).getId();
					
					st = conn.prepareStatement("DELETE FROM ListRestaurants WHERE restaurant_id =? and username = ? "
							+ "and list_id=?");
					st.setString(1, id);	
					st.setString(2, name);
					st.setString(3, "0");
					st.execute();
				}
				else if (r instanceof Recipe)
				{
					String url = ((Recipe) r).getURL();
					st = conn.prepareStatement("DELETE FROM ListRecipes WHERE recipe_url =? and username = ? "
							+ "and list_id= ?");
					st.setString(1, url);
					st.setString(2,  name);
					st.setString(3, "0");
					st.execute();
				}				
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
			}
			finally
			{
				try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			favorites.remove(r);
		}
	}
	
	public void removeFromToExplore(Result r) {
		Connection conn = null;
		PreparedStatement st = null;
		if (toExploreContains(r)){
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				if (r instanceof Restaurant)
				{
					String id = ((Restaurant) r).getId();
					
					st = conn.prepareStatement("DELETE FROM ListRestaurants WHERE restaurant_id =? and username = ? "
							+ "and list_id=?");
					st.setString(1, id);	
					st.setString(2, name);
					st.setString(3, "1");
					st.execute();
				}
				else if (r instanceof Recipe)
				{
					String url = ((Recipe) r).getURL();
					st = conn.prepareStatement("DELETE FROM ListRecipes WHERE recipe_url =? and username = ? "
							+ "and list_id= ?");
					st.setString(1, url);
					st.setString(2,  name);
					st.setString(3, "1");
					st.execute();
				}				
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
			}
			finally
			{
				try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}				
			toExplore.remove(r);
		}
	}
	
	public void removeFromDoNotShow(Result r) {
		Connection conn = null;
		PreparedStatement st = null;
		if (doNotShowContains(r)) {
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false");
				if (r instanceof Restaurant)
				{
					String id = ((Restaurant) r).getId();
					
					st = conn.prepareStatement("DELETE FROM ListRestaurants WHERE restaurant_id =? and username = ? "
							+ "and list_id=?");
					st.setString(1, id);	
					st.setString(2, name);
					st.setString(3, "2");
					st.execute();
				}
				else if (r instanceof Recipe)
				{
					String url = ((Recipe) r).getURL();
					st = conn.prepareStatement("DELETE FROM ListRecipes WHERE recipe_url =? and username = ? "
							+ "and list_id= ?");
					st.setString(1, url);
					st.setString(2,  name);
					st.setString(3, "2");
					st.execute();
				}				
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
			}
			finally
			{
				try {
					conn.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}				
			doNotShow.remove(r);
		}
	}
	
	public void removeFromFavorites(int index){
		if (favorites.size() > index)
		{
			
			favorites.remove(index);
		}
	}
	
	public void removeFromToExplore(int index){
		if (toExplore.size() > index)
		{
			toExplore.remove(index);
		}
	}
	
	public void removeFromDoNotShow(int index){
		if (doNotShow.size() > index)
		{
			doNotShow.remove(index);
		}
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
		Connection conn = null;
		PreparedStatement st = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                    "user=root&password=root&useSSL=false");
			st = conn.prepareStatement("DELETE FROM Groceries WHERE username = ? "
					+ "and list_id= ?");
			st.setString(1,name);
			st.execute();
					
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally
		{
			try {
				conn.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		while(groceryList.size() > 0)
		{
			groceryList.remove(0);
		}
	}

	public ArrayList<String> getGroceries() {
		return groceryList; 
	}
	
	public Restaurant get(String token, String name) {
		if (token.charAt(0)=='\'') {
			token = token.substring(1,token.length()-1);
		}
		if (cache.containsKey(token)) {
			return (Restaurant)cache.get(token);
		} else {
			//TODO: Get the real name of the restaurant
			Restaurant r = new Restaurant (name,token);
			r = RestaurantGetter.getContactInfo(r);
			r = RestaurantGetter.getDriveTime(r);
			cache.put(token,r);
			return r;
		}
	}
	public Recipe get(String token) {
		if (token.charAt(0)=='\'') {
			token = token.substring(1,token.length()-1);
		}
		if (cache.containsKey(token)) {
			return (Recipe)cache.get(token);
		}
		else {
			Recipe r = RecipeGetter.parseRecipe(RecipeGetter.readRecipe(token));
			if (r == null) return null;
			r.setURL(token);
			cache.put(token, r);
			return r;
		} 
	}
}

