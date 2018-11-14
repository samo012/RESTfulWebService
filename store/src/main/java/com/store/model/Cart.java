package com.store.model;

import java.util.Collection;

public class Cart {
    private int cartId;
    private Collection<CartProduct> items;
    private String username;

    public Cart() {
    }

    public Cart(String username) {
        this.username = username;
    }

    public Cart(int cartId, String username) {
        this.cartId = cartId;
        this.username = username;
    }

    public int getCartId() {
        return this.cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Collection<CartProduct> getItems() {
        return this.items;
    }

    public void setItems(Collection<CartProduct> items) {
        this.items = items;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "Cart{cartId=" + this.cartId + ", items=" + this.items + ", username='" + this.username + '\'' + '}';
    }
}
