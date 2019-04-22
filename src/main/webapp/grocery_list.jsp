<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Recipe" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.Restaurant" %>
    <%@ page import="edu.usc.cs.group8.ImHungry.User" %>
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
	    <link rel="stylesheet" type="text/css" href="grocery_list.css" />
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/ssbootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
		
		<title>Grocery List Page</title>
	</head>
	
	<body>
		<!-- navbar -->
		<jsp:include page="modules/nav_bar.jsp" />
		
		<div id="buttonContainer">
			
				<div class="btn-group-vertical" id="button_stuff">
		        	<button class="btn btn-dark" onclick="PrintPreview()">Printable Version</button> 
		        	<script>
			    	function PrintPreview() {
			    		window.print();
			    		/* var div = document.getElementById('button_stuff');
			    	    div.style.display = 'none'; */
			        }
			</script>
		        	
		        	<button id="back_to_results_button" class="btn btn-dark">Back to Results</button> 
		        	<!-- Back to Results -->
		        	<script type="text/javascript">
				    document.getElementById("back_to_results_button").onclick = function () {
				        location.href = "results_page.jsp";
				    };
				</script>
				     
		        	<!--  <button class="btn btn-dark" id="add_to_list_button">Add to list</button> 
		        	
		        	
		        	<button class="btn btn-dark" id="grocery_list_button" >Add to Grocery List</button>
		       		-->
		       		
		       		<button id="delete_button">Delete the Selected Item from the list</button>
			      		<!-- The following JavaScript function deals with Deleting the Selected Item from the Current List -->
			      		<script type="text/javascript">
			      			document.getElementById("delete_button").onclick=function(){
			      				var radios = document.getElementsByTagName('input');
			      				var item_index;
			      				for (var i = 0; i < radios.length; i++) {
			      				    if (radios[i].type === 'radio' && radios[i].checked) {
			      				        // get the item_index from the radio button input       
			      				        item_index = radios[i].value;       
			      				    }
			      				}
			      				//send the REMOVE request to the backend servlet and let the backend deal with the remove logic and session storage.
			      				
			      				var redirect_link = "IHGroceryList?action=REMOVE&ingredient=" + item_index.toString();
			      				location.href = redirect_link;
			      			}
			      		</script> 
		       		
		        </div> 
	        
			</div>
			
		
		<!-- begin of body -->
		<div id="mainContent">
			<div id="title">Grocery List</div>
			<div id="gListWrap">
				<ul id="completeGList">
					
					<% 
					//Getting grocery list from session
				
				
					ArrayList<String> grocery_results = ((User)(session.getAttribute("user"))).getGroceries();
				
					//if no items in grocery list
					if(grocery_results.size() == 0)
					{
						%>  <p> No Items in grocery list</p>  <% 
						
						return;
					}
					
					for (int i = 0; i < grocery_results.size(); i++){
						
						//The following will get the detailed restaurant information needed to be displayed
						String ingredient = (String)(grocery_results.get(i));
						System.out.println(ingredient);
				
						%><li> <label> <input type="radio" name="gItem_select" value = <%= i %>>  <%= ingredient%> </label></li><% 
					}
				 %> 
				 
				</ul>
			
			
			</div>
			<!-- end of gListWrap -->
			
		
			
	        <!-- end of button group -->
		</div>
		
		<!-- end oof mainCntent -->

	</body>
</html>