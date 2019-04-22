<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Recipe" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.User" %>
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
  <!-- navbar -->
  <jsp:include page="modules/nav_bar.jsp" />
		
  <% Recipe recipe = new Recipe("","","","",new ArrayList<String>(),new ArrayList<String>()); 
  
		    	//Creating a new recipe object
		    		
		    		
				//grab the recipe id
		    		if (request.getParameter("recipe_id") != null && !request.getParameter("recipe_id").equals("")){
		    			String id = request.getParameter("recipe_id");
		    			User currUser;
		    			if ((session.getAttribute("user")) != null){
		    				currUser = (User)(session.getAttribute("user"));
		    			}
		    			else currUser = new User();
			    		recipe = (Recipe)(currUser.get(id));
		    		}
		    		//grab the item id
		    		else if (request.getParameter("list_id") != null && !request.getParameter("list_id").equals("")){
		    			int index = Integer.parseInt(request.getParameter("item_id"));
		    			if (request.getParameter("list_id").equals("FAVORITES")){
		    				recipe = (Recipe)((User)(session.getAttribute("user"))).getLists().getFavorites().get(index);
		    			}
		    			if (request.getParameter("list_id").equals("TO_EXPLORE")){
		    				recipe = (Recipe)((User)(session.getAttribute("user"))).getLists().getToExplore().get(index);
		    			}
		    			if (request.getParameter("list_id").equals("DO_NOT_SHOW")){
		    				recipe = (Recipe)((User)(session.getAttribute("user"))).getLists().getDoNotShow().get(index);
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
		    		
		    		String token = "";
		    		for (int i = 0; i < recipe.getURL().length(); i++){
		    			if (recipe.getURL().charAt(i) == ' '){
		    				token += "%20";
		    			}
		    			else if (recipe.getURL().charAt(i) == ':'){
		    				token += "%3A";
		    			}
		    			else if (recipe.getURL().charAt(i) == '/'){
		    				token += "%2F";
		    			}
		    			else if (recipe.getURL().charAt(i) == '-'){
		    				token += "%2D";
		    			}
		    			else token += recipe.getURL().charAt(i);
		    			
		    		}
			    %>
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
	        	<button class="btn btn-dark" onclick="PrintPreview()">Printable Version</button> 
	        	<button id="back_to_results_button" class="btn btn-dark">Back to Results</button> 
	        	<!-- Back to Results -->
	        	<script type="text/javascript">
				    document.getElementById("back_to_results_button").onclick = function () {
				        location.href = "results_page.jsp";
				    };
				</script>
			<!-- Dropdown list elements, Favorites, To Explore, and Do Not Show -->
	        	<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-dark" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
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
	        	<button class="btn btn-dark" id="add_to_list_button">Add to list</button> 
	        	<script>
				//function to set list id to the appropriate list if selected
		        	 document.getElementById("add_to_list_button").onclick = function () {
						console.log("I got clicked on!");
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
				        var recipe_id = "<%=token%>";
					 //send to servlet
				        location.href = "IHManageList?action=ADD&list_id=" + list_id + "&recipe_id='"+ recipe_id + "'&restaurant_id=";	
				    };
	        	</script>
	        	
	        	<button class="btn btn-dark" id="grocery_list_button" >Add to Grocery List</button>
	        </div> 
		<!-- Use the recipe servlet to use the relevant information -->
        	<div class="recipe_information" id="all_stuff_on_page">	

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
		    	
		    	<% 
		    		if(stuff_ingredients.size() == 0) {
		    			%> 
		    			<script type="text/javascript">
			    			document.getElementById('ingredients_unordered').style.display = 'none';
		    			</script>
	    			<% } %> 
		    	
			<!-- Printing out ingredients in unordered list using java -->
		    	<% for(int i = 0; i < stuff_ingredients.size(); i+=1) { %> 
			            <ul>
			            	<li><%=stuff_ingredients.get(i)%></li>
			            </ul>
			    <% } %>
			    
		    	</div>
			<!-- Printing out instructions in ordered list using java -->
		    	<div id=instructions_for_food>Instructions:
		    		<% 
		    		if(stuff_instructions.size() == 0) {
		    			%> 
		    			<script type="text/javascript">
			    			document.getElementById('instructions_for_food').style.display = 'none';
		    			</script>
		    	<% } %>
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
	    <div style="clear:both;"></div>
	</div>
  	<!-- End Container Class -->	
	  
	<!-- add grocery to grocery list -->
<script type = "text/javascript">
						
			
			document.getElementById("grocery_list_button").onclick=function(){
     				var recipe_url = getUrlVars()["recipe_id"];
     				var radios = document.getElementsByTagName('input');
     				var item_index;
    
     				//send the REMOVE request to the backend servlet and let the backend deal with the remove logic and session storage.
     				var redirect_link = "IHGroceryList?action=ADD&recipe_url=" + recipe_url;
     				location.href = redirect_link;
     		}
			function getUrlVars() {
  			    var vars = {};
  			    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
  			        vars[key] = value;
  			    });
  			    return vars;
  			}
					
					
		</script>
	<!-- More Required Links -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
