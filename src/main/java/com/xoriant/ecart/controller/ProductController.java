package com.xoriant.ecart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.ecart.model.Product;
import com.xoriant.ecart.service.ProductService;

@RestController
@RequestMapping("/api/product")
class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/save")
	public Product addNewProduct(@RequestBody Product product) {
		return productService.addNewProduct(product);
	}

	@PostMapping("/saveAll")
	public List<Product> addNewListProduct(@RequestBody List<Product> product) {
		return productService.addNewListProduct(product);
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

}
