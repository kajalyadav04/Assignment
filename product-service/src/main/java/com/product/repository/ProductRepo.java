package com.product.repository;

import com.product.model.Product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepo {

	private final RestTemplate restTemplate;

	// Constructor Injection for RestTemplate(dependency Injection)
	public ProductRepo(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// Method to fetch products by category
	public List<Map<String, Object>> getProductByCategory(String category) {
		String url = "https://fakestoreapi.com/products/category/" + category;
		return restTemplate.getForObject(url, List.class);

	}

   // Method to add a new product
	public Product addProduct(Product product) {
		String url = "https://fakestoreapi.com/products";
		return restTemplate.postForObject(url, product, Product.class);
	}
}
