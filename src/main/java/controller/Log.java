package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import beanclasses.Bean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		if (password.isEmpty()) {
			request.setAttribute("Error", "Error Occured");
			RequestDispatcher rs = request.getRequestDispatcher("Login.jsp");
			rs.forward(request, response);
		}
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(
				"http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource/" + username + "/" + password);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		ArrayList<String> list = invocationBuilder.get().readEntity(ArrayList.class);
		if (list != null) {
			Bean b = new Bean();
			b.setEmployeeId(list.get(0));
			if (list.get(1).equals("admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("frame.html");
				rd.forward(request, response);
			} else if (list.get(1).contentEquals("employee")) {
				Bean bean = new Bean();
				bean.setEmployeeId(list.get(0));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("frame1.html");
				requestDispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("Error", "Error Occured");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
			requestDispatcher.forward(request, response);

		}
		// doGet(request, response);
	}

}
