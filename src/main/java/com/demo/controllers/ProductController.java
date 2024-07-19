package com.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.ErrorDTO;
import com.demo.dtos.ProductRequestDTO;
import com.demo.dtos.ProductResponceDTO;
import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Product;
import com.demo.services.ProductService;

@RestController
public class ProductController {
	private ProductService productService;
	private ModelMapper modelMapper;
	
	public ProductController(ProductService productService,ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}
	@GetMapping("/products/{id}")
	public ProductResponceDTO getProductDetails(@PathVariable("id") int productId) throws ProductNotFoundException {
		Product  product =  productService.getSingleProduct(productId);
		return convertToProductResponseDTO(product); 
	}
	
	@GetMapping("/products")
	public List<ProductResponceDTO> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		List<ProductResponceDTO>productResponceDtos = new ArrayList<>();
		for(Product product : products) {
			productResponceDtos.add(convertToProductResponseDTO(product));
		}
		return productResponceDtos;
	}
	@PostMapping("/products")
	public  ProductResponceDTO createNewProduct(@RequestBody ProductRequestDTO productRequestDto) {
		Product product =  productService.addProduct(
				productRequestDto.getTitle(),
				productRequestDto.getDescription(),
				productRequestDto.getImage(),
				productRequestDto.getPrice(),
				productRequestDto.getCategory());
		return convertToProductResponseDTO(product); 
	}
	
	private ProductResponceDTO convertToProductResponseDTO(Product product) {
		String categoryTitle = product.getCategory().getTitle();
		ProductResponceDTO productResponceDto = modelMapper.map(product, ProductResponceDTO.class);
		productResponceDto.setCategory(categoryTitle);
		return productResponceDto;
	}
	
	/*
	 * @ExceptionHandler(ProductNotFoundException.class) public
	 * ResponseEntity<ErrorDTO>
	 * handleProductNotFoundException(ProductNotFoundException
	 * productNotFoundException) { ErrorDTO errorDto = new ErrorDTO();
	 * errorDto.setMessage(productNotFoundException.getMessage()); return new
	 * ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
	 * 
	 * 
	 * }
	 */
	
}
