package com.demo.services;

import java.util.List;

import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Product;

public interface ProductService {
	
	public Product getSingleProduct(int productId) throws ProductNotFoundException;
	
	public List<Product> getAllProducts();
	
	public Product addProduct(
			String title,
			String description,
			String ImageUrl,
			double price,
			String category
			);
	
}
