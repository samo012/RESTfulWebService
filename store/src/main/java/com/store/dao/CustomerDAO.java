package com.store.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.ArrayList;
import java.util.List;



public class CustomerDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";


    public CustomerDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired
    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Customer createCustomer(Customer customer){

        this.jdbcTemplate.update("INSERT into customers (fname, lname, username, email) values (?, ?, ?, ?)", new Object[] { 
            customer.getFname(), customer.getLname(), customer.getUsername(), customer.getEmail()});
    
        return customer;
    }

    public Customer getCustomer(String username){

        String sql = "SELECT * FROM customers WHERE username = ?";

        Customer customer = (Customer)this.jdbcTemplate.queryForObject(
                sql, new Object[] { username }, new CustomerRowMapper());

        return customer;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<Customer>();

        this.jdbcTemplate.query("SELECT * FROM customers", new Object[] { },
                (rs, rowNum) -> new Customer(rs.getString("fname"), rs.getString("lname"), rs.getString("username"), rs.getString("email"))
        ).forEach(customer -> customers.add(customer));

        return customers;
    }

    public Customer updateCustomer(Customer customer){
         this.jdbcTemplate.update( "UPDATE customers SET username = ?, email = ? WHERE fname = ? AND lname = ?",
            new Object[] {customer.getUsername(), customer.getEmail(), customer.getFname(), customer.getLname()});
        return customer;
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
