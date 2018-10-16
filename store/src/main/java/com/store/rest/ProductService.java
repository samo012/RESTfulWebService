package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;


import com.store.dao.*;
import com.store.model.*;

@Service
public class ProductService {
	
	//@Autowired
	private ProductDAO productDAO = new ProductDAO();
	
 
	public String getMsg( String msg) { 
		return "Hello : " + msg;
	}

	public Product getProductByID(int itemId) {
		return productDAO.getProductByID(itemId);
	}
	public List<Product> getProductByKeyword(String keyword) {
		return productDAO.getProductByKeyword(keyword);
	}


	public List<Product> getAllProducts() {
		List<Product> products = productDAO.getAllProducts();
		return products;
	}


}
