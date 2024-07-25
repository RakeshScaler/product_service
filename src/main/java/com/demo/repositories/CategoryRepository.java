package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
		Category save(Category category);
		Category findByTitle(String name);
}
