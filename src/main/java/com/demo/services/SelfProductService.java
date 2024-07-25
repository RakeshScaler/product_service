package com.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.exceptions.ProductNotFoundException;
import com.demo.models.Category;
import com.demo.models.Product;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.ProductRepository;

@Service("SelfProductService")
public class SelfProductService implements ProductService {
	
	  private final CategoryRepository categoryRepository;
	  private final ProductRepository productRepository;
	  
	  public SelfProductService(ProductRepository productRepository,
              CategoryRepository categoryRepository) {
		  			this.productRepository = productRepository;
		  			this.categoryRepository = categoryRepository;
	  }
	@Override
	public Product getSingleProduct(Long productId) throws ProductNotFoundException {
		Product  product  = productRepository.findByIdIs(productId);
		if(product == null) {
			throw new ProductNotFoundException("Product with id = "+productId+" not Found");
		}
		return product;
	}

	@Override
	public Product deleteProduct(Long productId) throws ProductNotFoundException {
		Product  product  = productRepository.findByIdIs(productId);
		if(product == null) {
			throw new ProductNotFoundException("Product with id = "+productId+" not Found");
		}
		productRepository.deleteById(productId);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	 @Override
	    public Product updateProduct(Long productId, String title, String description,
	                                 String imageUrl, String category, double price)
	            throws ProductNotFoundException {
	        Product productInDb = productRepository.findByIdIs(productId);
	        if (productInDb == null) {
	            throw new ProductNotFoundException(
	                    "Product with id " + productId + " not found"
	            );
	        }

	        if (title != null) {
	            productInDb.setTitle(title);
	        }
	        if (description != null) {
	            productInDb.setDescription(description);
	        }
	        if (imageUrl != null) {
	            productInDb.setImageUrl(imageUrl);
	        }

	        if (price != 0) {
	            productInDb.setPrice(price);
	        }
	        if (category != null) {
	            Category categoryfromDb = categoryRepository.findByTitle(category);
	            if (categoryfromDb == null) {
	                Category newCategory = new Category();
	                newCategory.setTitle(category);
	                categoryfromDb = newCategory;
	            }
	            productInDb.setCategory(categoryfromDb);
	        }
	        return productRepository.save(productInDb);
	    }


	@Override
	public Product replaceProduct(Long productId, String title, String description, String imageUrl, String category,
			double price) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProduct(String title, String description, String ImageUrl, double price, String category) {
		Product newProduct = new Product();
		newProduct.setTitle(title);
		newProduct.setDescription(description);
		newProduct.setImageUrl(ImageUrl);
		newProduct.setPrice(price);
		Category categoryFromDb = categoryRepository.findByTitle(category);
		if(categoryFromDb == null) {
			Category newCategory = new Category();
			newCategory.setTitle(category);
			//categoryRepository.save(newCategory);
			categoryFromDb = newCategory;
		}
		newProduct.setCategory(categoryFromDb);
		Product savedProduct = productRepository.save(newProduct);
		return savedProduct;
	}

}
