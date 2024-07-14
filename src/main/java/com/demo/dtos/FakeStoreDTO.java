package com.demo.dtos;

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
	
	public ProductResponceDTO toProductResponcedto() {
		ProductResponceDTO productResponcedto = new ProductResponceDTO();
		productResponcedto.setId(id);
		productResponcedto.setTitle(title);
		productResponcedto.setDescription(description);
		productResponcedto.setImage(image);
		productResponcedto.setPrice(price);
		productResponcedto.setCategory(category);
		return productResponcedto;
	}

}
