package com.demo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.models.Category;
import com.demo.models.Product;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.ProductRepository;

import jakarta.transaction.Transactional;

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
	/*
	 * @Test void testNativeSql() { List<Product> products =
	 * productRepository.someNativeSql(1L); for (Product product : products) {
	 * System.out.println(product.getTitle());
	 * System.out.println(product.getCategory().getTitle());
	 * System.out.println(product.getImageUrl()); } }
	 * 
	 * @Test void testNativeProjectionSql() { ProductProjection product2 =
	 * productRepository.someNativeSql2(1L);
	 * System.out.println(product2.getTitle());
	 * System.out.println(product2.getId()); }
	 */
	    
	    @Test
	    //Transactional
	    void testFetchType() {
	        Optional<Category> category = categoryRepository.findById(1L);
	        if (category.isPresent()) {
	            System.out.println(category.get().getTitle());
	            List<Product> products = category.get().getProducts();
	            for (Product product : products) {
	                System.out.println(product.getTitle());
	            }
	        }
	    }
	    
	    @Test
	    @Transactional
	    void testFetchMode() {
	        List<Category> categories = categoryRepository.findByTitleEndingWith("electronics");
	        for (Category category : categories) {
	            System.out.println(category.getTitle());
	            List<Product> products = category.getProducts();
	            for (Product product : products) {
	                System.out.println(product.getTitle());
	            }
	        }
	    }

}
