package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CustomerService {
	
	//@Autowired
	private CustomerDAO customerDAO = new CustomerDAO();


	public Customer getCustomer(String username) {
		return customerDAO.getCustomer(username);
	}
	public Customer createCustomer(Customer customer) {
		return customerDAO.createCustomer(customer);
	}
	public Customer updateCustomer(Customer customer) {
		return customerDAO.updateCustomer(customer);
	}
	public String deleteCustomer(String username) { return customerDAO.deleteCustomer(username); }


	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerDAO.getAllCustomers();
		return customers;
	}
	public boolean customerExists(String user) {

		try {
			customerDAO.getCustomer(user);
		}
		catch (EmptyResultDataAccessException e)
		{
			return false;
		}
		return true;
	}


}

