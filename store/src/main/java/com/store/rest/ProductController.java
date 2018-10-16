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
import java.util.ArrayList;
import java.util.List;


import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/items")
public class ProductController extends HttpServlet  {

	//@Autowired
	private ProductService productService = new ProductService();

	 public void init(ServletConfig config) {
		 try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		  }catch(ServletException e){
		  }
	 }
		  
	
	@GET
	@Path("/{itemId}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getMsg(@PathParam("itemId") int itemId) {
		return Response.status(200).entity(productService.getProductByID(itemId)).build();
	}

	@GET
	@Path("/search/{keyword}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getProductByID(@PathParam("keyword") String keyword){
		return Response.status(200).entity(productService.getProductByKeyword(keyword)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
	 	return productService.getAllProducts();
	}

}
