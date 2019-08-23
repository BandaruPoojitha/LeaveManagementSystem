package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import Hibernate.LoginValidation;
import beanclasses.Department;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (request.getParameter("value").equals("1")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddEmployee.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").equals("2")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddDepartment.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").equals("3")) {

			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource1/")
					.path("getDepartment");
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			ArrayList<String> response1 = invocationBuilder.get().readEntity(ArrayList.class);
			session.setAttribute("ids", response1);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewDepartment.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").equals("4")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditEmployee.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO A
		doGet(request, response);
	}

}
