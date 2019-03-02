<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="classes.Recipe" %>
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
    <title>Recipe Page</title>
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
	        	<button id="back_to_results_button" class="btn btn-success">Back to Results</button> 
	        	<!-- Back to Results -->
	        	<script type="text/javascript">
				    document.getElementById("back_to_results_button").onclick = function () {
				        location.href = "restaurant_page.jsp";
				    };
				</script>
	        	<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
			      <div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        <a class="dropdown-item" href="#">Favorites</a>
			        <a class="dropdown-item" href="#">To Explore</a>
			        <a class="dropdown-item" href="#">Do Not Show</a>
			      </div>
	        	<button class="btn btn-success">Add to list</button> 
	        </div> 
        	<div class="recipe_information">
	        	<!-- <h1>Recipe Title</h1>
	        	<p>Recipe Information</p>
	        	<img src="" id="recipe_picture">
		    	<h1>Prep Time</h1>
		    	<h1>Cook Time</h1>
		    	<h1>Ingredients</h1>
		    	<h1>Instructions</h1> -->
		    	
		    	<%
		    	//Creating a new recipe object
		    	
		    		ArrayList<String> stuff_ingredients = new ArrayList<String>();
		    		ArrayList<String> stuff_instructions = new ArrayList<String>();
		    		
		    		stuff_ingredients.add("ingredient 1");
		    		stuff_ingredients.add("ingredient 2");
		    		stuff_ingredients.add("ingredient 3");
		    		
		    		stuff_instructions.add("instruction 1");
		    		stuff_instructions.add("instruction 2");
		    		stuff_instructions.add("instruction 2");
		    		
			        Recipe recipe = new Recipe("Parmesan", 1, 2, "img.url", stuff_ingredients, stuff_instructions);
		    		String recipe_name = recipe.getName();
			        int prepTime = recipe.getPrepTime();
			        String imgURL = recipe.getImgURL();
			        int cookTime = recipe.getCookTime();
			        List<String> new_ingredients = new ArrayList<>();
			        List<String> new_instructions = new ArrayList<>();
			        new_ingredients= recipe.getIngredients();
			        new_instructions = recipe.getInstructions();
			    %>
		    	<div id=recipe_title><%=recipe_name%></div>
		    	<div id=prep_time>Prep Time: <%=prepTime%></div>
		    	<div id=cook_time>Cook Time: <%=cookTime%></div>
		    	<h1>Ingredients:</h1>
		    	<%-- <div id=ingredients_for_food><%=ingredients[1]%></div> --%>
		    	<h1>Instructions</h1>
		    	<div id=instructions_for_food>loading...</div>
		    	
		    	
			    
		    	<script>
		    	/* $.ajax({
		                url: 'recipeservlet', //the mapping of your servlet
		                type: 'POST',
		                dataType: 'json',
		                data: 'JSON.stringify(recipeservlet)',
		                success: function(data) {
		                        if(data.isValid){
		                     		var obj = JSON.parse(data);
		                     		document.getElementById("recipe_title").innerHTML = obj.prepTime;
		                     		document.getElementById("prep_time").innerHTML = obj.prepTime;
		                     		document.getElementById("cook_time").innerHTML = obj.cookTime;
		                     		var ingredients = "";
		                     		for (var i = 0; i < obj.ingredients.length; i++) {
		                     			ingredients += obj.ingredients[i];
		                     		}
		                     		document.getElementById("ingredients_for_food").innerHTML = ingredients;
		                     		for (var i = 0; i < obj.instructions.length; i++) {
		                     			ingredients += obj.instructions[i];
		                     		}
		                     		document.getElementById("instructions_for_food").innerHTML = obj.instructions;
		                } else{
		                    //data is not valid so give the user some kind of alert text
		                }
	                } */
		    		/* document.getElementById("recipe_title").innerHTML = sessionStorage.getItem("recipe_title_from_results_page");
		    		document.getElementById("prep_time").innerHTML = sessionStorage.getItem("prep_time_from_results_page");
		    		document.getElementById("cook_time").innerHTML = sessionStorage.getItem("cook_time_from_results_page");
		    		document.getElementById("ingredients_for_food").innerHTML = sessionStorage.getItem("ingredients_from_results_page");
		    		document.getElementById("instructions_for_food").innerHTML = sessionStorage.getItem("instructions_from_results_page"); */
		    	</script>
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
