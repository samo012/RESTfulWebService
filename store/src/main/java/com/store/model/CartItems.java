package com.store.model;

public class CartItems {

    private int id, cartid, productid;

    public CartItems(int cartid, int productid) {
        this.cartid = cartid;
        this.productid = productid;
    }

    public CartItems(int id, int cartid, int productid) {
        this.id = id;
        this.cartid = cartid;
        this.productid = productid;
    }

    public CartItems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
}
