package com.store.model;

import java.util.Collection;

public class CartProduct {
    private int productId;
    private String productName;
    private double msrp;
    private double salePrice;
    private int cartId;
    private Collection<Product> items;

    public CartProduct() {
    }

    public CartProduct(int productId, String productName, double msrp, double salePrice, int cartId) {
        this.productId = productId;
        this.productName = productName;
        this.msrp = msrp;
        this.salePrice = salePrice;
        this.cartId = cartId;
    }

    public void setProducts(Collection<Product> productCollection) {
        this.items = productCollection;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getMsrp() {
        return this.msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getCartId() {
        return this.cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String toString() {
        return "{cartId=" + this.cartId + ", items=" + this.items + '}';
    }
}
