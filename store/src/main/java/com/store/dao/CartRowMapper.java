package com.store.dao;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.store.model.*;


public class CartRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();

        cart.setId(rs.getInt("id"));

        cart.setUsername(rs.getString("username"));

        cart.setActive(rs.getInt("active"));

        return cart;
    }
}