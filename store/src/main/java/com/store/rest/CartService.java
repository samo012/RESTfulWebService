package com.store.rest;

import com.store.dao.CartDAO;
import com.store.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartDAO cartDAO = new CartDAO();

    public CartItems addItemToCart(CartItems item) {
        cartDAO.addCartItem(item);
        return item;
    }
    public int getUsersCart(String username){
        return cartDAO.getUsersCart(username);
    }
    public Cart  createCart(Cart cart){ return cartDAO.createCart(cart);}
    public List<CartItems> getItems(int cartId) { return cartDAO.getItems(cartId); }
    public int removeItem(int cartId, int itemId){return cartDAO.removeItem(cartId,itemId);}

    public List<String> listUsersByProduct(int itemId) { return cartDAO.getUsersByProductId(itemId); }
    public int buyItem(int cartId) {return cartDAO.checkout(cartId); }

    public String getMsg( String msg) {
        return msg;
    }


}
