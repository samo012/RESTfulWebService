package com.store.model;

public class Item {
    private int itemId;
    private String name;
    private double msrp;
    private double salePrice;
    private String upc;
    private String shortDescription;
    private String brandName;
    private String size;
    private String color;
    private String gender;

    public Item() {
    }

    public Item(int itemId, String name, double msrp, double salePrice, String upc, String shortDescription, String brandName, String size, String color, String gender) {
        this.itemId = itemId;
        this.name = name;
        this.msrp = msrp;
        this.salePrice = salePrice;
        this.upc = upc;
        this.shortDescription = shortDescription;
        this.brandName = brandName;
        this.size = size;
        this.color = color;
        this.gender = gender;
    }

    public Item(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMsrp() {
        return this.msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public String getUpc() {
        return this.upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String toString() {
        return "Item{itemId=" + this.itemId + ", name='" + this.name + '\'' + ", msrp=" + this.msrp + ", salePrice=" + this.salePrice + ", upc=" + this.upc + ", shortDescription='" + this.shortDescription + '\'' + ", brandName='" + this.brandName + '\'' + ", size=" + this.size + ", color='" + this.color + '\'' + ", gender='" + this.gender + '\'' + '}';
    }
}
