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
	  <link href="https://fonts.googleapis.com/css?family=Archivo+Black|Judson:400,700" rel="stylesheet">
	  <link rel="stylesheet" type="text/css" href="search_page.css" />
	<!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		  <!-- Title -->
	  <title>Search Page</title>
  </head>
  <body>
    	<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/ssbootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script> -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<div class = "div_for_entire_content">
		<!-- I'm Hungry Title -->
		<div class = "text-center"> I'm Hungry </div>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
		<!-- Container class-->
		<div class="container">
		
		<% User currUser = (User) request.getAttribute("user"); 
			if (currUser != null)
				System.out.println(currUser.getName());
		%>
	    	<br/>
			<!-- Using bootstrap for the search form -->
			<div class="row justify-content-center">
				<form id="feed_me_form" class="form-inline" action="IHSearch" name="only_form">
					 <div class="input_group"> 
						<input class="form-control form-control-lg form-control-borderless" type="text" placeholder="Enter Food" name="search_query" required style="height: 38px;" />
						<span></span>
						<input class="form-control form-control-borderless" id="ex2" data-toggle="tooltip" title="Number of items to show in results" data-placement="right" type="number" min="1" value="5" name="num_results" >
						<input class="form-control form-control-borderless" id="ex3" data-toggle="tooltip" title="Search radius in miles" data-placement="right" type="number" min="1" value="5" name="num_miles" > 
					 </div>
				</form> 
			</div>
	       	<br>
	        <!-- Submit/Login/Sign-up buttons -->
	       	<div class="form-actions"> 
			<!-- <button id="submit_button" class="btn btn-lg btn-success" type="submit">Feed Me!</button>  -->
			<!-- <button id="login_button" class="btn btn-lg btn-success" type="submit" data-toggle="modal" data-target="#loginModal">Login</button>  -->
			<button id="submit_button" class="btn btn-lg btn-dark" type="submit">Feed Me!</button> 
			<div class="row">
				<div class="col-12 text-center py-2">
				    	<a href="#loginModal" role="button" class="btn btn-lg btn-dark" data-toggle="modal">Login</a>
           				<a href="#signupModal" role="button" class="btn btn-lg btn-dark" data-toggle="modal">Sign Up</a>
				</div>
		    	</div>
			<div class="container">
			</div>
			<!-- Onclick function for the submit button -->
			<script type="text/javascript">
			    document.getElementById("submit_button").onclick = function () {
				if(document.forms['only_form'].search_query.value === "") {
						alert("Please enter a search term.");
						return false;
				}
				document.getElementById("feed_me_form").submit();
				//location.href = "results_page.jsp";
			    };
			</script>
	       	</div>
		<!-- Function for the hovertext -->
	       	<script>
			$(document).ready(function(){
			  $('[data-toggle="tooltip"]').tooltip(); 
			});
		</script>
		</div>
	</div>
	
<!-- register modal	  -->
	<div id="signupModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h3>Sign Up</h3>
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body">
	                <form class="form" role="form" autocomplete="off" id="formSignUp" novalidate="" method="POST" action="IHLogin">
	                	<input type="hidden" name="action" value="SignUp" />
	                    <div class="form-group">
	                        <!-- <a href="" class="float-right">New user?</a> -->
	                        <label for="uname1">Username</label>
	                        <input type="text" class="form-control form-control-lg" name="uname_signup" id="uname1" required="">
	                        <div class="invalid-feedback">Oops, you missed this one.</div>
	                    </div>
	                    <div class="form-group">
	                        <label>Password</label>
	                        <input type="password" class="form-control form-control-lg" name="password_signup" id="pwd_register" required="" autocomplete="new-password">
	                        <div class="invalid-feedback">Enter your password too!</div>
	                    </div>
	                    <div class="form-group">
	                        <label>Verify Password</label>
	                        <input type="password" class="form-control form-control-lg" name="verify_uname_signup" id="pwd_verify_register" required="" autocomplete="new-password">
	                        <div class="invalid-feedback">Enter your password again!</div>
	                    </div>
	                    <div class="form-group py-4">
	                        <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true">Cancel</button>
	                        <button type="submit" class="btn btn-dark btn-lg float-right" id="btnSignUp">Sign Up</button>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
<!-- end register modal -->
	  
<script type="text/javascript">
    	document.getElementById("btnSignUp").onclick = function () {
	document.getElementById("formSignUp").submit();
    };
</script>
		  
<!-- login modal -->
	<div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h3>Login</h3>
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body">
	                <form class="form" role="form" autocomplete="off" id="formLogin" novalidate="" method="POST" action="IHLogin">
				<input type="hidden" name="action" value="LogIn" />
	                    <div class="form-group">
	                        <!-- <a href="" class="float-right">New user?</a> -->
	                        <label for="uname1">Username</label>
	                        <input type="text" class="form-control form-control-lg" name="uname_login" id="uname_login_id" required="">
	                        <div class="invalid-feedback">Oops, you missed this one.</div>
	                    </div>
	                    <div class="form-group">
	                        <label>Password</label>
	                        <input type="password" class="form-control form-control-lg" name="password_login" id="password_login_id" required="" autocomplete="new-password">
	                        <div class="invalid-feedback">Enter your password too!</div>
	                    </div>
	                    <div class="form-group py-4">
	                        <button class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true">Cancel</button>
	                        <button type="submit" class="btn btn-dark btn-lg float-right" id="btnLogin">Login</button>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
<!-- end login modal -->

<script type="text/javascript">
    	document.getElementById("btnLogin").onclick = function () {
	document.getElementById("formLogin").submit();
    };
</script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
	 <script> 
		//Restricts input for the given textbox 
		 function setInputFilter(textbox, inputFilter) {
		   ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function(event) {
		     textbox.addEventListener(event, function() {
		       if (inputFilter(this.value)) {
		         this.oldValue = this.value;
		         this.oldSelectionStart = this.selectionStart;
		         this.oldSelectionEnd = this.selectionEnd;
		       } else if (this.hasOwnProperty("oldValue")) {
		         this.value = this.oldValue;
		         this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
		       }
		     });
		   });
		 }
		// Restrict input to integer>=1
		 setInputFilter(document.getElementById("ex2"), function(value) {
		   return /^\d*$/.test(value) && (value === "" || parseInt(value) >= 1);
		 });
		 setInputFilter(document.getElementById("ex3"), function(value) {
			   return /^\d*$/.test(value) && (value === "" || parseInt(value) >= 1);
		 });
	 </script> 
</html>
