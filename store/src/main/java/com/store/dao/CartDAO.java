package com.store.dao;

import com.store.model.Cart;
import com.store.model.CartProduct;
import com.store.model.Item;
import com.store.model.Order;
import com.store.model.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CartDAO {
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
    private JdbcTemplate jdbcTemplate;

    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public CartDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Cart addToCart(String username, int productId) {
        String Query = "SELECT * FROM products WHERE itemId =  ?";
        Item item = (Item)this.jdbcTemplate.queryForObject(Query, new Object[]{productId}, new BeanPropertyRowMapper(Item.class));

        Cart cart;
        String Query3;
        try {
            Query3 = "SELECT * FROM carts WHERE username = ?";
            cart = (Cart)this.jdbcTemplate.queryForObject(Query3, new Object[]{username}, new BeanPropertyRowMapper(Cart.class));
        } catch (EmptyResultDataAccessException var8) {
            cart = null;
        }

        if (null == cart) {
            Query3 = "INSERT into carts (username) value (?)";
            this.jdbcTemplate.update(Query3, new Object[]{username});
            String Query2 = "SELECT * FROM carts WHERE username = ?";
            cart = (Cart)this.jdbcTemplate.queryForObject(Query2, new Object[]{username}, new BeanPropertyRowMapper(Cart.class));
        }

        Query3 = "INSERT INTO cartproducts (productId, productName, msrp, salePrice, cartId) VALUES (?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(Query3, new Object[]{item.getItemId(), item.getName(), item.getMsrp(), item.getSalePrice(), cart.getCartId()});
        return cart;
    }

    public Collection<CartProduct> showCart(String username) {
        String Query = "SELECT * FROM carts where username = ?";
        Cart cart = (Cart)this.jdbcTemplate.queryForObject(Query, new Object[]{username}, new BeanPropertyRowMapper(Cart.class));
        Collection<CartProduct> cartProducts = new ArrayList();
        this.jdbcTemplate.query("SELECT * FROM cartproducts WHERE cartId = ? ", new Object[]{cart.getCartId()}, (rs, rowNum) -> {
            return new CartProduct(rs.getInt("productId"), rs.getString("productName"), rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getInt("cartId"));
        }).forEach((cartProduct) -> {
            cartProducts.add(cartProduct);
        });
        return cartProducts;
    }

    public Map showCartv2(String username) {
        String Query = "SELECT * FROM carts where username = ?";
        Cart cart = (Cart)this.jdbcTemplate.queryForObject(Query, new Object[]{username}, new BeanPropertyRowMapper(Cart.class));
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(cart.getCartId());
        Collection<Product> products = new ArrayList();
        this.jdbcTemplate.query("SELECT * FROM cartproducts WHERE cartId = ? ", new Object[]{cart.getCartId()}, (rs, rowNum) -> {
            return new Product(rs.getInt("productId"), rs.getString("productName"), rs.getDouble("msrp"), rs.getDouble("salePrice"));
        }).forEach((product) -> {
            products.add(product);
        });
        cartProduct.setProducts(products);
        Map<String, Object> jsonCartMap = new HashMap();
        jsonCartMap.put("cartId", cart.getCartId());
        jsonCartMap.put("items", products);
        return jsonCartMap;
    }

    public void deleteItemCart(int cartId, int productId) {
        String query = "DELETE FROM cartproducts WHERE cartId = " + cartId + " AND productId = " + productId;
        this.jdbcTemplate.update(query);
    }

    public void purchase(int cartId) {
        String Query = "SELECT * FROM carts WHERE cartId = ?";
        Cart cart = (Cart)this.jdbcTemplate.queryForObject(Query, new Object[]{cartId}, new BeanPropertyRowMapper(Cart.class));
        String user = cart.getUsername();
        String Query2 = "SELECT * FROM cartproducts WHERE cartId = ?";
        Collection<CartProduct> cartProducts = new ArrayList();
        this.jdbcTemplate.query(Query2, new Object[]{cartId}, (rs, rowNum) -> {
            return new CartProduct(rs.getInt("productId"), rs.getString("productName"), rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getInt("cartId"));
        }).forEach((cartProduct) -> {
            cartProducts.add(cartProduct);
        });
        Iterator var7 = cartProducts.iterator();

        while(var7.hasNext()) {
            CartProduct product = (CartProduct)var7.next();
            this.jdbcTemplate.update("INSERT INTO orders (productId, username) VALUES (?, ?)", new Object[]{product.getProductId(), user});
        }

        String query = "DELETE FROM cartproducts WHERE cartId = " + cartId;
        this.jdbcTemplate.update(query);
    }

    public Collection<Order> listUsers(int productId) {
        String Query = "SELECT * FROM orders WHERE productId = " + productId;
        Collection<Order> order = new ArrayList();
        this.jdbcTemplate.query(Query, new Object[0], (rs, rowNum) -> {
            return new Order(rs.getInt("productId"), rs.getString("username"));
        }).forEach((order1) -> {
            order.add(order1);
        });
        return order;
    }

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_store");
        dataSource.setUsername("springuser");
        dataSource.setPassword("ThePassword");
        return dataSource;
    }
}
