package com.store.model;

public class Order {
    private int productId;
    private String username;

    public Order(String username) {
    }

    public Order(int productId, String username) {
        this.productId = productId;
        this.username = username;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "{productId=" + this.productId + ", username='" + this.username + '\'' + '}';
    }
}
