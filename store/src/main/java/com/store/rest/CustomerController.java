package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.springframework.http.MediaType;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;


import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/customers")
public class CustomerController extends HttpServlet  {

	//@Autowired
	private CustomerService customerService = new CustomerService();

	 public void init(ServletConfig config) {
		 try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		  }catch(ServletException e){
		  }
	 }


	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomer(@PathParam("username") String usr) {
		return Response.status(200).entity(customerService.getCustomer(usr)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response createCustomer(@QueryParam("fname") String fname,
							   @QueryParam("lname") String lname,
							   @QueryParam("username") String username,
							   @QueryParam("email") String email)
	{
		if(fname == null || lname == null || username == null || email == null)
			return Response.status(404).entity("Error").build();

		if(customerService.getCustomer(username) != null)
			return Response.status(409).entity("Username already exists").build();

		Customer customer = new Customer(fname,lname,username,email);
		return Response.status(200).entity(customerService.createCustomer(customer)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response updateCustomer(@QueryParam("fname") String fname,
							   @QueryParam("lname") String lname,
							   @QueryParam("username") String username,
							   @QueryParam("email") String email)
	{
		Customer customer = new Customer(fname, lname, username, email);
		return Response.status(200).entity(customerService.updateCustomer(customer)).build();
	}

	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response deleteCustomer(@PathParam("username") String usr) {
		return Response.status(200).entity(customerService.deleteCustomer(usr)).build();
	}



	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

}
