package com.demo.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
	
	private int id;
	private String title;
	private String description;
	private double price;
	private String imageUrl;
	Category category;
	

}
