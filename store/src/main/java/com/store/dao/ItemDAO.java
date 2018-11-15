package com.store.dao;

import com.store.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ItemDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public ItemDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public ItemDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Collection<Item> getAllItems() {
        Collection<Item> items = new ArrayList();
        this.jdbcTemplate.query("SELECT * FROM products", new Object[0], (rs, rowNum) -> {
            return new Item(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getString("upc"), rs.getString("shortDescription"), rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"));
        }).forEach((item) -> {
            items.add(item);
        });
        return items;
    }

    public Item getItem(int itemId) {
        String query = "SELECT * FROM products WHERE itemId = ?";
        Item item = (Item)this.jdbcTemplate.queryForObject(query, new Object[]{itemId}, new BeanPropertyRowMapper(Item.class));
        return item;
    }

    public Collection<Item> getItemByKeyword(String keyword) {
        Collection<Item> items = new ArrayList();
        String toGet = "SELECT * FROM products WHERE name LIKE '%" + keyword + "%' OR upc LIKE '%" + keyword + "%' OR shortDescription LIKE '%" + keyword + "%' OR brandName LIKE '%" + keyword + "%' OR size LIKE '%" + keyword + "%' OR color LIKE '%" + keyword + "%' OR gender LIKE '%" + keyword + "%'";
        this.jdbcTemplate.query(toGet, new Object[0], (rs, rowNum) -> {
            return new Item(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getString("upc"), rs.getString("shortDescription"), rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"));
        }).forEach((item) -> {
            items.add(item);
        });
        return items;
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
