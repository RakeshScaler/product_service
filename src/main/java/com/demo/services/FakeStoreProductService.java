package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.dtos.FakeStoreDTO;
import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Product;

@Service
public class FakeStoreProductService implements ProductService{

	private RestTemplate restTemplate;
	
    public FakeStoreProductService(RestTemplate restTemplate) {
    	this.restTemplate = restTemplate;
    }
	
	@Override
	public Product getSingleProduct(int productId)throws ProductNotFoundException {
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
	
	

}
