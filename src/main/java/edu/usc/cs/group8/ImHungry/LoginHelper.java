package edu.usc.cs.group8.ImHungry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class LoginHelper {
	
	Connection conn = null;
	PreparedStatement st = null;
	ResultSet rs = null;


	public boolean login(String username, String password, User currUser) {
		long passvalue = encrypt(password);
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        conn =
	           DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
	                                       "user=root&password=root&useSSL=false");
	        st = conn.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
	        st.setString(1, username);
	        st.setLong(2, passvalue);
	        rs = st.executeQuery();
	        if (rs.next()) {
	        	String user = rs.getString("username"); 
	        	currUser.setName(user);
	        }
	        else {
	        	return false;
	        }
	        rs.close();
	        if (currUser.getName().equals(""))
	        	return false; 
	        TreeMap<Integer, Restaurant> favoriteRestaurants = new TreeMap<Integer, Restaurant>();
	        TreeMap<Integer, Recipe>  favoriteRecipes = new TreeMap<Integer, Recipe> ();
	        TreeMap<Integer, Restaurant> toExploreRestaurants = new TreeMap<Integer, Restaurant>();
	        TreeMap<Integer, Recipe>  toExploreRecipes = new TreeMap<Integer, Recipe> ();
	        TreeMap<Integer, Restaurant> doNotShowRestaurants = new TreeMap<Integer, Restaurant>();
	        TreeMap<Integer, Recipe>  doNotShowRecipes = new  TreeMap<Integer, Recipe> ();
	        ArrayList<Query> quickAccess = new ArrayList<Query>();
	        ArrayList<String> groceries = new ArrayList<String>();
	        
	        st = conn.prepareStatement("SELECT * FROM ListRestaurants l1, Restaurant r where username=? AND l1.restaurant_id = r.restaurant_id");
	        st.setString(1, username);
	        rs = st.executeQuery(); 
	        while(rs.next())
	        {
	        	String resName = rs.getString("restaurant_name");
	        	String resID = rs.getString("restaurant_id"); 
	        	Restaurant temp = new Restaurant(resName,resID);
	        	temp = RestaurantGetter.getContactInfo(temp); 
	        	temp = RestaurantGetter.getDriveTime(temp); 
	        	int listID = rs.getInt("list_id"); 
	        	int listNum = rs.getInt("list_no");
	        	switch(listID)
	        	{
	        	case 0: 
	        		favoriteRestaurants.put(listNum, temp);
	        		break;
	        	case 1: 
	        		toExploreRestaurants.put(listNum, temp);
	        		break;
	        	default:
	        		doNotShowRestaurants.put(listNum, temp);
	        		break;
	        	}
	        	
	        }
	        rs.close();
	        st = conn.prepareStatement("SELECT * FROM ListRecipes l2, Recipe r where username=? AND l2.recipe_url = r.recipe_url");
	        st.setString(1, username);
	        rs = st.executeQuery();
	        while(rs.next())
	        {
	        	String recURL = rs.getString("recipe_url");
	        	Recipe temp = RecipeGetter.parseRecipe(RecipeGetter.readRecipe(recURL));
				temp.setURL(recURL); 
	        	int listID = rs.getInt("list_id"); 
	        	int listNum = rs.getInt("list_no");
	        	switch(listID)
	        	{
	        	case 0: 
	        		favoriteRecipes.put(listNum, temp);
	        		break;
	        	case 1: 
	        		toExploreRecipes.put(listNum, temp);
	        		break;
	        	default:
	        		doNotShowRecipes.put(listNum, temp);
	        		break;
	        	}
	        	
	        }
	        
	        st = conn.prepareStatement("SELECT * FROM QuickAccess where username=?");
	        st.setString(1, username);
	        rs = st.executeQuery();
	        while(rs.next())
	        {
	        	String keyword = rs.getString("keyword");
	        	int numResults = rs.getInt("num_results");
	        	Query temp = new Query(keyword,"" + numResults); 
	        	quickAccess.add(temp);
	        	
	        }
	        
	        st = conn.prepareStatement("SELECT * FROM Groceries where username=?");
	        st.setString(1, username);
	        rs = st.executeQuery();
	        while(rs.next())
	        {
	        	String ingredient = rs.getString("ingredient");
	        	currUser.addToGroceryList(ingredient);
	        	
	        }
	        
	        TreeMap<Integer,Result> favorites = new TreeMap<Integer,Result>();
	        favorites.putAll(favoriteRecipes);
	        favorites.putAll(favoriteRestaurants);
	        currUser.getLists().setFavorites(new ArrayList<Result>(favorites.values()));
	        
	        TreeMap<Integer,Result> toExplore = new TreeMap<Integer,Result>();
	        toExplore.putAll(toExploreRecipes);
	        toExplore.putAll(toExploreRestaurants);
	        currUser.getLists().setToExplore(new ArrayList<Result>(toExplore.values()));
	        
	        TreeMap<Integer,Result> doNotShow = new TreeMap<Integer,Result>();
	        doNotShow.putAll(doNotShowRecipes);
	        doNotShow.putAll(doNotShowRestaurants);
	        currUser.getLists().setDoNotShow(new ArrayList<Result>(doNotShow.values()));
	        
	        currUser.getLists().setQuickAccess(quickAccess);
	        
	        return true;
	        // Do something with the Connection
	
	    } catch (SQLException ex) {
	        // handle any errors
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
	}

	public boolean logout(User currUser) {
		if (currUser.getName().equals("")) {
			return false;
		}
		currUser.setName("");
		currUser.reset();
		return true;
		
	}

	public boolean createAccount(String username, String password) {
		long passvalue = encrypt(password);
		 try {
		        conn =
		           DriverManager.getConnection("jdbc:mysql://localhost:3306/ImHungry?" +
		                                       "user=root&password=root&useSSL=false");
		        st = conn.prepareStatement("SELECT * FROM User WHERE username=?");
		        st.setString(1, username);
		        rs = st.executeQuery();
		        if (rs.next()) {
		        	return false; 
		        }
		        rs.close();
		        
		        st = conn.prepareStatement("INSERT INTO User(username,password,list_size) values(?,?,0)");
		        st.setString(1, username);
		        st.setLong(2,passvalue);
		        st.execute();

		        
        
		        return true;
		        // Do something with the Connection
		
		    } catch (SQLException ex) {
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
			return false; 
	}
	
	public long encrypt(String password) {
		long encrypted = 0;
		for (int i = 0; i < password.length(); i++) {
			encrypted += ((int)password.charAt(i) * Math.pow(i+1, 2));
		}
		return encrypted;
	}

  }