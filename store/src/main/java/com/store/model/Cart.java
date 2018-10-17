package com.store.model;

import java.util.ArrayList;


public class Cart {

    private int id;
    private String username;
    private int active;

    public Cart(String username, int active) {
        this.username = username;
        this.active = active;
    }

    public Cart(int id, String username, int active) {
        this.id = id;
        this.username = username;
        this.active = active;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}