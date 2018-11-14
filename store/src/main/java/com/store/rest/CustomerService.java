package com.store.rest;

import com.store.dao.CustomerDAO;
import com.store.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	private CustomerDAO customerDAO = new CustomerDAO();

	public CustomerService() {
	}

	public void createCustomer(String fname, String lname, String username, String email) {
		Customer customer = new Customer(fname, lname, username, email);
		this.customerDAO.createCustomer(customer);
	}

	public Customer getCustomer(String username) {
		Customer retString = this.customerDAO.getCustomer(username);
		return retString;
	}

	public void updateCustomer(String fname, String lname, String username, String email) {
		Customer customer = new Customer(fname, lname, username, email);
		this.customerDAO.updateCustomer(customer);
	}

	public void deleteCustomer(String username) {
		Customer customer = new Customer(username);
		this.customerDAO.deleteCustomer(customer);
	}
}
