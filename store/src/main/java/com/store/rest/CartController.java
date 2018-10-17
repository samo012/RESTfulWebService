package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import javafx.util.Pair;
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
    public Response addItemToCart(@QueryParam("productId") int pid,
                                  @QueryParam("username") String username)
    {
        if (pid == 0 || username == null)
            return Response.status(400).entity("Bad Request").build();

        int cartActive = cartService.getUsersCart(username);

        // 1 . Check for active cart belonging to this user, if none, create a cart
        if(cartActive == -1)
        {
            cartService.createCart(new Cart(username, 1));
            // get new cart's cartid (?)
            cartActive = cartService.getUsersCart(username);
        }

        // add product id to cart contents table
        CartItems item = new CartItems(cartActive, pid);
        cartService.addItemToCart(item);
        String output = cartService.getMsg("Item successfully added");
        return Response.status(200).entity(output).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getItemsFromCartId(@QueryParam("username") String username,
                                       @QueryParam("productId") int productid)
    {
        // if no username, return users who've bought some product
        if(username == null)
        {
            //return Response.status(200).entity(cartService.listUsersByProduct(productid)).build();
        }

        int activeCart = cartService.getUsersCart(username);

        if(activeCart == -1)
        {
            return Response.status(400).entity("Bad Request").build();
        }



        List<CartItems> items = new ArrayList<>();

        items = cartService.getItems(activeCart);

        Collection<Product> products = new ArrayList<>();

        for(int i=0; i<items.size(); i++)
        {
            products.add(productService.getProductByID(items.get(i).getProductid()));
        }
          Object [] p = new Object[]{products, activeCart};
        return Response.status(200).entity(products).build();
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