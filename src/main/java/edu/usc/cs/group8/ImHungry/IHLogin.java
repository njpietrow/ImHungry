package edu.usc.cs.group8.ImHungry;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IHServlet
 */

@WebServlet("/IHLogin")
public class IHLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IHLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		LoginHelper l = new LoginHelper();
		if (action.equals("LogOut")) {
			User currUser = new User();
			try {
				currUser = (User)request.getSession().getAttribute("user");
			} catch (Exception e) {
				return;
			}
			l.logout(currUser);
			request.getRequestDispatcher("search_page.jsp").forward(request, response);
		}
		if (action.equals("LogIn")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User currUser = new User();
			if (l.login(username, password, currUser)) {
				request.setAttribute("user", currUser);
				request.getRequestDispatcher("search_page.jsp").forward(request, response);
			} else {
				response.setStatus(response.SC_BAD_GATEWAY);
				response.getWriter().println("Incorrect password.");
				response.getWriter().flush();
			}
		}
		if (action.equals("SignUp")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			if (!password.equals(password2)) {
				response.setStatus(response.SC_BAD_GATEWAY);
				response.getWriter().println("Passwords do not match.");
				response.getWriter().flush();
			}
			else {
				User currUser = new User();
				if (l.login(username, password, currUser)) {
					request.setAttribute("user", currUser);
					request.getRequestDispatcher("search_page.jsp").forward(request, response);
				} else {
					response.setStatus(response.SC_BAD_GATEWAY);
					response.getWriter().println("Account with that name already exists.");
					response.getWriter().flush();
				}
			}
		}
	}


}
