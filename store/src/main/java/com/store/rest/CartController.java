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
@Path("/carts")
public class CartController extends HttpServlet  {

    //@Autowired
    private CartService cartService = new CartService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response addItemToCart( @QueryParam("productId") int productId,
                                   @QueryParam("username") String username)
    {
        int cartId =0;
        if (cartService.getUsersCart(username) == 0)
            cartId = cartId + 1;

        if(productId == 0 || username == null)
            return Response.status(404).entity("Error").build();

        Cart cart = new Cart(cartId,productId,username,true);
        return Response.status(200).entity(cartService.addItemToCart(cart)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List <Cart> showItems(@QueryParam("username") String usr) {
        int cartId = cartService.getUsersCart(usr);
        return cartService.showItems(cartId);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response removeItem(@QueryParam("cartId") int cartId,
                               @QueryParam("productId") int productId)
    {
        return Response.status(200).entity(cartService.removeItem(cartId,productId)).build();
    }


    @PUT
    @Path("/purchase/{cartId}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response buyItem(@PathParam("cartId") int cartId)
    {
        return Response.status(200).entity(cartService.buyItem(cartId)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List <Cart> listUsersByProduct(@QueryParam("productId") int productId) {
        return cartService.listUsersByProduct(productId);
    }

}
