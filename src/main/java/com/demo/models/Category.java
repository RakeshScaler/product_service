package com.demo.models;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Category extends BaseModel{
	
	private String title;
	@OneToMany(mappedBy = "category")
	@Fetch(value = FetchMode.SUBSELECT)
	List<Product> products;
	

}
