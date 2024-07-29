package com.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.models.Product;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.ProductRepository;
import com.demo.repositories.projections.ProductProjection;
import com.demo.repositories.projections.ProductWithIdAndTitle;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Test
	void contextLoads() {
	}
	
	/*
	 * @Test void testJPADeclaredJoin() { List<Product> products =
	 * productRepository.findAllByCategory_Title("electronics"); for(Product product
	 * : products) { System.out.println(product.getTitle());
	 * System.out.println(product.getCategory().getTitle());
	 * System.out.println(product.getPrice()); } System.out.println("============");
	 * }
	 * 
	 * @Test void testHQL() { List<Product> products =
	 * productRepository.getProductWithCategoryName("electronics"); for (Product
	 * product : products) { System.out.println(product.getTitle());
	 * System.out.println(product.getCategory().getTitle());
	 * System.out.println(product.getPrice()); }
	 * System.out.println("--------------"); }
	 * 
	 * @Test void testSpecificFields() { List<String> productTitles =
	 * productRepository.someTitleMethod("electronics"); for (String productTitle :
	 * productTitles) { System.out.println(productTitle); }
	 * System.out.println("_____________"); }
	 * 
	 * @Test void testProjections() { List<ProductWithIdAndTitle> products =
	 * productRepository.someMethod1("electronics"); for (ProductWithIdAndTitle
	 * product : products) { System.out.println(product.getTitle());
	 * System.out.println(product.getId()); } }
	 * 
	 * @Test void testProductProjection() { List<ProductProjection>
	 * productProjections = productRepository.someMethod2("new electronics");
	 * for(ProductProjection p : productProjections) {
	 * System.out.println(p.getTitle()); System.out.println(p.getId()); } }
	 */
	    @Test
	    void testNativeSql() {
	        List<Product> products = productRepository.someNativeSql(1L);
	        for (Product product : products) {
	            System.out.println(product.getTitle());
	            System.out.println(product.getCategory().getTitle());
	            System.out.println(product.getImageUrl());
	        }
	    }
	    
	    @Test
	    void testNativeProjectionSql() {
	    	ProductProjection product2 = productRepository.someNativeSql2(1L);
	        System.out.println(product2.getTitle());
	        System.out.println(product2.getId());
	    }
}
