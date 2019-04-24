 <%@ page import="edu.usc.cs.group8.ImHungry.Query" %>
 <%@ page import="java.util.ArrayList" %>
 
<div id=recent_collage>
   <!--  iterate through recent searches -->
   <% 
 	//Getting query list from session
	ArrayList<Query> query_results = ((User)(session.getAttribute("user"))).getQuickAccess();

	//if no items in grocery list
	if(query_results.size() == 0)
	{
		%>  <p> No Items in query list</p>  <% 
		
		return;
	}
	
	for (int i = 0; i < query_results.size(); i++){
		
		//The following will get the detailed restaurant information needed to be displayed
		String query = (String)(query_results.get(i).getKeyword());
		System.out.println(query);

		%> <p> <%= query %></p> <% 
	}
%> 
   
   
   %>
</div>