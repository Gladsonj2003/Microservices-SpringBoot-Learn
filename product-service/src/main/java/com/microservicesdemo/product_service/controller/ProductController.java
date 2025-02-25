package com.microservicesdemo.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesdemo.product_service.entity.Product;
import com.microservicesdemo.product_service.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	// Create a product
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	// Get all products
	@GetMapping
	public List<Product> getallProducts() {
		return productRepository.findAll();
	}
	
	// Get product by ID
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new RuntimeException("Product not found with ID: "+ productId));
		return ResponseEntity.ok(product);
	}
}
