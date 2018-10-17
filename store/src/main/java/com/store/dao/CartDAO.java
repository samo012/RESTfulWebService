package com.store.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;



public class CartDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";


    public CartDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired
    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Cart createCart(Cart cart){

        this.jdbcTemplate.update("INSERT into carts (username, active) values (?, ?)", new Object[] {
                cart.getUsername(), cart.isActive()});

        return cart;
    }

    public void addCartItem(CartItems items){

        this.jdbcTemplate.update("INSERT into cartItems (cartid, productid) values (?, ?)", new Object[] {
                items.getCartid(), items.getProductid()});

    }


    public int getUsersCart(String username)
    {
        try {

            String sql = "SELECT * FROM carts WHERE username = ? AND active = ?";

            Cart cart = (Cart) this.jdbcTemplate.queryForObject(
                    sql, new Object[]{username, 1}, new CartRowMapper());

            return cart.getId();

        }
        catch(EmptyResultDataAccessException e)
        {
            return -1;
        }

    }

    public List<CartItems> getItems(int cartId){

        List<CartItems> output = new ArrayList<>();

        this.jdbcTemplate.query("SELECT * FROM cartItems where cartId = " + cartId , new Object[] { },
                (rs, rowNum) -> new CartItems(rs.getInt("id"), rs.getInt("cartId"), 
                        rs.getInt("productId"))).forEach(item -> output.add(item));

        return output;

    }

    public int checkout(int cartId)
    {
        this.jdbcTemplate.update( "UPDATE carts SET active = ? WHERE id = ?",
                new Object[] {0, cartId});
        return cartId;

    }

    public int removeItem(int cartid, int productid)
    {
        this.jdbcTemplate.update("DELETE FROM cartItems WHERE cartid = ? AND productid = ?", new Object [] {cartid, productid});
        return productid;
    }

    public List<String> getUsersByProductId(int pid)
    {
        String sql = "SELECT carts.username, cartItems.productid FROM cartItems LEFT JOIN carts ON cartItems.cartid=carts.id WHERE carts.active = 0 LIMIT 1";
        List<String> output = new ArrayList<>();

        this.jdbcTemplate.query(sql, new Object[] { },
                (rs, rowNum) -> rs.getString("username")).forEach(c -> output.add(c));

        return output;


    }


    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;

    }

}