 <%@ page import="edu.usc.cs.group8.ImHungry.Query" %>
 <%@ page import="edu.usc.cs.group8.ImHungry.User" %>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="java.util.*" %>
 

<div class = "text-center"> 
	<!-- This is the header for the result page -->
	<br><br>
	<h1>Recent Queries</h1>
</div>
 
<div id=recent_collage>
   <!--  iterate through recent searches -->
   <% 
 	//Getting query list from session
 	User person =  ((User)(session.getAttribute("user")));
	ArrayList<Query> query_results = person.getQuickAccess();

	//if no items in grocery list
	if(query_results.size() == 0)
	{
		%>  <p> No Items in query list</p>  <% 
		
		return;
	}
	
	
	
	for (int i = 0; i < query_results.size(); i++){
		//display each query
		%> <div class = "query_container">  <%
		
		//The following will get the detailed quick access information needed to be displayed
		Query curr = query_results.get(i);
		ArrayList<String> imgs = curr.getImages();
		String query = (String)(query_results.get(i).getKeyword());
		String numResults = (String)(query_results.get(i).getNumResults());
		String radius = (String)(query_results.get(i).getRadius());
		System.out.println(query);
	
		//display search query collages
		%><div class=collage_container><%
		
		Random r = new Random(); 
		
		for(int k = 0; k <imgs.size(); k++) { 
		%> 
			<img class="collage_sm" src=<%=imgs.get(k)%> style="max-width:30%; max-height:30%; object-fit: contain; transform: rotate(<%= r.nextInt(31) - 15 %>deg)">
		<% 
		
		} 
		%></div>
		
		<div style="clear:both;"></div>
		<div class="q_info_container">
		<!-- IHSearch?search_query=chicken&num_results=5&num_miles=5 -->
			<a href="IHSearch?search_query=<%=query%>&num_results=<%=numResults%>&num_miles=<%=radius%>" style="text-decoration:none;"> 
			<div class = "query_info">Search Term: <%=query %></div>
			<div class = "query_info">Num Results: <%=numResults %></div>
			<div class = "query_info">Radius: <%=radius %></div>
			</a>
		</div>
		
		
		</div><% 
				
	} %>

	<div style="clear:both;"></div>
</div>