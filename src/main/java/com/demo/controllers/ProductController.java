package com.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.ProductRequestDTO;
import com.demo.dtos.ProductResponceDTO;
import com.demo.services.ProductService;

@RestController
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping("/products/{id}")
	public ProductResponceDTO getProductDetails(@PathVariable("id") int producId) {
		return productService.getSingleProduct(producId);
	}
	@PostMapping("/products")
	public  ProductResponceDTO createNewProduct(@RequestBody ProductRequestDTO productRequestDto) {
		return productService.addProduct(
				productRequestDto.getTitle(),
				productRequestDto.getDescription(),
				productRequestDto.getImage(),
				productRequestDto.getPrice(),
				productRequestDto.getCategory());
	}
}
