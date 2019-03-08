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
	    <link rel="stylesheet" type="text/css" href="results_page.css" />
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
	
		<title>Results for <%= session.getAttribute("query") %></title>
	</head>
	
	<body>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/ssbootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<div class = "div_for_entire_content">
		
			<!-- The following div will display the collage with random rotation angle -->
			<div id=collage>
		    	<% Random r = new Random(); %>
		    	<% for(int i = 0; i < ((ArrayList<String>)(session.getAttribute("images"))).size(); i+=1) { %> 
			            <img class="collage_image" src=<%=((ArrayList<String>)(session.getAttribute("images"))).get(i)%> style="max-width:30%; max-height:30%; object-fit: contain; transform: rotate(<%= r.nextInt(31) - 15 %>deg)">
			    <% } %>
			    
			    <div class="btn-group-vertical" id="button_stuff">
						<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
							<div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        			<a class="dropdown-item" href="#">Favorites</a>
			        			<a class="dropdown-item" href="#">To Explore</a>
			        			<a class="dropdown-item" href="#">Do Not Show</a>
			      			</div>
			      			<script>
			      				//This is a helper fuction that will help to make the dropdown menu look nicer
			      				//Specifically, the name of the list will be displayed on the button after selected
			      				var list_has_been_chosen = false;
			      				var chosen_list = "";
							     $(function(){
								    $(".dropdown-item").click(function(){					
								      $("#btnGroupVerticalDrop2").text($(this).text());
								      $("#btnGroupVerticalDrop2").val($(this).text());
								      list_has_been_chosen = true;
								      chosen_list = $(this).text();
								   });
								    
								});
							</script>
							
							<!-- Manage List Button is the button to be clicked after user has selected a list from the dropdown menu
							 And by clicking the manage list button, user will be redirected to the page that s/he selected-->
							<button class="btn btn-success" id="manage_list_button">Manage List</button>
								<!-- Redirect to the List Management Page -->
								<script type="text/javascript">
									document.getElementById("manage_list_button").onclick = function(){
										if (list_has_been_chosen) {
											var list_name = "";
											if (chosen_list == "Favorites"){
												list_name = "FAVORITES";
											}
											else if (chosen_list == "To Explore"){
												list_name = "TO_EXPLORE";
											}
											else if (chosen_list == "Do Not Show"){
												list_name = "DO_NOT_SHOW";
											}
											
											//Redirect the user to the chosen list
											location.href = "list_management_page.jsp?list_id=" + list_name;
										}
									};
								</script>
							
							<!-- Back to Search button, when clicked, will take the user back to the search page -->
							<button id="back_to_search_button" class="btn btn-success">Back to Search</button>
								<!-- Back to Search -->
								<script type="text/javascript">
									document.getElementById("back_to_search_button").onclick = function(){
										location.href = "search_page.jsp";
									};
								</script>
					</div>
			    
	    	</div>
			<div class = "text-center"> 
				<!-- This is the header for the result page -->
				<h1>Results for <%= session.getAttribute("query") %></h1>
			</div>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
		
			<div class="container">
		    <br/>
		    	<!-- Button Group for Back to Search Page and Dropdown for Predefined Lists-->	
				<div class="overall_information">
				
					<!-- The following div is an outer container div for the two tables -- restaurant results table
					and recipe results table -->
					<div class="recipe_and_restaurant_results">
					
						<!-- The following div is the container for the restaurant results table-->
						<div class="restaurant_results">
							<h2>Restaurant Results</h2>
							<table id="restaurant_results_table">
								<%
								//Getting restaurant results array list from session
								if (session.getAttribute("restaurants") == null){
									return;
								}
								ArrayList<Restaurant> list_of_restaurant_results = (ArrayList<Restaurant>)(session.getAttribute("restaurants"));
								for (int i = 0; i < list_of_restaurant_results.size(); i++){
									
									//The following will get the detailed restaurant information needed to be displayed
									Restaurant restaurant = list_of_restaurant_results.get(i);
									String restaurant_name = restaurant.getName();
									int driveTime = restaurant.getDriveTime();
									String address = restaurant.getAddress();
									%> <tr><th><a href="restaurant_page.jsp?restaurant_id=<%= i%>"><%=restaurant_name%></a> </th> <th>Drive Time: <%=driveTime%> min </th> <th><%=address %> </th> <th><%
											for (int j = 0; j < restaurant.getPriceRange(); j++ ){%>
												$
											<% } %></th></tr> <%
								}
								%>
							</table>
						</div>
					
						<!-- The following div is the container for the recipe results table -->
						<div class="recipe_results">
							<h2>Recipe Results</h2>
							<table id="recipe_results_table">
								<%
								//Getting recipe results array list from session
								if (session.getAttribute("recipes") == null){
									return;
								}
								ArrayList<Recipe> list_of_recipe_results = (ArrayList<Recipe>)(session.getAttribute("recipes"));
								for (int i = 0; i < list_of_recipe_results.size(); i++){
									
									//The following will get the detailed recipe information needed to be displayed
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
									%> <tr><th><a href="recipe_page.jsp?recipe_id=<%= i%>"><%=recipe_name%></a></th> <th>Prep Time: <%=prepTime %></th> <th>Cook Time: <%=cookTime %></th> </tr> <%
								}
								%>
							</table>
						</div>
					</div>
		        </div>
	        </div>
	    </div>
	    
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	</body>
</html>
