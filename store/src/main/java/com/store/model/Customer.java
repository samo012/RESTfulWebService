package com.store.model;

public class Customer {

	private int id;
    private String fname;
 	private String lname;
 	private String username;
 	private String email;


    public Customer(String fname, String lname, String username, String email) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public Customer(int id, String fname, String lname, String username, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public Customer(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
      public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
      public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}