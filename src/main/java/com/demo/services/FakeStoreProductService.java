package com.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.dtos.FakeStoreDTO;
import com.demo.dtos.ProductResponceDTO;

@Service
public class FakeStoreProductService implements ProductService{

	private RestTemplate restTemplate;
	
    public FakeStoreProductService(RestTemplate restTemplate) {
    	this.restTemplate = restTemplate;
    }
	
	@Override
	public ProductResponceDTO getSingleProduct(int productId) {
		// TODO Auto-generated method stub
		FakeStoreDTO fakeStoredto = restTemplate.getForObject(
					"https://fakestoreapi.com/products/"+productId, FakeStoreDTO.class);
		return fakeStoredto.toProductResponcedto();
	}
	
	@Override
	public ProductResponceDTO addProduct(String title,String description,String ImageUrl,double price,String category){
		FakeStoreDTO fakeStoredto = new FakeStoreDTO();
		fakeStoredto.setTitle(title);
		fakeStoredto.setDescription(description);
		fakeStoredto.setImage(ImageUrl);
		fakeStoredto.setPrice(price);
		fakeStoredto.setCategory(category);
		FakeStoreDTO responce = restTemplate.postForObject("https://fakestoreapi.com/products/", fakeStoredto, FakeStoreDTO.class);
		return fakeStoredto.toProductResponcedto();
	}

	

}
