package com.store.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.ArrayList;
import java.util.List;



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

        this.jdbcTemplate.update("INSERT into carts (cartId, productId, username, active) values (?, ?, ?, ?)", new Object[] {
                cart.getCartId(), cart.getProductId(), cart.getUser(), cart.isActive()});

        return cart;
    }

    public int getUsersCart(String username){

        String sql = "SELECT cartId FROM carts WHERE username = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[] { username }, int.class);
    }

    public List<Cart> showItems(int cartId){

        List<Cart> items = new ArrayList<Cart>();

        this.jdbcTemplate.query("SELECT productId FROM carts WHERE cartId = ?", new Object[] {cartId},
                (rs, rowNum) -> new Cart(rs.getInt("cartId"), rs.getInt("productId"), rs.getString("username"), rs.getBoolean("active"))
        ).forEach(item -> items.add(item));

        return items;
    }

    public Cart addItemtoCart(Cart cart){

        String sql = "INSERT into carts (cartId, productId, username, active) VALUES (?,?,?,?)";

        this.jdbcTemplate.update(sql, new Object[] { cart.getCartId(), cart.getProductId(), cart.getUser(), true });

        return cart;
    }


    public Cart removeItem(Cart cart){
        this.jdbcTemplate.update("DELETE FROM carts WHERE cartId = ? AND productId=?", new Object [] {
                cart.getCartId(),cart.getProductId()});
        return cart;
    }

    public String deleteCustomer(String username){

        String str = "Error deleting customer";

        int rows = this.jdbcTemplate.update("DELETE FROM customers WHERE username = ?", new Object [] {username});
        if (rows > 0){
            str = "Customer Successfully Deleted";
        }
        return str;
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