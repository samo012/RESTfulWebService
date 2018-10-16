package com.store.dao;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.store.model.*;


public class CustomerRowMapper implements RowMapper {
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setId(rs.getInt("id"));
        customer.setFname(rs.getString("fname"));
        customer.setLname(rs.getString("lname"));
        customer.setUsername(rs.getString("username"));
        customer.setEmail(rs.getString("email"));


        return customer;
    }
}