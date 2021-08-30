package com.example.onlineshopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.repo.ProductRepository;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "products")
public class ProductController {
	

	private static List<Product> product= new ArrayList<>();
	@Autowired
	private ProductRepository productRepository;
	private byte[] bytes;
	
	@GetMapping("/get")
	public List<Product> getProducts() throws IOException {
		return productRepository.findAll();
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

	@PostMapping("/add")
	public void createProduct(@RequestBody Product product) throws IOException {
		product.setPhoto(this.bytes);
	    productRepository.save(product);
	    this.bytes = null;

	}
	
	@DeleteMapping(path = { "/{id}" })
	public Product deleteProduct(@PathVariable("id") int id) throws IOException
	{
		Iterator<Product> iterator = product.iterator();
        while(iterator.hasNext()) 
        {
        	Product product =iterator.next();
        	if(product.getId()==id)
        	{
        		iterator.remove();
        		return product;
        	}
         }
        return null;
     }
	
	  
	@PutMapping("/update/{id}")
	public Product updateProduct(@RequestBody Product product, int id) throws IOException 
	{
	 return	productRepository.save(product);
	}
}