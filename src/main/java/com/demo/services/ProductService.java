package com.demo.services;

import com.demo.dtos.ProductResponceDTO;

public interface ProductService {
	
	public ProductResponceDTO getSingleProduct(int productId);
	
	public ProductResponceDTO addProduct(
			String title,
			String description,
			String ImageUrl,
			double price,
			String category
			);
}
