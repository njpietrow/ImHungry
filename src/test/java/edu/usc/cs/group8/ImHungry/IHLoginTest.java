package edu.usc.cs.group8.ImHungry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IHLoginTest {
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock 
	HttpSession session;
	
	@Mock 
	RequestDispatcher RD;
	
	@Mock 
	IHLogin LG;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	void testLogIn() throws ServletException, IOException {
		when(request.getParameter("username")).thenReturn("GJHalfond");
		when(request.getParameter("password")).thenReturn("Scrum");
		when(request.getParameter("action")).thenReturn("LogIn");
		LG.doGet(request,response);
		
		when(request.getParameter("username")).thenReturn("GJHalfond");
		when(request.getParameter("password")).thenReturn("Waterfall");
		when(request.getParameter("action")).thenReturn("LogIn");
		LG.doGet(request,response);
	}
	
	@Test
	void testSignUp() throws ServletException, IOException {
		when(request.getParameter("username")).thenReturn("GJHalfond");
		when(request.getParameter("password")).thenReturn("Scrum");
		when(request.getParameter("action")).thenReturn("SignUp");
		LG.doGet(request,response);
		
		when(request.getParameter("username")).thenReturn("CHailey");
		when(request.getParameter("password")).thenReturn("imn0ttellingy0umypassw0rd");
		when(request.getParameter("action")).thenReturn("SignUp");
		LG.doGet(request,response);
	}
	
	@Test
	void testLogOut() throws ServletException, IOException {
		when(request.getParameter("action")).thenReturn("LogOut");
		LG.doGet(request,response);
	}

}
