package com.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	private final ProductService productService;
	public ProductController(ProductService productService) {
		this.productService=productService;
		
	}

//	@GetMapping(value = "/get-name/{name}}")
//	public String getName(@PathVariable("name")String name) {		
//		return name;
//	}
//	@GetMapping(value = "/anjali")
//	public String getNameAj() {		
//		return "anjali";
//	}

}
