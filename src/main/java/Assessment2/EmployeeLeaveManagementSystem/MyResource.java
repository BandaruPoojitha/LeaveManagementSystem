package Assessment2.EmployeeLeaveManagementSystem;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Hibernate.LoginValidation;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	@Context
	HttpServletResponse res;
	@Context
	HttpServletRequest req;

//get the role of user
	@GET
	@Path("{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getPost(@PathParam("username") String username, @PathParam("password") String password)
			throws IOException, ServletException {
		LoginValidation loginvalidation = new LoginValidation();
		
		return loginvalidation.getType(username, password);

	}
}
