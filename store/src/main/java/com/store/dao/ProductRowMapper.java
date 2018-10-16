package com.store.dao;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.store.model.*;

public class ProductRowMapper implements RowMapper
{
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setItemId(rs.getInt("itemId"));
        product.setName(rs.getString("name"));
        product.setMsrp(rs.getDouble("msrp"));
        product.setSalePrice(rs.getDouble("salePrice"));
        product.setUpc(rs.getInt("upc"));
        product.setShortDescription(rs.getString("shortDescription"));
        product.setBrandName(rs.getString("brandName"));
        product.setSize(rs.getString("size"));
        product.setColor(rs.getString("color"));
        product.setGender(rs.getString("gender"));

        return product;
    }

}