package com.example.onlineshopping.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshopping.model.Product;



public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}