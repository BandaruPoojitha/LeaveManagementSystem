package Assessment2.EmployeeLeaveManagementSystem;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Hibernate.AdminDAO;
import Hibernate.LoginValidation;
import beanclasses.Department;
import beanclasses.Employee;

@Path("myresource1")

public class AdminRest {
	@Context
	HttpServletResponse res;
	@Context
	HttpServletRequest req;

	// Add new Employee.
	@POST
	@Path("employees")
	public String addEmployee(Employee e) {
		AdminDAO admin = new AdminDAO();
		admin.addEmployee(e);
		return "added successfully";
	}
	//Edit Employee Address
	@Path("Editaddress/{address}")
	 @PUT
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String editAddress(Employee employee, @PathParam("address") String address) {
		 AdminDAO admin=new AdminDAO();
		
		 admin.updateAddress(employee.getEmployeeId(),address);
	 return "updated";
	 }
	//EDit employee contact number
	 @PUT
	 @Path("EditContact/{contact}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String editContact(Employee employee, @PathParam("contact") String contact) {
		 AdminDAO admin=new AdminDAO();
		
		 admin.updateContact(employee.getEmployeeId(),contact);
	 return "updated";
	 }
	 
		//EDit employee Email number
	 @PUT
	 @Path("EditEmail/{email}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String editEmail(Employee employee, @PathParam("email") String email) {
		 AdminDAO admin=new AdminDAO();
		
		 admin.updateEmail(employee.getEmployeeId(),email);
	 return "updated";
	 }
//Add new Department
	@POST
	@Path("department")
	public String addDepartment(Department e) {
		AdminDAO admin = new AdminDAO();
		admin.addDepartment(e);
		return "added successfully";
	}
//get the Department details
	@GET
	@Path("getDepartment")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getDepartment() {
		System.out.println("rest1");
		AdminDAO admin = new AdminDAO();

		ArrayList<String> al = admin.getDepartmentDetails();

		return al;

	}
}
