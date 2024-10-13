package com.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.product.model.Product;
import com.product.repository.ProductRepo;

@Service
public class ProductService {

	private final ProductRepo productRepo;

	public ProductService(ProductRepo productRepo) {
		// TODO Auto-generated constructor stub
		this.productRepo = productRepo;
	}
	// Method to fetch products by category
	public List<Map<String, Object>> getProductsByCategoryList(String category) {
		return productRepo.getProductByCategory(category);
	}
//	 Method to add a new product
	public Product addProducts(Product product) {
		return productRepo.addProduct(product);
	}

}
