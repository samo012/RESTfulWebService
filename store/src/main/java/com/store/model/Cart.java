package com.store.model;

public class Cart {
    private int id;
    private int productId;
    private Boolean active = false;
    private String user;

    public Cart (int cartId, int productId, String username, Boolean active){
        this.id = cartId;
        this.productId = productId;
        this.user = username;
        this.active = active;
    }

    public int getCartId() {
        return id;
    }
    public void setCartId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int id) {
        this.productId = id;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(Boolean b) {
        this.active = b;
    }

}
