<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Recipe" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Restaurant" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.ListManager" %>
    <%@ page import="java.net.HttpURLConnection" %>
    <%@ page import="javax.servlet.http.HttpServlet" %>
    <%@ page import="javax.servlet.http.HttpServletRequest" %>
    <%@ page import="javax.servlet.http.HttpServletResponse" %>    
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
	
	
		<title>List Management Page</title>
	</head>
	
	<body>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/ssbootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<div class = "div_for_entire_content">
			<div> 
				<!-- "L" to be changed to the proper list name -->
				L List
			</div>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
		
			<div class="container">
			<br/>
		    	<!-- Button Group for Dropdown for Predefined Lists, Back to Results Page and Back to Search Page-->	
				<div class="overall_information">
					<div class="btn-group-vertical" id="button_stuff">
						<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
							<div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        			<a class="dropdown-item" href="#">Favorites</a>
			        			<a class="dropdown-item" href="#">To Explore</a>
			        			<a class="dropdown-item" href="#">Do Not Show</a>
			      			</div>
			      			<script>
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

											location.href = "list_management_page.jsp?list_id=" + list_name;
										}
									}
									
								</script>
							
							<button id="back_to_results_button" class="btn btn-success">Back to Results</button>
								<!-- Back to Results -->
					        	<script type="text/javascript">
								    document.getElementById("back_to_results_button").onclick = function () {
								        location.href = "results_page.jsp";
								    };
								</script>
							<button id="back_to_search_button" class="btn btn-success">Back to Search</button>
								<!-- Back to Search -->
								<script type="text/javascript">
									document.getElementById("back_to_search_button").onclick = function(){
										location.href = "search_page.jsp";
									};
								</script>
			      	</div>
			      	
			      	<div class = "list_results">
			      		<table id="list_results_table">
			      			<%
			      				//request.getParameter() to get the parameter from the url
			      				String list_name = request.getParameter("list_id");
			      				ArrayList list = null;
			      				if (list_name.equals("FAVORITES")){
			      					list = ListManager.getInstance().getFavorites();
			      				}
			      				else if (list_name.equals("TO_EXPLORE")){
			      					list = ListManager.getInstance().getToExplore();
			      				}
			      				else if (list_name.equals("DO_NOT_SHOW")){
			      					list = ListManager.getInstance().getDoNotShow();
			      				}
			      				else return;
			      				
			      				for (int i = 0; i < list.size(); i++){
			      					if (list.get(i) instanceof Recipe){
			      						Recipe recipe = (Recipe)list.get(i);
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
			      						String redirect_link = "IHManageList?list_id=" + list_name + "&action=DISPLAY&item_id=" + Integer.toString(i); 
			      						%><tr><th><a href=<%= redirect_link%>><%=recipe_name %>, </th> <th>Prep Time: <%=prepTime %></th> <th>Cook Time: <%=cookTime %></th></a>
			      						
			      						<input type="radio" value=<%=i %> name="only_one_selection">Delete <%=recipe_name %> from this List</input>
			      						</tr><%
			      					}
			      					else if (list.get(i) instanceof Restaurant){
			      						Restaurant restaurant = (Restaurant)list.get(i);
			      						String restaurant_name = restaurant.getName();
			      						int driveTime = restaurant.getDriveTime();
			      						String address = restaurant.getAddress();
			      						String redirect_link = "IHManageList?list_id=" + list_name + "&action=DISPLAY&item_id=" + Integer.toString(i); 
			      						%> <tr><th><a href=<%= redirect_link %>><%=restaurant_name %>, </th> <th>Drive Time: <%=driveTime %> min, </th> <th><%=address %></th></a>  
			      						
			      						<input type="radio" value=<%=i %> name="only_one_selection">Delete <%=restaurant_name %> from this list </input>
			      						</tr><%
			      					}
			      				}
			      			
			      			%>
			      			
			      		</table>      
			      		
			      		<button type="submit" id="delete_button">Delete the Selected Item from the list</button>
			      		<!-- Delete the Selected Item from the Current List -->
			      		<script type="text/javascript">
			      			document.getElementById("delete_button").onclick=function(){
			      				var list_name = getUrlVars()["list_id"];
			      				var radios = document.getElementsByTagName('input');
			      				var item_index;
			      				for (var i = 0; i < radios.length; i++) {
			      				    if (radios[i].type === 'radio' && radios[i].checked) {
			      				        // get the item_index from the radio button input
			      				        item_index = radios[i].value;       
			      				    }
			      				}
			      				var redirect_link = "IHManageList?list_id=" + list_name + "&action=REMOVE&item_id=" + item_index.toString();
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
			      	</div>
				
				
				</div>
			
			</div>
		</div>
	</body>
</html>