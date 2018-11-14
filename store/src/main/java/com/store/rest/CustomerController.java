package com.store.rest;

import com.store.model.Customer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Controller
@Path("/customers")
public class CustomerController extends HttpServlet {
	private CustomerService customerService = new CustomerService();

	public CustomerController() {
	}

	public void init(ServletConfig config) {
		try {
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		} catch (ServletException var3) {

		}

	}

	@POST
	@Path("")
	public Response createCustomer(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("username") String username, @QueryParam("email") String email) {
		this.customerService.createCustomer(fname, lname, username, email);
		return Response.status(200).entity("Created customer:  " + fname + " " + lname + " " + username + " " + email).build();
	}

	@PUT
	@Path("")
	public Response updateCustomer(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("username") String username, @QueryParam("email") String email) {
		this.customerService.updateCustomer(fname, lname, username, email);
		return Response.status(200).entity("Updated customer:  " + fname + " " + lname + " " + username + " " + email).build();
	}

	@GET
	@Path("/{params}")
	@Produces({"application/json"})
	public Response getCustomer(@PathParam("params") String username) {
		Customer retString = this.customerService.getCustomer(username);
		return Response.status(200).entity(retString).build();
	}

	@DELETE
	@Path("/{params}")
	public Response deleteCustomer(@PathParam("params") String username) {
		String retString = "Deleted Customer " + username;
		this.customerService.deleteCustomer(username);
		return Response.status(200).entity(retString).build();
	}
}
