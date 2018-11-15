package com.store.dao;

import com.store.model.Cart;
import com.store.model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CustomerDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store_solution";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public CustomerDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Customer createCustomer(Customer customer) {
        String Query = "INSERT INTO customers(fname,lname,username,email) values (?,?,?,?)";
        this.jdbcTemplate.update(Query, new Object[]{customer.getFname(), customer.getLname(), customer.getUsername(), customer.getEmail()});
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        String updateQuery = "UPDATE customers SET fname = ?, lname = ?, email = ? WHERE username = ?";
        this.jdbcTemplate.update(updateQuery, new Object[]{customer.getFname(), customer.getLname(), customer.getEmail(), customer.getUsername()});
        return customer;
    }

    public void deleteCustomer(Customer customer) {
        String query2 = "SELECT * FROM carts WHERE username = ?";
        Cart hello = (Cart)this.jdbcTemplate.queryForObject(query2, new Object[]{customer.getUsername()}, new BeanPropertyRowMapper(Cart.class));
        int idNeeded = hello.getCartId();
        String query1 = "DELETE FROM cartproducts WHERE cartId = ?";
        this.jdbcTemplate.update(query1, new Object[]{idNeeded});
        String query = "DELETE FROM carts WHERE username = ?";
        this.jdbcTemplate.update(query, new Object[]{customer.getUsername()});
        String deleteQuery = "DELETE FROM customers WHERE username = ?";
        this.jdbcTemplate.update(deleteQuery, new Object[]{customer.getUsername()});
    }

    public Customer getCustomer(String username) {
        String query = "SELECT * FROM customers WHERE username = ?";
        Customer customer = (Customer)this.jdbcTemplate.queryForObject(query, new Object[]{username}, new BeanPropertyRowMapper(Customer.class));
        return customer;
    }

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_store_solution");
        dataSource.setUsername("springuser");
        dataSource.setPassword("ThePassword");
        return dataSource;
    }
}
