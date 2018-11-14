package com.store.rest;

import com.store.dao.CartDAO;
import com.store.model.CartProduct;
import com.store.model.Order;
import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartDAO cartDAO = new CartDAO();

    public CartService() {
    }

    public void addToCart(String username, int productId) {
        this.cartDAO.addToCart(username, productId);
    }

    public Collection<CartProduct> showCart(String username) {
        Collection<CartProduct> cartProduct = this.cartDAO.showCart(username);
        return cartProduct;
    }

    public Map showCartv2(String username) {
        Map cartProduct = this.cartDAO.showCartv2(username);
        return cartProduct;
    }

    public void deleteItemCart(int cartId, int productId) {
        this.cartDAO.deleteItemCart(cartId, productId);
    }

    public void purchase(int cartId) {
        this.cartDAO.purchase(cartId);
    }

    public Collection<Order> listUsers(int productId) {
        Collection<Order> order = this.cartDAO.listUsers(productId);
        return order;
    }
}
