<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="classes.Restaurant" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="recipe_page.css" />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Restaurant Page</title>
  </head>
  <body>
	 <!-- Required Links -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/ssbootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
	
	<!-- Container Class -->	
	<div class="container">
	    <br/><br>
	    <!-- Button Group for Printable Version, Back to Results, Dropdown for Predefined Lists, and Add to list -->
	    <div class="overall_information">
	    	<div class="btn-group-vertical">
	        	<button class="btn btn-success">Printable Version</button> 
	        	<button class="btn btn-success">Back to Results</button> 
	        	<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
			      <div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        <a class="dropdown-item" href="#">Favorites</a>
			        <a class="dropdown-item" href="#">To Explore</a>
			        <a class="dropdown-item" href="#">Do Not Show</a>
			      </div>
	        	<button class="btn btn-success">Add to list</button> 
	        </div> 
        	<div class="restaurant_information">
		    	<%
		    	//Creating a new recipe object
			        Restaurant restaurant = new Restaurant("In N Out",15, 10.5, "ininout.com","address","2134536784",16);
		    		String restaurant_name = restaurant.getName();
			        int drive_time = restaurant.getDriveTime();
			        double restaurant_distance = restaurant.getDistance();
			        String website_URL = restaurant.getWebsiteURL();
			        String restaurant_address = restaurant.getAddress();
			        String restaurant_phone_num = restaurant.getPhoneNum();
			        int price_range = restaurant.getPriceRange();
			    %>
		    	<div id=restaurant_name><%=restaurant_name%></div>
		    	<div id=restaurant_address><%=restaurant_address%></div>
		    	<div id=restaurant_phone_number><%=restaurant_phone_num%></div>
		    	<div id=restaurant_website><%=website_URL%></div>
        	</div>
	    </div>
	</div>
  	<!-- End Container Class -->	
	  
	<!-- More Required Links -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
