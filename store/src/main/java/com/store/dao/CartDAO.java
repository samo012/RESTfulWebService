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
        String sql = "SELECT carts.cartId, products.itemId, products.name, products.msrp, products.salePrice " +
                "FROM carts INNER JOIN products ON carts.cartId=products.itemId WHERE cartId = ?";
        this.jdbcTemplate.query(sql, new Object[] {cartId},
                (rs, rowNum) -> new Cart(rs.getInt("cartId"), rs.getInt("productId"), rs.getString("username"), rs.getBoolean("active"))
        ).forEach(item -> items.add(item));

        return items;
    }

    public Cart addItemtoCart(Cart cart){

        String sql = "INSERT into carts (cartId, productId, username, active) VALUES (?,?,?,?)";

        this.jdbcTemplate.update(sql, new Object[] { cart.getCartId(), cart.getProductId(), cart.getUser(), true });

        return cart;
    }


    public String removeItem(int cartId, int productId){
        this.jdbcTemplate.update("DELETE FROM carts WHERE cartId = ? AND productId=?", new Object [] {
                cartId,productId});
        String str = "Item successfully removed from cart";
        return str;
    }

    public String buyItem(int cartId){

        this.jdbcTemplate.update( "UPDATE carts SET active = ? WHERE cartId = ?",
                new Object[] {false, cartId});
        String str = "Item successfully purchased";
        return str;

    }
    public List<Cart> usersByProduct(int productId){

        String sql = "SELECT username FROM carts WHERE productId = ? AND active = ?";

        List<Cart> items = new ArrayList<Cart>();

        this.jdbcTemplate.query(sql, new Object[] {productId, false},
                (rs, rowNum) -> new Cart(rs.getInt("productId"), rs.getString("username"))
        ).forEach(item -> items.add(item));

        return items;

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