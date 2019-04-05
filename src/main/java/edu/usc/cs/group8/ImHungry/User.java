package edu.usc.cs.group8.ImHungry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
	public String name; 
	public ListManager mg; 

	public User(String name, ListManager manage)
	{
		this.name = name;
		mg = manage; 
	}
	public User()
	{
		name = null;
		mg = new ListManager(); 
	}
	public ListManager getLists() {
		// TODO Auto-generated method stub
		 return mg; 
	}
	public void addToFavorites(Result r) {
		mg.addToFavorites(r);
		
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
		mg.addToToExplore(r);
		
		
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
		mg.addToDoNotShow(r);
		
		
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
	public void setLists(ListManager mg)
	{
		this.mg = mg; 
	}
	public Query getLastSearch() {
		if (mg.getQuickAccess().isEmpty()) {
			return null;
		}
		return mg.getQuickAccess().get(mg.getQuickAccess().size()-1);
	}
	
}
