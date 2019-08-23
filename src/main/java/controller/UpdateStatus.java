package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import Hibernate.EmployeeDAO;
import Hibernate.LoginValidation;
import beanclasses.ApplyLeave;
import beanclasses.Employee;

/**
 * Servlet implementation class UpdateStatus
 */
public class UpdateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStatus() {
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

		int j = 7;
		ArrayList<String> al1 = (ArrayList<String>) session.getAttribute("cancel");

		for (int i = 0; i < al1.size(); i++) {
			String i1 = new Integer(i).toString();
			String value = request.getParameter("jk" + i1);
		
			if (value.equals("cancel")) {
				
				 Client client = ClientBuilder.newClient();
				    
				    String callResult = 
				      client.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/") .path("/{id}")
				        .resolveTemplate("id", al1.get(j))
				    	        
				    				        .request(MediaType.TEXT_PLAIN)
				        .delete(String.class);
				 
				j = j + 8;
			}
			else
			{
				j=j+8;
			}
			i = i + 7;
             
		}
		session.removeAttribute("cancel");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(true);
		
		ArrayList<String> al1 = (ArrayList<String>) session.getAttribute("ids");
		int j = 7;
		
		for (int i = 0; i < al1.size(); i++) {

			String i1 = new Integer(i).toString();
			String value = request.getParameter("JK" + i1);
			i = i + 7;
		
			int sno = Integer.parseInt(al1.get(j));
			if (value.equals("forward")) {
				EmployeeDAO employeedao = new EmployeeDAO();
				Employee employee=new Employee();
				String managerId = employeedao.getManagerId();
		        Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/");
                String response1 = target.path("updatemanagerId/"+managerId+"/"+sno)
                                 .request()
                                 .put(Entity.entity(employee, MediaType.APPLICATION_JSON)).toString();;

				

				j = j + 8;

				employeedao.closeEm();
			} else {
				Employee employee=new Employee();
				EmployeeDAO employeedao=new EmployeeDAO();
				  Client client = ClientBuilder.newClient();
	                WebTarget target = client.target("http://localhost:8080/EmployeeLeaveManagementSystem/webapi/myresource2/");
	                String response1 = target.path("updatestatus/"+value+"/"+sno)
	                                 .request()
	                                 .put(Entity.entity(employee, MediaType.APPLICATION_JSON)).toString();;;
				
				
				j = j + 8;

				employeedao.closeEm();
			}
			session.removeAttribute("ids");
		}
		// sdoGet(request, response);
	}

}
