package com.store.dao;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.store.model.*;


public class CartRowMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();

        cart.setCartId(rs.getInt("cartId"));
        cart.setProductId(rs.getInt("productId"));

        cart.setUser(rs.getString("username"));

        cart.setActive(rs.getBoolean("active"));



        return cart;
    }
}