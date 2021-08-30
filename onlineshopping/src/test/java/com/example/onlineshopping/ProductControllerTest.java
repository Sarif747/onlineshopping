package com.example.onlineshopping;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.repo.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.jupiter.api.Assertions.assertNotEquals; 
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProductControllerTest {

	@Autowired
	ProductRepository productRepository;
	
	private byte[] bytes;
	
	
	@Test
	@Order(1)
	public void testCreateProduct() {
		Product product = new Product();
		product.setId(1);
		product.setName("Samsung M21");
		product.setPrice("15499");
		product.setPhoto(this.bytes);
		productRepository.save(product);
		assertNotNull(productRepository.findById(1).get());
		
	}
	

	@Test
	@Order(2)
	public void testGetAllProduct() {
		List<?> productList = productRepository.findAll();
		assertThat(productList).size().isGreaterThan(0);
		
	}
	
	@Test
	@Order(3)
	public void testUpdateProduct () {
		Product product = productRepository.findById(1).get();
		product.setPrice("15499");
		productRepository.save(product);
		assertNotEquals(15499.00, productRepository.findById(1).get().getPrice());
	}
		
	@Test
	@Order(4)
	public void testDeleteProduct() {
		productRepository.deleteById(1);
		assertThat(productRepository.existsById(1)).isFalse();
	}
    

	
}
