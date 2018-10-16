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
    public String removeItem(int cartId, int productId){return cartDAO.removeItem(cartId,productId);}

    public List<Cart> listUsersByProduct(int productId) { return cartDAO.usersByProduct(productId); }
    public String buyItem(int cartId) {return cartDAO.buyItem(cartId); }






}
