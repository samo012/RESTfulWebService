package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import org.springframework.http.MediaType;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;


import com.store.dao.*;
import com.store.model.*;

@Controller
@Path("/carts")
public class CartController extends HttpServlet  {

    private CartService cartService = new CartService();
    private ProductService productService = new ProductService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }


    @POST
    public Response addItemToCart(@QueryParam("productId") int productId,
                                  @QueryParam("username") String username)
    {
        if (username == null || productId ==0)
            return Response.status(400).entity("Bad Request").build();

        int cartActive = cartService.getUsersCart(username); //get users cart id

        if(cartActive == -1)  //Check for active cart
        {
            cartService.createCart(new Cart(username, 1)); //create one if it doesn't exist
            cartActive = cartService.getUsersCart(username); //set new cart id
        }

        CartItems item = new CartItems(cartActive, productId); //add item to cart items
        cartService.addItemToCart(item);
        String output = cartService.getMsg("Item successfully added");
        return Response.status(200).entity(output).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getItemsFromCartId(@QueryParam("username") String username,
                                       @QueryParam("productId") int productid)
    {

        if(username == null) //if no username specified, show users who bough a product
        {
            return Response.status(200).entity(cartService.listUsersByProduct(productid)).build();
        }

        int activeCart = cartService.getUsersCart(username);

        if(activeCart == -1) //Check for active cart
        {
            return Response.status(400).entity("Bad Request").build();
        }



        List<CartItems> items = cartService.getItems(activeCart);

        Collection<Product> products = new ArrayList<>();

        for(int i=0; i<items.size(); i++)
        {
            products.add(productService.getProductByID(items.get(i).getProductid()));
        }

        Object [] p = new Object[]{"CartId: "+ activeCart, products};
        return Response.status(200).entity(p).build();
    }

    @DELETE
    public Response removeItemFromCart(@QueryParam("productId") int pid,
                                       @QueryParam("cartId") int cart)
    {   String output = cartService.getMsg("Item successfully removed from cart");
        cartService.removeItem(cart,pid);
        return Response.status(200).entity(output).build();
    }



    @PUT
    @Path("/purchase/{cartId}")
    public Response purchaseCart(@PathParam("cartId") int id) {
        cartService.buyItem(id);
        String output = cartService.getMsg("User successfully purchased items");
        return Response.status(200).entity(output).build();
    }

}