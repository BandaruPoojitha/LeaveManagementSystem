package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import beanclasses.Department;
import beanclasses.Employee;
import validation.ValidateEmployeeDetails;

/**
 * Servlet implementation class AdminOperations
 */
public class AdminOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOperations() {
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
		// TODO Auto-generated method stub
		if (request.getParameter("add") != null) {
			Employee employee = new Employee();
			Department department = new Department();
			department.setDepartmentId(request.getParameter("departmentId"));
			ValidateEmployeeDetails validateEmployeeDetails = new ValidateEmployeeDetails();
			employee.setEmployeeName(request.getParameter("name"));
			employee.setEmployeeId(request.getParameter("EmployeeId"));
			employee.setDepartment(department);
			employee.setManagerId(request.getParameter("managerId"));
			employee.setAddress(request.getParameter("address"));
			employee.setEmail(request.getParameter("Email"));
			if (validateEmployeeDetails.emailValidation(employee.getEmail())) {
				employee.setPhonenumber(request.getParameter("phonenumber"));
				if (validateEmployeeDetails.validatephonenumber(employee.getPhonenumber())) {

					Client client = ClientBuilder.newClient(new ClientConfig().register(Employee.class));

					WebTarget webTarget = client
							.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource1/")
							.path("employees");

					Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
					Response response1 = invocationBuilder.post(Entity.entity(employee, MediaType.APPLICATION_JSON));

				}
			}
		} else if (request.getParameter("Department") != null) {

			Client client = ClientBuilder.newClient(new ClientConfig().register(Department.class));
			Department department = new Department();
			department.setDepartmentId(request.getParameter("departmentId"));
			Employee employee = new Employee();
			employee.setEmployeeId(request.getParameter("managerId"));
			department.setManagerId(employee);
			WebTarget webTarget = client
					.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource1/")
					.path("department");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response1 = invocationBuilder.post(Entity.entity(department, MediaType.APPLICATION_JSON));

		} else if (request.getParameter("EditEmployee") != null) {
		       System.out.println(request.getParameter("employeeId").isEmpty());
			if (!request.getParameter("employeeId").isEmpty()) {
				String employeeId=request.getParameter("employeeId") ;
				Employee employee=new Employee();
				employee.setEmployeeId(employeeId);
				if (!request.getParameter("address").isEmpty()) {
					
                       String newAddress=request.getParameter("address");
                       Client client = ClientBuilder.newClient();
                       WebTarget target = client.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource1/");
                       String response1 = target.path("Editaddress/"+newAddress)
                                        .request()
                                        .put(Entity.entity(employee, MediaType.APPLICATION_JSON)).toString();;
                                        //System.out.println(response1+"&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				}
				if (!request.getParameter("contact").isEmpty()) {

                    String newContact=request.getParameter("contact");
				    Client client = ClientBuilder.newClient();
                    WebTarget target = client.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource1/");
                    String response1 = target.path("EditContact/"+newContact)
                                     .request()
                                     .put(Entity.entity(employee, MediaType.APPLICATION_JSON)).toString();;
					
				}
				if (!request.getParameter("email").isEmpty()) {
					
                         String newEmail=request.getParameter("email");
                         Client client = ClientBuilder.newClient();
                         WebTarget target = client.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource1/");
                         String response1 = target.path("EditEmail/"+newEmail)
                                          .request()
                                          .put(Entity.entity(employee, MediaType.APPLICATION_JSON)).toString();;
     					
				}
			}
			else {
				System.out.println("enetr empid");
			}
		}
		//doGet(request, response);
	}

}
