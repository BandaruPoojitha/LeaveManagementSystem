package Assessment2.EmployeeLeaveManagementSystem;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Hibernate.AdminDAO;
import Hibernate.EmployeeDAO;
import beanclasses.Employee;

@Path("myresource2")
public class EmployeeRest {
	//get the leaveDetails
    @GET
    @Path("getLeaves")
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getLeaves() {
    	EmployeeDAO  employee=new EmployeeDAO();
    	employee.getLeaves();
    return employee.getLeaves();
    }
    //get the leaves which can be cancelled.
    @GET
    @Path("cancelLeaves")
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String>  cancelLeaves() {
    	EmployeeDAO  employee=new EmployeeDAO();
   
    return employee.leaveDetails();
    }
     //get the leaveDetails to checkStatus
    @GET
    @Path("trackLeaves")
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String>  trackLeaves() {
    	EmployeeDAO  employee=new EmployeeDAO();
   
    return employee.trackLeaveDetails();
    }
    //get the managerId of logged in Employee
	@GET
	@Path("managerId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getManagerId() {
		EmployeeDAO employee=new EmployeeDAO();
		return employee.getManagerId();
	}
	//get the Paid leaves count of logged in Employee
	@GET
	@Path("getPaid")
	@Produces(MediaType.APPLICATION_JSON)
	public int getPaid() {
		
		EmployeeDAO employee=new EmployeeDAO();
		
		return employee.getPaid();
	}
	//delete the leaves

	   @DELETE
	    @Produces(MediaType.TEXT_PLAIN)
	    @Consumes(MediaType.TEXT_PLAIN)
	    @Path("/{id}")
	    public String deleteLeave(@PathParam("id") String sno) {
		   int sno1=Integer.parseInt(sno);
		   System.out.println(sno+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$##############");
			EmployeeDAO employee = new EmployeeDAO();
			employee.cancelLeave(sno1);
			return "deleted";
	   }
	   //forwarding the leave to manager
	   @PUT
		 @Path("updatemanagerId/{managerId}/{sno}")
		 @Consumes(MediaType.APPLICATION_JSON)
		 public String editEmail(Employee employee, @PathParam("managerId") String managerId,@PathParam("sno") int sno) {
		   EmployeeDAO employeeDAO=new EmployeeDAO();
		   
		   employeeDAO.updateApplyTo(managerId, sno);
		 return "updated";
		 }
	   //updating the leaveStatus
	   @PUT
		 @Path("updatestatus/{status}/{sno}")
		 @Consumes(MediaType.APPLICATION_JSON)
		 public String updateStatus(Employee employee, @PathParam("status") String status,@PathParam("sno") int sno) {
		   EmployeeDAO employeeDAO=new EmployeeDAO();
		   
		   employeeDAO.updateStatus(status, sno);
		 return "updated";
		 }
	   
	   
//get the BalanceLeaves count
	@GET
	@Path("leaveBalance")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Integer> getBalanceLeaves() {
           EmployeeDAO employeeDAO=new EmployeeDAO();
           return employeeDAO.leaveBalance();
	}
	//get the LOP leaves count of logged in Employee
	@GET
	@Path("getLOP")
	@Produces(MediaType.APPLICATION_JSON)
	public int getLOP() {
		
		EmployeeDAO employee=new EmployeeDAO();
		
		return employee.getLOP();
	}

}
