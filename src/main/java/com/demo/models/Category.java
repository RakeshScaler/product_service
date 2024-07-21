package com.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Category extends BaseModel{
	
	private String title;
	@OneToMany(mappedBy = "category")
	List<Product> product;
	

}
