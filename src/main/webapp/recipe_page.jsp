<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Recipe" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.ListManager" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="recipe_page.css" />
    <!-- <link rel="stylesheet" type="text/css" href="print.css" /> -->

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Recipe Page</title>
  </head>
  <body id="print_preview">
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
	    	<div class="btn-group-vertical" id="button_stuff">
	        	<button class="btn btn-success" onclick="PrintPreview()">Printable Version</button> 
	        	<button id="back_to_results_button" class="btn btn-success">Back to Results</button> 
	        	<!-- Back to Results -->
	        	<script type="text/javascript">
				    document.getElementById("back_to_results_button").onclick = function () {
				        location.href = "results_page.jsp";
				    };
				</script>
			<!-- Dropdown list elements, Favorites, To Explore, and Do Not Show -->
	        	<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
			      <div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        <a class="dropdown-item" href="#">Favorites</a>
			        <a class="dropdown-item" href="#">To Explore</a>
			        <a class="dropdown-item" href="#">Do Not Show</a>
			      </div>
				<!-- Function to populate the empty dropdown list title with the list that is selected-->
			      <script>
				      $(function(){
					    $(".dropdown-item").click(function(){					
					      $("#btnGroupVerticalDrop2").text($(this).text());
					      $("#btnGroupVerticalDrop2").val($(this).text());
					   });
					});
			      </script>
	        	<button class="btn btn-success" id="add_to_list_button">Add to list</button> 
	        	<script>
				//function to set list id to the appropriate list if selected
		        	 document.getElementById("add_to_list_button").onclick = function () {
	        		 	var list_id;
				        if($("#btnGroupVerticalDrop2").text() =="Favorites") {
				        	list_id = "FAVORITES";
				        }
				        else if($("#btnGroupVerticalDrop2").text() =="To Explore") {
				        	list_id = "TO_EXPLORE";
				        }
				        else {
				        	list_id = "DO_NOT_SHOW"; 
				        }
					 //send to servlet
				        location.href = "IHManageList?action=ADD&list_id=" + list_id + "&recipe_id="+<%=request.getParameter("recipe_id")%>+"&restaurant_id=";	
				    };
	        	</script>
	        </div> 
		<!-- Use the recipe servlet to use the relevant information -->
        	<div class="recipe_information" id="all_stuff_on_page">	
		    	<%
		    	//Creating a new recipe object
		    		Recipe recipe = new Recipe("","","","",new ArrayList<String>(),new ArrayList<String>());
		    		
				//grab the recipe id
		    		if (request.getParameter("recipe_id") != null && !request.getParameter("recipe_id").equals("")){
		    			int index = Integer.parseInt(request.getParameter("recipe_id"));
			    		if (session.getAttribute("recipes") == null){
			    			return;
			    		}
			    		recipe = ((ArrayList<Recipe>)(session.getAttribute("recipes"))).get(index);
		    		}
		    		//grab the item id
		    		else if (request.getParameter("list_id") != null && !request.getParameter("list_id").equals("")){
		    			int index = Integer.parseInt(request.getParameter("item_id"));
		    			if (request.getParameter("list_id").equals("FAVORITES")){
		    				recipe = (Recipe)ListManager.getInstance().getFavorites().get(index);
		    			}
		    			if (request.getParameter("list_id").equals("TO_EXPLORE")){
		    				recipe = (Recipe)ListManager.getInstance().getToExplore().get(index);
		    			}
		    			if (request.getParameter("list_id").equals("DO_NOT_SHOW")){
		    				recipe = (Recipe)ListManager.getInstance().getDoNotShow().get(index);
		    			}
		    		}
		    		
				//use the recipe name from the servlet
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
				//display image
			        String imgURL = recipe.getImgURL();
			        ArrayList<String> stuff_ingredients = recipe.getIngredients();
		    		ArrayList<String> stuff_instructions = recipe.getInstructions();
			    %>
		    	<div id=recipe_title><%=recipe_name%></div>
		    	<br></br>
		   	<div id=image_url_div>
				<img id="actual_image_link" src=<%=imgURL%> alt="Recipe_Image"></div>
		    	<br></br>
		    	<br></br>
		    	<div id=prep_time>Prep Time: <%=prepTime%></div>
		    	<div id=cook_time>Cook Time: <%=cookTime%></div>
		    	<br></br>
	
		    	<div id=ingredients_unordered>Ingredients: 
		    	
			<!-- Printing out ingredients in unordered list using java -->
		    	<% for(int i = 0; i < stuff_ingredients.size(); i+=1) { %> 
			            <ul>
			            	<li><%=stuff_ingredients.get(i)%></li>
			            </ul>
			    <% } %>
			    
		    	</div>
			<!-- Printing out instructions in ordered list using java -->
		    	<div id=instructions_for_food>Instructions:
		    		<% for(int i = 0; i < stuff_instructions.size(); i+=1) { %> 
			            	<ul><%=i+1 + ". " + stuff_instructions.get(i)%></ul>
			    <% } %>
		    	</div>
		    	
		    	<script>
			    	function PrintPreview() {
			    		window.print();
			    		/* var div = document.getElementById('button_stuff');
			    	    div.style.display = 'none'; */
			        }
			</script>
			   	<style type="text/css">
					@media print {
					    .btn-group-vertical {
					        display :  none;
					    }
					}
				</style>
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
