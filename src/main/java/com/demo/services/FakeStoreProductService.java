package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.dtos.FakeStoreDTO;
import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Product;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

	private RestTemplate restTemplate;
	
    public FakeStoreProductService(RestTemplate restTemplate) {
    	this.restTemplate = restTemplate;
    }
	
	@Override
	public Product getSingleProduct(Long productId)throws ProductNotFoundException {
		// TODO Auto-generated method stub
		FakeStoreDTO fakeStoredto = restTemplate.getForObject(
					"https://fakestoreapi.com/products/"+productId, FakeStoreDTO.class);
		
		 if(fakeStoredto==null) {
			 throw new ProductNotFoundException("product with id "+productId+"not found");
		 }
		return fakeStoredto.toProduct();
	}
	
	@Override
	public List<Product> getAllProducts(){
		FakeStoreDTO[] fakeStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreDTO[].class);
		List<Product> products = new ArrayList<>();
		for(FakeStoreDTO fks : fakeStoreDto) {
			products.add(fks.toProduct());
		}
		return products;
	}
	
	@Override
	public Product addProduct(String title,String description,String ImageUrl,double price,String category){
		FakeStoreDTO fakeStoredto = new FakeStoreDTO();
		fakeStoredto.setTitle(title);
		fakeStoredto.setDescription(description);
		fakeStoredto.setImage(ImageUrl);
		fakeStoredto.setPrice(price);
		fakeStoredto.setCategory(category);
		FakeStoreDTO responce = restTemplate.postForObject("https://fakestoreapi.com/products/", fakeStoredto, FakeStoreDTO.class);
		return fakeStoredto.toProduct();
	}
	
	 public Product deleteProduct(Long productId)
		        throws ProductNotFoundException {
		        ResponseEntity<FakeStoreDTO> responseEntity = restTemplate.exchange(
		                "http://fakestoreapi.com/products/" + productId,
		                HttpMethod.DELETE,
		              null,
		              FakeStoreDTO.class
		        );
//		        FakeStoreDto fakeStoreDto = restTemplate.exchange(
//		                "http://fakestoreapi.com/products/" + productId,
//		                HttpMethod.DELETE,
//		                null,
//		                FakeStoreDto.class
//		        ).getBody();

		        FakeStoreDTO fakeStoreDto = responseEntity.getBody();
		        if (fakeStoreDto == null) {
		            throw new ProductNotFoundException(
		                    "Product with id " + productId + " not found"
		                            +" try to delete a product with id less than 21");
		        }

		        return fakeStoreDto.toProduct();
		    }
	 
	    @Override
	    public Product updateProduct (Long productId,String title,String description,String imageUrl,String category,double price)throws ProductNotFoundException {
	        FakeStoreDTO requestDto = new FakeStoreDTO();
	        requestDto.setTitle(title);
	        requestDto.setDescription(description);
	        requestDto.setImage(imageUrl);
	        requestDto.setCategory(category);
	        requestDto.setPrice(price);

	        // Known Issue: HTTP PATCH is not supported by RestTemplate
	        // So below code will NOT work
	        // Will throw an exception:
	        // Invalid HTTP method: PATCH\n\tat org.springframework.web.client.
	        // create request entity to send in patch request body to fakestore
//	         HttpEntity<FakeStoreDto> requestEntity = new HttpEntity<>(requestDto);
	//
//	        ResponseEntity<FakeStoreDto> responseEntity = restTemplate.exchange(
//	                "https://fakestoreapi.com/products/" + productId,
//	                HttpMethod.PATCH,
//	                requestEntity,
//	                FakeStoreDto.class
//	        );
	//
//	        FakeStoreDto response = responseEntity.getBody();
//	        if (response == null) {
//	            throw new ProductNotFoundException(
//	                    "Product with id " + productId + " not found");
//	        }

	       // FakeStoreDTO response = requestDto;
	        requestDto.setId(productId);
	        return requestDto.toProduct();
	    }

	    @Override
	    public Product replaceProduct (
	            Long productId,
	            String title,
	            String description,
	            String imageUrl,
	            String category,
	            double price)
	            throws ProductNotFoundException {

	        FakeStoreDTO requestDto = new FakeStoreDTO();
	        requestDto.setTitle(title);
	        requestDto.setDescription(description);
	        requestDto.setImage(imageUrl);
	        requestDto.setCategory(category);
	        requestDto.setPrice(price);

	        // create request entity to send in put request body to fakestore
	        HttpEntity<FakeStoreDTO> requestEntity = new HttpEntity<>(requestDto);

	        FakeStoreDTO response = restTemplate.exchange(
	                "https://fakestoreapi.com/products/" + productId,
	                HttpMethod.PUT,
	                requestEntity,
	                FakeStoreDTO.class
	        ).getBody();

	        if (response == null) {
	            throw new ProductNotFoundException(
	                    "Product with id " + productId + " not found" );
	        }
	        return response.toProduct();
	    }
	
	

}
