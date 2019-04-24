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
	
	private ArrayList<ArrayList<Result> > lists;

	private HashMap<String,Result> cache;

	public User(String name)
	{
		this.name = name;
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
		quickAccess = new ArrayList<Query>();
		groceryList = new ArrayList<String>();
		lists = new ArrayList<ArrayList<Result>>(3);
		lists.add(favorites);
		lists.add(toExplore);
		lists.add(doNotShow);
		cache = new HashMap<String,Result>();
	}

	public User()
	{
		name = "";
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
		quickAccess = new ArrayList<Query>();
		groceryList = new ArrayList<String>();
		lists = new ArrayList<ArrayList<Result>>();
		lists.add(favorites);
		lists.add(toExplore);
		lists.add(doNotShow);
		cache = new HashMap<String,Result>();
	}

	public User getLists() {
		// TODO Auto-generated method stub
		 return this;
	}
	
	public void addToList(Result r, int listNum) {
		if (!lists.get(listNum).contains(r)) lists.get(listNum).add(r);

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		if (r instanceof Restaurant)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");

				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1;
				if (rs.next())
				{
					list_size = rs.getInt("list_size");
				}
				if (list_size == -1) return;
				st.close();
				list_size = list_size+1;
				rs.close();

				st = conn.prepareStatement("SELECT * from Restaurant where restaurant_name = ?");
				st.setString(1, r.getName());
				rs = st.executeQuery();

				if (!rs.next()) {
					st = conn.prepareStatement("INSERT INTO Restaurant(restaurant_id, restaurant_name) values(?,?)");
					st.setString(1, ((Restaurant) r).getId());
					st.setString(2, r.getName());
					st.execute();
				}

				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,?,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, listNum);
				st.setInt(4, list_size);
				st.execute();

				st = conn.prepareStatement("UPDATE User Set list_size = ? WHERE username = ?");
				st.setInt(1, list_size);
				st.setString(2, name);
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
		else
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");

				st = conn.prepareStatement("SELECT list_size from User WHERE username=?");
				st.setString(1, name);
				rs = st.executeQuery();
				int list_size = -1;
				if (rs.next())
				{
					list_size = rs.getInt("list_size");
				}
				if (list_size == -1) return;
				st.close();
				list_size = list_size+1;

				st = conn.prepareStatement("SELECT * from Recipe where recipe_name = ?");
				st.setString(1, r.getName());
				rs = st.executeQuery();

				if (!rs.next()) {

					st = conn.prepareStatement("INSERT INTO Recipe(recipe_url, recipe_name) values(?,?)");
					st.setString(1, ((Recipe) r).getURL());
					st.setString(2, r.getName());
					st.execute();

				}

				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,?,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, listNum);
				st.setInt(4, list_size);
				st.execute();

				st = conn.prepareStatement("UPDATE User Set list_size = ? WHERE username = ?");
				st.setInt(1, list_size);
				st.setString(2, name);
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

	public void addToFavorites(Result r) {
		addToList(r,0);
	}

	public void addToToExplore(Result r) {
		addToList(r,1);
	}


	public void addToDoNotShow(Result r) {
		addToList(r,2);
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
	
	public void removeFromList(Result r, int listNum) {
		Connection conn = null;
		PreparedStatement st = null;
		if (lists.get(listNum).contains(r))
		{

			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
				if (r instanceof Restaurant)
				{
					String id = ((Restaurant) r).getId();

					st = conn.prepareStatement("DELETE FROM ListRestaurants WHERE restaurant_id =? and username = ? "
							+ "and list_id=?");
					st.setString(1, id);
					st.setString(2, name);
					st.setInt(3, listNum);
					st.execute();
				}
				else
				{
					String url = ((Recipe) r).getURL();
					st = conn.prepareStatement("DELETE FROM ListRecipes WHERE recipe_url =? and username = ? "
							+ "and list_id= ?");
					st.setString(1, url);
					st.setString(2,  name);
					st.setInt(3, listNum);
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
			lists.get(listNum).remove(r);
		}
	}

	public void removeFromFavorites(Result r) {
		removeFromList(r,0);
	}

	public void removeFromToExplore(Result r) {
		removeFromList(r,1);
	}

	public void removeFromDoNotShow(Result r) {
		removeFromList(r,2);
	}

	public void removeFromFavorites(int index){
		if (favorites.size() > index)
		{
			removeFromFavorites(favorites.get(index));
		}
	}

	public void removeFromToExplore(int index){
		if (toExplore.size() > index)
		{
			removeFromToExplore(toExplore.get(index));
		}
	}

	public void removeFromDoNotShow(int index){
		if (doNotShow.size() > index)
		{
			removeFromDoNotShow(doNotShow.get(index));
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
		for (int i = favorites.size() - 1; i >= 0; i--) {
			removeFromFavorites(i);
		}
		for (int i = toExplore.size() - 1; i >= 0; i--) {
			removeFromToExplore(i);
		}
		for (int i = doNotShow.size() - 1; i >= 0; i--) {
			removeFromDoNotShow(i);
		}
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

	public void setFavorites(ArrayList<Result> favorites) {
		this.favorites = favorites;
		lists.set(0, favorites);
	}

	public void setToExplore(ArrayList<Result> toExplore) {
		this.toExplore = toExplore;
		lists.set(1, toExplore);
	}

	public void setDoNotShow(ArrayList<Result> doNotShow) {
		this.doNotShow = doNotShow;
		lists.set(2, doNotShow);
	}

	public void setQuickAccess(ArrayList<Query> quickAccess) {
		this.quickAccess = quickAccess;
	}


	public void addToGroceryList(String r, String recipeURL) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			st = conn.prepareStatement("SELECT * FROM Groceries WHERE username = ? and ingredient = ? and recipe_url = ?");
			st.setString(1, name);
			st.setString(2, r);
			st.setString(3, recipeURL);
			rs = st.executeQuery();
			if (!rs.next()) {
				groceryList.add(r);
				PreparedStatement st2 = null;
				st2 = conn.prepareStatement("INSERT INTO Groceries(username, ingredient, recipe_url) VALUES(?,?,?)");
				st2.setString(1, name);
				st2.setString(2, r);
				st2.setString(3, recipeURL);
				st2.execute();
				st2.close();
			}
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
	public void addToGroceryList(Recipe r) {
		ArrayList<String> ingreds = r.getIngredients();
		Connection conn = null;
		PreparedStatement st = null;
		for (int i = 0; i < ingreds.size(); i++)
		{
			addToGroceryList(ingreds.get(i),r.getURL());
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
		                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
						st = conn.prepareStatement("DELETE FROM Groceries WHERE username =? and ingredient = ?");
						st.setString(1, name);
						st.setString(2, ingreds.get(j));
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
	                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
					st = conn.prepareStatement("DELETE FROM Groceries WHERE username =? and ingredient = ?");
					st.setString(1, name);
					st.setString(2, res);
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
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			st = conn.prepareStatement("DELETE FROM Groceries WHERE username = ?");
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

	public Restaurant getRestaurant(String token) {
		
		if (this.name == null) return null;
		if (cache.containsKey(token)) {
			return (Restaurant)cache.get(token);
		} else {
			Restaurant r = new Restaurant(token);
			r = RestaurantGetter.getContactInfo(r);
			r = RestaurantGetter.getDriveTime(r);
			cache.put(token,r);
			//TODO: Get the real name of the restaurant
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
						"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
				st = conn.prepareStatement("SELECT * FROM Restaurant WHERE restaurant_id = ? AND restaurant_name = ?");
				st.setString(1, token);
				st.setString(2, r.getName());
				rs = st.executeQuery();
				if (!rs.next()) {
					PreparedStatement st2 = null;
					st2 = conn.prepareStatement("INSERT INTO Restaurant(restaurant_id,restaurant_name) VALUES(?,?)");
					st2.setString(1, token);
					st2.setString(2, r.getName());
					st2.execute();
					st2.close();
				}
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
			
			return r;
		}
	}
	public Recipe getRecipe(String token) {
		if (name == null) return null;
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
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
						"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
				st = conn.prepareStatement("SELECT * FROM Recipe WHERE recipe_url = ? AND recipe_name = ?");
				st.setString(1, token);
				st.setString(2, r.getName());
				rs = st.executeQuery();
				if (!rs.next()) {
					PreparedStatement st2 = null;
					st2 = conn.prepareStatement("INSERT INTO Recipe(recipe_url,recipe_name) VALUES(?,?)");
					st2.setString(1, token);
					st2.setString(2, r.getName());
					st2.execute();
					st2.close();
				}
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
			return r;
		}
	}

	public void setGroceries(ArrayList<String> groceries) {
		this.groceryList = groceries;
		
	}
	
	public void swapItems(int index1, int index2, int listNum) {
		ArrayList<Result> tempList = lists.get(listNum);
		Result temp = tempList.get(index1);
		lists.get(listNum).set(index1,lists.get(listNum).get(index2));
		lists.get(listNum).set(index2, temp);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			if (lists.get(listNum).get(index1) instanceof Restaurant) {
				if (lists.get(listNum).get(index2) instanceof Restaurant) {
					
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = ?");
					st.setString(1, name);
					st.setInt(2, listNum);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(lists.get(listNum).get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("restaurant_id").equals(((Restaurant)(lists.get(listNum).get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = ? AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, listNum);
					st2.setInt(4, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, listNum);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				}
				else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = ?");
					st.setString(1, name);
					st.setInt(2, listNum);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(lists.get(listNum).get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = ?");
					st2.setString(1, name);
					st2.setInt(2, listNum);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(lists.get(listNum).get(index2))).getURL())) {
							tempindex1 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = ? AND list_no = ?");
					st3.setInt(1, tempindex1);
					st3.setString(2, name);
					st3.setInt(3, listNum);
					st3.setInt(4, tempindex2);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = ? AND list_no = ?");
					st4.setInt(1, tempindex2);
					st4.setString(2, name);
					st4.setInt(3, listNum);
					st4.setInt(4, tempindex1);
					st4.execute();
					
					st2.close();
					st3.close();
					st4.close();
				}
			} else {
				if (lists.get(listNum).get(index2) instanceof Recipe) {
					st = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = ?");
					st.setString(1, name);
					st.setInt(2, listNum);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("recipe_url").equals(((Recipe)(lists.get(listNum).get(index1))).getURL())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("recipe_url").equals(((Recipe)(lists.get(listNum).get(index2))).getURL())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = ? AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, listNum);
					st2.setInt(4, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, listNum);
					st2.setInt(4, tempindex1);
					st2.execute();
					
					st2.close();
				} else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = ?");
					st.setString(1, name);
					st.setInt(2, listNum);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(lists.get(listNum).get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = ?");
					st2.setString(1, name);
					st2.setInt(2, listNum);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(lists.get(listNum).get(index1))).getURL())) {
							tempindex2 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = ? AND list_id = ?");
					st3.setInt(1, tempindex2);
					st3.setString(2, name);
					st3.setInt(3, listNum);
					st3.setInt(4, tempindex1);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = ? AND list_id = ?");
					st4.setInt(1, tempindex1);
					st4.setString(2, name);
					st4.setInt(3, listNum);
					st4.setInt(4, tempindex2);
					st4.execute();
					
					st2.close();
					st3.close();
					st4.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public void swapFavorites(int index1, int index2) {
		swapItems(index1,index2,0);
	}

	public void swapToExplore(int index1, int index2) {
		swapItems(index1,index2,1);
	}

	public void swapDoNotShow(int index1, int index2) {
		swapItems(index1,index2,2);
	}
}
