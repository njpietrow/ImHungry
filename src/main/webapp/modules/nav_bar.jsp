<!-- begin of nav  -->
	
<% 
String username = (String) session.getAttribute("username"); 
if (username == null || username == "")
	username = "Guest";
%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <a class="navbar-brand" href="search_page.jsp">I'm Hungry</a>
	
	  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
	    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
	      <li class="nav-item active">
	     
	      	<a id="back_to_results_button" href="">Back to Results</a> 
	        <a class="nav-link" id="gListLink" href="grocery_list.jsp">Grocery List <span class="sr-only">(current)</span></a>
	      </li>
	
	    </ul>
	    
	     <span class="navbar-text">
	     
	     <!-- display logout if logged in -->
	     <% 
	     if ( username != "Guest")
	     {
	    	 %> <a id="logout" href="IHLogin?action=LogOut">Logout </a> <% 
	    	 
	     }  
	     %>
	    
     	 Welcome, <%=username %>
    	</span>
	  </div>
	</nav>
<!-- end of nav -->