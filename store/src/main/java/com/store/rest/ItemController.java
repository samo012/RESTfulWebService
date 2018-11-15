package com.store.rest;

import com.store.model.Item;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Controller
@Path("/items")
public class ItemController extends HttpServlet {
    private ItemService itemService = new ItemService();

    public ItemController() {
    }

    public void init(ServletConfig config) {
        try {
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        } catch (ServletException var3) {

        }

    }

    @GET
    @Path("")
    @Produces({"application/json"})
    public Collection<Item> getAllItems() {
        Collection<Item> items = this.itemService.getAllItems();
        return items;
    }

    @GET
    @Path("/{params}")
    @Produces({"application/json"})
    public Response getItem(@PathParam("params") int itemId) {
        Item retString = this.itemService.getItem(itemId);
        return Response.status(200).entity(retString).build();
    }

    @GET
    @Path("/search/{params}")
    @Produces({"application/json"})
    public Collection<Item> getItemByKeyword(@PathParam("params") String keyword) {
        Collection<Item> items = this.itemService.getItemByKeyword(keyword);
        return items;
    }
}
