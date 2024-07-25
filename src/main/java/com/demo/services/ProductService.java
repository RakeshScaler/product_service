package com.demo.services;

import java.util.List;

import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Product;

public interface ProductService {
	
	public Product getSingleProduct(Long productId) throws ProductNotFoundException;
	public Product deleteProduct(Long productId) throws ProductNotFoundException;
	public List<Product> getAllProducts();
	public Product updateProduct(Long productId,String title,String description,String imageUrl,String category,double price) throws ProductNotFoundException;
	public Product replaceProduct(Long productId,String title,String description,String imageUrl,String category,double price) throws ProductNotFoundException;
	public Product addProduct(String title,String description,String ImageUrl,double price,	String category	);
	
	
}
