package com.store.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";


    public ProductDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired
    public ProductDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Product getProductByID(int itemId){

        String sql = "SELECT * FROM products WHERE itemId = ?";
        Product product = (Product)this.jdbcTemplate.queryForObject(sql, new Object[] { itemId }, new ProductRowMapper());

        return product;
    }

    public List<Product> getProductByKeyword(String str){
        List<Product> products = new ArrayList<Product>();

        this.jdbcTemplate.query("SELECT * FROM products WHERE shortDescription LIKE '%" +str+"%'", new Object[] { },
                (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(product -> products.add(product));

        return products;
    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<Product>();

        this.jdbcTemplate.query("SELECT * FROM products", new Object[] { },
                (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(product -> products.add(product));

        return products;
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
