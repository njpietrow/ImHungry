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

	public User(String name)
	{
		this.name = name;
		favorites = new ArrayList<Result>();
		toExplore = new ArrayList<Result>();
		doNotShow = new ArrayList<Result>();
		quickAccess = new ArrayList<Query>();
		groceryList = new ArrayList<String>();
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

				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,0,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, list_size);
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

				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,0,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, list_size);
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

				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,1,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, list_size);
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

				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,1,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, list_size);
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

				st = conn.prepareStatement("INSERT INTO ListRestaurants(restaurant_id, username, list_id, list_no) values(?,?,2,?)");
				st.setString(1, ((Restaurant) r).getId());
				st.setString(2, name);
				st.setInt(3, list_size);
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
				rs.close();

				st = conn.prepareStatement("SELECT * from Recipe where recipe_name = ?");
				st.setString(1, r.getName());
				rs = st.executeQuery();

				if (!rs.next()) {
					st = conn.prepareStatement("INSERT INTO Recipe(recipe_url, recipe_name) values(?,?)");
					st.setString(1, ((Recipe) r).getURL());
					st.setString(2, r.getName());
					st.execute();
				}

				st = conn.prepareStatement("INSERT INTO ListRecipes(recipe_url, username, list_id, list_no) values(?,?,2,?)");
				st.setString(1, ((Recipe) r).getURL());
				st.setString(2, name);
				st.setInt(3, list_size);
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
                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
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
				else
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
                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
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
				else
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
                        "user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
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
				else
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


	public void addToGroceryList(String r, String recipeURL) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			//st = conn.prepareStatement("SELECT * FROM Groceries WHERE username = ? AND ingredient = ? AND recipe_url = ?");
			st = conn.prepareStatement("SELECT * FROM Groceries WHERE username = ? AND ingredient = ? AND recipe_url = ?");
			st.setString(1, name);
			st.setString(2, r);
			st.setString(3, recipeURL);
			System.out.println(st);
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
		System.out.println(ingreds.size());
		for (int i = 0; i < ingreds.size(); i++)
		{
			System.out.println(ingreds.get(i));
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

	public Restaurant get(String token, String name) {
		
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
	public Recipe get(String token) {
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

	public void swapFavorites(int index1, int index2) {
		Result temp = favorites.get(index1);
		favorites.set(index1,favorites.get(index2));
		favorites.set(index2, temp);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			if (favorites.get(index1) instanceof Restaurant) {
				if (favorites.get(index2) instanceof Restaurant) {
					
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 0");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(favorites.get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("restaurant_id").equals(((Restaurant)(favorites.get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 0 AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				}
				else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 0");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(favorites.get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 0");
					st2.setString(1, name);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(favorites.get(index2))).getURL())) {
							tempindex1 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 0 AND list_no = ?");
					st3.setInt(1, tempindex1);
					st3.setString(2, name);
					st3.setInt(3, tempindex2);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 0 AND list_no = ?");
					st4.setInt(1, tempindex2);
					st4.setString(2, name);
					st4.setInt(3, tempindex1);
					st4.execute();
					
					st2.close();
					st3.close();
					st4.close();
				}
			} else {
				if (favorites.get(index2) instanceof Recipe) {
					st = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 0");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("recipe_url").equals(((Recipe)(favorites.get(index1))).getURL())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("recipe_url").equals(((Recipe)(favorites.get(index2))).getURL())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 0 AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				} else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 0");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(favorites.get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 0");
					st2.setString(1, name);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(favorites.get(index1))).getURL())) {
							tempindex2 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 0 AND list_id = ?");
					st3.setInt(1, tempindex2);
					st3.setString(2, name);
					st3.setInt(3, tempindex1);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 0 AND list_id = ?");
					st4.setInt(1, tempindex1);
					st4.setString(2, name);
					st4.setInt(3, tempindex2);
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

	public void swapToExplore(int index1, int index2) {
		Result temp = toExplore.get(index1);
		toExplore.set(index1,toExplore.get(index2));
		toExplore.set(index2, temp);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			if (toExplore.get(index1) instanceof Restaurant) {
				if (toExplore.get(index2) instanceof Restaurant) {
					
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 1");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(toExplore.get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("restaurant_id").equals(((Restaurant)(toExplore.get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 1 AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				}
				else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 1");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(toExplore.get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 1");
					st2.setString(1, name);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(toExplore.get(index2))).getURL())) {
							tempindex1 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 1 AND list_no = ?");
					st3.setInt(1, tempindex1);
					st3.setString(2, name);
					st3.setInt(3, tempindex2);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 1 AND list_no = ?");
					st4.setInt(1, tempindex2);
					st4.setString(2, name);
					st4.setInt(3, tempindex1);
					st4.execute();
					
					st2.close();
					st3.close();
					st4.close();
				}
			} else {
				if (toExplore.get(index2) instanceof Recipe) {
					st = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 1");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("recipe_url").equals(((Recipe)(toExplore.get(index1))).getURL())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("recipe_url").equals(((Recipe)(toExplore.get(index2))).getURL())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 1 AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				} else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 1");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(toExplore.get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 1");
					st2.setString(1, name);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(toExplore.get(index1))).getURL())) {
							tempindex2 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 1 AND list_id = ?");
					st3.setInt(1, tempindex2);
					st3.setString(2, name);
					st3.setInt(3, tempindex1);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 1 AND list_id = ?");
					st4.setInt(1, tempindex1);
					st4.setString(2, name);
					st4.setInt(3, tempindex2);
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

	public void swapDoNotShow(int index1, int index2) {
		Result temp = doNotShow.get(index1);
		doNotShow.set(index1,doNotShow.get(index2));
		doNotShow.set(index2, temp);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
					"user=root&password=root&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
			if (doNotShow.get(index1) instanceof Restaurant) {
				if (doNotShow.get(index2) instanceof Restaurant) {
					
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 2");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(doNotShow.get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("restaurant_id").equals(((Restaurant)(doNotShow.get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 2 AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				}
				else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 2");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(doNotShow.get(index1))).getId())) {
							tempindex2 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 2");
					st2.setString(1, name);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(doNotShow.get(index2))).getURL())) {
							tempindex1 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 2 AND list_no = ?");
					st3.setInt(1, tempindex1);
					st3.setString(2, name);
					st3.setInt(3, tempindex2);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 2 AND list_no = ?");
					st4.setInt(1, tempindex2);
					st4.setString(2, name);
					st4.setInt(3, tempindex1);
					st4.execute();
					
					st2.close();
					st3.close();
					st4.close();
				}
			} else {
				if (doNotShow.get(index2) instanceof Recipe) {
					st = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 2");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("recipe_url").equals(((Recipe)(doNotShow.get(index1))).getURL())) {
							tempindex2 = rs.getInt("list_no");
						}
						if (rs.getString("recipe_url").equals(((Recipe)(doNotShow.get(index2))).getURL())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					PreparedStatement st2 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 2 AND list_no = ?");
					st2.setInt(1, tempindex1);
					st2.setString(2, name);
					st2.setInt(3, tempindex2);
					st2.execute();
					
					st2.setInt(1, tempindex2);
					st2.setString(2, name);
					st2.setInt(3, tempindex1);
					st2.execute();
					
					st2.close();
				} else {
					st = conn.prepareStatement("SELECT * FROM ListRestaurants WHERE username = ? AND list_id = 2");
					st.setString(1, name);
					rs = st.executeQuery();
					int tempindex1 = -1;
					int tempindex2 = -1;
					while (rs.next()) {
						if (rs.getString("restaurant_id").equals(((Restaurant)(doNotShow.get(index2))).getId())) {
							tempindex1 = rs.getInt("list_no");
						}
					}
					
					PreparedStatement st2 = conn.prepareStatement("SELECT * FROM ListRecipes WHERE username = ? AND list_id = 2");
					st2.setString(1, name);
					ResultSet rs2 = st2.executeQuery();
					
					while (rs2.next()) {
						if (rs2.getString("recipe_url").equals(((Recipe)(doNotShow.get(index1))).getURL())) {
							tempindex2 = rs2.getInt("list_no");
						}
					}
					rs2.close();
					
					PreparedStatement st3 = conn.prepareStatement("UPDATE ListRestaurants SET list_no = ? WHERE username = ? AND list_id = 2 AND list_id = ?");
					st3.setInt(1, tempindex2);
					st3.setString(2, name);
					st3.setInt(3, tempindex1);
					st3.execute();
					
					PreparedStatement st4 = conn.prepareStatement("UPDATE ListRecipes SET list_no = ? WHERE username = ? AND list_id = 2 AND list_id = ?");
					st4.setInt(1, tempindex1);
					st4.setString(2, name);
					st4.setInt(3, tempindex2);
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
}
