package com.store.model;

public class Product {
    private int productId;
    private String productName;
    private double msrp;
    private double salePrice;

    public Product() {
    }

    public Product(int productId, String productName, double msrp, double salePrice) {
        this.productId = productId;
        this.productName = productName;
        this.msrp = msrp;
        this.salePrice = salePrice;
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

    public String toString() {
        return "{productId=" + this.productId + ", productName='" + this.productName + '\'' + ", msrp=" + this.msrp + ", salePrice=" + this.salePrice + '}';
    }
}
