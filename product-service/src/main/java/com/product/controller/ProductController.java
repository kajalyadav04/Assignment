package com.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
public class ProductController {

	private final ProductService productService;

    // Constructor Injection for ProductService
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
    //Get Endpoint to get products by category /product/category/{category}
	@GetMapping(value="/product/category/{category}")
	public List<Map<String, Object>> getProductByCategoryList(@PathVariable("category") String category){
		return productService.getProductsByCategoryList(category);
	}

    //Post Endpoint to add a new product
	@PostMapping(value = "/product")
	public Product addProduct(@RequestBody Product product) {
		System.out.println("saviing product: "+product);
		Product savedProduct = productService.addProducts(product);
		System.out.println("product after sav: "+ savedProduct);
		return savedProduct;
	}
}
