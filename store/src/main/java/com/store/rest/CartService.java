package com.store.rest;

import com.store.dao.CartDAO;
import com.store.dao.CustomerDAO;
import com.store.model.*;

import java.util.List;

public class CartService {
    private CartDAO cartDAO = new CartDAO();


    public Cart addItemToCart(Cart cart) {
        return cartDAO.addItemtoCart(cart);
    }
    public int getUsersCart(String username){return cartDAO.getUsersCart(username);}
    public List<Cart> showItems(int cartId) { return cartDAO.showItems(cartId); }
    public Cart removeItem(Cart cart) { return cartDAO.removeItem(cart);
    }
    public Cart listUsersByProduct(String username) { return cartDAO.deleteCustomer(username); }
    public Cart buyItem(int cartId) {return cartDAO.buyItem(cartId); }






}
