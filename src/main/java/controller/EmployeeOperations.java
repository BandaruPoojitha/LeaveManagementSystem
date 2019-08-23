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

/**
 * Servlet implementation class EmployeeOperations
 */
public class EmployeeOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeOperations() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		if (request.getParameter("value").equals("1")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApplyLeave.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").equals("2")) {
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/")
					.path("getLeaves");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			ArrayList<String> response1 = invocationBuilder.get().readEntity(ArrayList.class);

			session.setAttribute("ids", response1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("GrantLeave.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").contentEquals("3")) {
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/")
					.path("cancelLeaves");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			ArrayList<String> response1 = invocationBuilder.get().readEntity(ArrayList.class);
			session.setAttribute("cancel", response1);
			System.out.print(session.getAttribute("cancel"));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("CancelLeave.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").equals("4")) {
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/")
					.path("trackLeaves");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			ArrayList<String> response1 = invocationBuilder.get().readEntity(ArrayList.class);
			session.setAttribute("track", response1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("TrackMyLeave.jsp");
			requestDispatcher.forward(request, response);
		} else if (request.getParameter("value").equals("5")) {

			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/")
					.path("leaveBalance");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			ArrayList<String> response1 = invocationBuilder.get().readEntity(ArrayList.class);
			session.setAttribute("leavebal", response1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LeaveBalance.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
