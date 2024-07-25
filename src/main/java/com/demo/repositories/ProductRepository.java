package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	Product save(Product product);
	List<Product> findAll();
	Product findByIdIs(Long id);
	
}
