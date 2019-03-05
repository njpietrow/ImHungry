<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Recipe" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Restaurant" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <link rel="stylesheet" type="text/css" href="result_page.css" />
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
	
		<title>result_page</title>
	</head>
	
	<body>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/ssbootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<div class = "div_for_entire_content">
			<div class = "text-center"> 
				Results for <%= session.getAttribute("query") %>
			</div>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
		
			<div class="container">
		    <br/>
		    	<!-- Button Group for Back to Search Page and Dropdown for Predefined Lists-->	
				<div class="overall_information">
					<div class="btn-group-vertical" id="button_stuff">
						<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
							<div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        			<a class="dropdown-item" href="#">Favorites</a>
			        			<a class="dropdown-item" href="#">To Explore</a>
			        			<a class="dropdown-item" href="#">Do Not Show</a>
			      			</div>
			      			<script>
							     $(function(){
								    $(".dropdown-item").click(function(){					
								      $("#btnGroupVerticalDrop2").text($(this).text());
								      $("#btnGroupVerticalDrop2").val($(this).text());
								   });
								});
							</script>
							<button class="btn btn-success">Manage List</button>
					</div>
				
					<div class="recipe_and_restaurant_results">
					
						<div class="restaurant_results">
							<table id="restaurant_results_table">
								<%
								//Getting restaurant results array list from session
								if (session.getAttribute("restaurants") == null){
									return;
								}
								ArrayList<Restaurant> list_of_restaurant_results = (ArrayList<Restaurant>)(session.getAttribute("restaurants"));
								for (int i = 0; i < list_of_restaurant_results.size(); i++){
									Restaurant restaurant = list_of_restaurant_results.get(i);
									String restaurant_name = restaurant.getName();
									int driveTime = restaurant.getDriveTime();
									String address = restaurant.getAddress();
									%> <tr><th><a href="restaurant_page.jsp?restaurant_id=<%= i%>"><%=restaurant_name%></a>, </th> <th><%=driveTime%> min, </th> <th><%=address %></th></tr> <%
								}
								
								
								%>
							</table>
						</div>
					
						<div class="recipe_results">
							<table id="recipe_results_table">
								<%
								//Getting recipe results array list from session
								if (session.getAttribute("recipes") == null){
									return;
								}
								ArrayList<Recipe> list_of_recipe_results = (ArrayList<Recipe>)(session.getAttribute("recipes"));
								for (int i = 0; i < list_of_recipe_results.size(); i++){
									Recipe recipe = list_of_recipe_results.get(i);
									String recipe_name = recipe.getName();
									String cookTime, prepTime;
									if (recipe.getCookTime() == 0){
										cookTime = "No cook time available.";
									}
									else cookTime = recipe.getCookTime() + " min";
									if (recipe.getPrepTime() == 0){
										prepTime = "No prep time available.";
									}
									else prepTime = recipe.getPrepTime() + " min";
									%> <tr><th><a href="recipe_page.jsp?recipe_id=<%= i%>"><%=recipe_name%></a></th> <th><%=cookTime %></th> <th><%=prepTime %></th></tr> <%
								}
								
								
								%>
							</table>
						</div>
					</div>
				
				
		        </div>
	        </div>
	    </div>
	    
	<script type="text/javascript">
		function listRecipeResults(){
			var recipe_table = document.getElementById('recipe_results_table');
			var recipe_table_row = document.createElement("tr");
			
			//var list_of_recipes = 
		}
	
	</script>
	    
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	</body>
</html>