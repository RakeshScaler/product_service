package com.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dtos.ProductRequestDTO;
import com.demo.dtos.ProductResponceDTO;
import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Product;
import com.demo.services.ProductService;

@RestController
public class ProductController {
	private ProductService productService;
	private ModelMapper modelMapper;
	
	public ProductController(@Qualifier("SelfProductService")ProductService productService,ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}
	@GetMapping("/products/{id}")
	public ProductResponceDTO getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
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
	public  ResponseEntity<ProductResponceDTO> createNewProduct(@RequestBody ProductRequestDTO productRequestDto) {
		Product product =  productService.addProduct(
				productRequestDto.getTitle(),
				productRequestDto.getDescription(),
				productRequestDto.getImageUrl(),
				productRequestDto.getPrice(),
				productRequestDto.getCategory());
		ProductResponceDTO productResponseDto = convertToProductResponseDTO(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
		
	}
	
	 @DeleteMapping("/products/{id}")
	    public ResponseEntity<ProductResponceDTO> deleteProduct(@PathVariable("id") Long productId)
	    throws ProductNotFoundException {
	        Product product = productService.deleteProduct(productId);
	        ProductResponceDTO productResponseDto = convertToProductResponseDTO(product);
	        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
	    }
	 
	 @PatchMapping("/products/{id}")
	    public ResponseEntity<ProductResponceDTO> updateProduct(@PathVariable("id") Long productId,
	                                                            @RequestBody ProductResponceDTO productRequestDto)
	    throws ProductNotFoundException {
	        Product product = productService.updateProduct(productId,
	                productRequestDto.getTitle(),
	                productRequestDto.getDescription(),
	                productRequestDto.getImageUrl(),
	                productRequestDto.getCategory(),
	                productRequestDto.getPrice() );
	        ProductResponceDTO productResponseDto = convertToProductResponseDTO(product);
	        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
	    }

	    @PutMapping("/product/{id}")
	    public ResponseEntity<ProductResponceDTO> replaceProduct(@PathVariable("id") Long productId,
	                                                            @RequestBody ProductResponceDTO productRequestDto)
	            throws ProductNotFoundException {
	        Product product = productService.replaceProduct(productId,
	                productRequestDto.getTitle(),
	                productRequestDto.getDescription(),
	                productRequestDto.getImageUrl(),
	                productRequestDto.getCategory(),
	                productRequestDto.getPrice() );
	        ProductResponceDTO productResponseDto = convertToProductResponseDTO(product);
	        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
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
