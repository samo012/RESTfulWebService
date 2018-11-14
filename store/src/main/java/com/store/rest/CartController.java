package com.store.rest;

import com.store.model.Order;
import java.util.Collection;
import java.util.Map;
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

@Controller
@Path("/carts")
public class CartController extends HttpServlet {
    private CartService cartService = new CartService();

    public CartController() {
    }

    @POST
    @Path("")
    public Response addToCart(@QueryParam("productId") int productId, @QueryParam("username") String username) {
        this.cartService.addToCart(username, productId);
        return Response.status(200).entity("Added to cart for: " + username).build();
    }

    @PUT
    @Path("/purchase/{params}")
    public Response purchase(@PathParam("params") int cartId) {
        this.cartService.purchase(cartId);
        return Response.status(200).entity("Cart " + cartId + " has been purchased").build();
    }

    @GET
    @Path("")
    @Produces({"application/json"})
    public Response listAndShow(@QueryParam("productId") int productId, @QueryParam("username") String username) {
        if (productId == 0) {
            Map cartProduct = this.cartService.showCartv2(username);
            return Response.status(200).entity(cartProduct).build();
        } else {
            Collection<Order> buyerList = this.cartService.listUsers(productId);
            return Response.status(200).entity(buyerList).build();
        }
    }

    @DELETE
    @Path("")
    public Response deleteItemCart(@QueryParam("cartId") int cartId, @QueryParam("productId") int productId) {
        this.cartService.deleteItemCart(cartId, productId);
        return Response.status(200).entity("Deleted product " + productId + " from cart " + cartId).build();
    }
}
