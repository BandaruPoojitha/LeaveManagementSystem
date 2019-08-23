package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import beanclasses.ApplyLeave;
import beanclasses.Bean;
import beanclasses.Employee;
import validation.LeaveValidation;

/**
 * Servlet implementation class Employees
 */
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Employees() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date date2=null;
		Date date1=null;
		// TODO Auto-generated method stub
		ApplyLeave applyLeave = new ApplyLeave();
		Employee employee = new Employee();
		Bean bean = new Bean();
		employee.setEmployeeId(bean.getEmployeeId());
		applyLeave.setEmployeeId(employee);
		applyLeave.setReason(request.getParameter("reason"));
		System.out.println(request.getParameter("LeaveType") + "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		applyLeave.setLeaveType(request.getParameter("LeaveType"));
		try {
			 date1 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startdate"));
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enddate"));
			// Date sd=new Date();
			System.out.println("date: " + request.getParameter("startdate"));

			applyLeave.setEnddate(date2);
			applyLeave.setStartdate(date1);
			System.out.println("date: " + date1);
		} catch (Exception e) {
			System.out.println(e);
		}
		int year= date1.getYear()+1900;
		System.out.println((year+"   "+ LocalDate.now().getYear()));
		if ((year == LocalDate.now().getYear())) {
			Client client = ClientBuilder.newClient();

			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/")
					.path("managerId");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			String response1 = invocationBuilder.get().readEntity(String.class);
			System.out.println("ManagerId   " + response1);

			String applyTo = response1;
			Employee employee1 = new Employee();
			employee1.setEmployeeId(applyTo);
			applyLeave.setApplyTo(employee1);

			LeaveValidation leaveValidation = new LeaveValidation();

			String str = applyLeave.getLeaveType();
			if (str.contentEquals("Paid")) {

				Client client1 = ClientBuilder.newClient();
				WebTarget webTarget1 = client1
						.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/getPaid");

				Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
				String response2 = invocationBuilder1.get().readEntity(String.class);

				if (!leaveValidation.leaveValid(applyLeave.getLeaveType(), applyLeave.getStartdate(),
						applyLeave.getEnddate(), applyLeave.getApplyTo(), applyLeave.getReason(), response2)) {
					request.setAttribute("Error", "Error Occured");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Employee.jsp");
					requestDispatcher.forward(request, response);
				}
			} else if (str.contentEquals("LOP")) {

				Client client1 = ClientBuilder.newClient();
				WebTarget webTarget1 = client1
						.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/getLOP");

				Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
				String response2 = invocationBuilder1.get().readEntity(String.class);

				if (!leaveValidation.leaveValid(applyLeave.getLeaveType(), applyLeave.getStartdate(),
						applyLeave.getEnddate(), applyLeave.getApplyTo(), applyLeave.getReason(), response2)) {
					request.setAttribute("Error", "Error Occured");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Employee.jsp");
					requestDispatcher.forward(request, response);
				}

			}
		}
		// doGet(request, response);
	}

}
