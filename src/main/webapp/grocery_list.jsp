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
    <%@ page import="edu.usc.cs.group8.ImHungry.User" %>
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
		
		<!-- begin of body -->
		<div id="mainContent">
			<div id="title">Grocery List</div>
			<div id="gListWrap">
				<ul id="completeGList">
				<li>item</li>
					
					<script type = "text/javascript">
					
					
					
					
					
					document.getElementById("delete_button").onclick=function(){
	      				var ingredient = getUrlVars()["ingredient"];
	      				var recipe_url = getUrlVars()["recipe_url"];
	      				var radios = document.getElementsByTagName('input');
	      				var item_index;
	      				for (var i = 0; i < radios.length; i++) {
	      				    if (radios[i].type === 'radio' && radios[i].checked) {
	      				        // get the item_index from the radio button input
	      				        item_index = radios[i].value;       
	      				    }
	      				}
	      				//send the REMOVE request to the backend servlet and let the backend deal with the remove logic and session storage.
	      				var redirect_link = "IHGroceryList?ingredient=" + ingredient + "&action=REMOVE&recipe_url=" + recipe_url;
	      				location.href = redirect_link;
	      			}
					
					
					</script>
					
				<%-- 	<%
					//Getting grocery list from session
					User curr = ((User)(session.getAttribute("currUser")));
					
					if (curr.getGroceries() == null){
						
						return;
					}  
					ArrayList<String> grocery_results = (ArrayList<String>)(curr.getGroceries());
					for (int i = 0; i < grocery_results.size(); i++){
						
						//The following will get the detailed restaurant information needed to be displayed
						String ingredient = grocery_results.get(i);
						
						%><li> <%= ingredient %><li><% 
					}
					%> --%>
				</ul>
				<!-- end of coompleteGList -->
			
			</div>
			<!-- end of gListWrap -->
			
		</div>
		<!-- end of mainCntent -->
		
		
	</body>
</html>
