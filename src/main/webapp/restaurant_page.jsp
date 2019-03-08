<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Restaurant" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.ListManager" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="restaurant_page.css" />

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
	    	<div class="btn-group-vertical" id="button_stuff">
	        	<button class="btn btn-success" onclick="PrintPreview()">Printable Version</button> 
	        	<button id="back_to_results_button" class="btn btn-success">Back to Results</button> 
	        	<!-- Back to Results -->
	        	<script type="text/javascript">
				    document.getElementById("back_to_results_button").onclick = function () {
				        location.href = "results_page.jsp";
				    };
			</script>
			<!-- Standardized buttons -->
	        	<button id="btnGroupVerticalDrop2" type="button" class="btn btn-secondary dropdown-toggle btn-success" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
			      <div class="dropdown-menu" aria-labelledby="btnGroupVerticalDrop2">
			        <a class="dropdown-item" href="#">Favorites</a>
			        <a class="dropdown-item" href="#">To Explore</a>
			        <a class="dropdown-item" href="#">Do Not Show</a>
			      </div>
				<!-- Populate the empty dropdown list title with the list that the user selects -->
			      <script>
				      $(function(){
					    $(".dropdown-item").click(function(){					
					      $("#btnGroupVerticalDrop2").text($(this).text());
					      $("#btnGroupVerticalDrop2").val($(this).text());
					   });
					});
			      </script>
				<!-- Add to list button -->
				<button class="btn btn-success" id="add_to_list_button">Add to list</button> 
				<script>
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
						location.href = "IHManageList?action=ADD&list_id=" + list_id + "&recipe_id=&restaurant_id=" + <%=request.getParameter("restaurant_id") %>;	
					    };
				</script>
	        </div> 
        	<div class="restaurant_information">
		    	<%
		    	//Creating a new recipe object
			        Restaurant restaurant = new Restaurant("","");
		    		
				//get the appropriate restaurant id
		    		if (request.getParameter("restaurant_id") != null && !request.getParameter("restaurant_id").equals("")){
		    			int index = Integer.parseInt(request.getParameter("restaurant_id"));
			    		if (session.getAttribute("restaurants") == null){
			    			return;
			    		}
			    		restaurant = ((ArrayList<Restaurant>)(session.getAttribute("restaurants"))).get(index);
		    		}
		    		//get the appropriate list id
		    		else if (request.getParameter("list_id") != null && !request.getParameter("list_id").equals("")){
		    			int index = Integer.parseInt(request.getParameter("item_id"));
		    			if (request.getParameter("list_id").equals("FAVORITES")){
		    				restaurant = (Restaurant)ListManager.getInstance().getFavorites().get(index);
		    			}
		    			if (request.getParameter("list_id").equals("TO_EXPLORE")){
		    				restaurant = (Restaurant)ListManager.getInstance().getToExplore().get(index);
		    			}
		    			if (request.getParameter("list_id").equals("DO_NOT_SHOW")){
		    				restaurant = (Restaurant)ListManager.getInstance().getDoNotShow().get(index);
		    			}
		    		}
				//get all required information from the restaurant object
		    		String restaurant_name = restaurant.getName();
			        int drive_time = restaurant.getDriveTime();
			        String website_URL = restaurant.getWebsiteURL();
			        String map_url = restaurant.getMapURL();
			        String restaurant_address = restaurant.getAddress();
			        String restaurant_phone_num = restaurant.getPhoneNum();
			        int price_range = restaurant.getPriceRange();
			    %>
		    	<div id=restaurant_name><%=restaurant_name%></div>
		    	<br>
			<!-- Link to Google Maps -->
		    	<div id=restaurant_address>
		    		<a href=<%=map_url%> target="_blank"><%=restaurant_address%></a>
	    		</div>
		    	<div id=restaurant_phone_number><%=restaurant_phone_num%></div>
			<!-- Link to appropriate website URL -->
		    	<div id=restaurant_website>
		    		<a href=<%=website_URL%> target="_blank"><%=website_URL%></a>
	    		</div>
        	</div>
	    </div>
	</div>
  	<!-- End Container Class -->	
	<script>
	//Print preview function for users
    	function PrintPreview() {
    		window.print();
        }
   	</script>
	<!-- Hides buttons on print preview call -->
   	<style type="text/css">
		@media print {
		    .btn-group-vertical {
		        display :  none;
		    }
		}
	</style>
	<!-- More Required Links -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
