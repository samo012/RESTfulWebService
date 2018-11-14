package com.store.model;

public class Customer {
    private String fname;
    private String lname;
    private String username;
    private String email;
    private int customerId;

    public Customer() {
    }

    public Customer(String fname, String lname, String username, String email) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public Customer(String username) {
        this.username = username;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Customer{fname='" + this.fname + '\'' + ", lname='" + this.lname + '\'' + ", username='" + this.username + '\'' + ", email='" + this.email + '\'' + ", customerId=" + this.customerId + '}';
    }
}
