package com.demo.dtos;

import com.demo.models.Category;
import com.demo.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
	
	private int id;
	private String title;
	private String description;
	private double price;
	private String image;
	private String category;
	
	public Product toProduct() {
		Product product = new Product();
		product.setId(id);
		product.setTitle(title);
		product.setDescription(description);
		product.setImageUrl(image);
		product.setPrice(price);
		Category categoryObj = new Category();
		categoryObj.setTitle(category);
		product.setCategory(categoryObj);
		return product;
	}

}
