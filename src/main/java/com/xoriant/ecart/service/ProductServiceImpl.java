package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.ProductRepo;
import com.xoriant.ecart.globalexceptionhandeler.NoSuchElementExceptionHandeler;
import com.xoriant.ecart.globalexceptionhandeler.UserInputException;
import com.xoriant.ecart.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;

	@Override
	public Product addNewProduct(Product product) {
		if (product.getProductName().isEmpty() || product.getProductName().length() == 0) {
			throw new UserInputException();
		}
		if (product.getDescription().isEmpty() || product.getDescription().length() == 0) {
			throw new UserInputException();
		}
		if (product.getPrice() <= 0) {
			throw new UserInputException();
		}

		return productRepo.save(product);
	}

	@Override
	public List<Product> addNewListProduct(List<Product> product) {
		for (Product newProduct : product) {
			if (newProduct.getProductName().isEmpty() || newProduct.getProductName().length() == 0) {
				throw new UserInputException();
			}
			if (newProduct.getDescription().isEmpty() || newProduct.getProductName().length() == 0) {
				throw new UserInputException();
			}
			if (newProduct.getPrice() <= 0) {
				throw new UserInputException();
			}
		}
		return productRepo.saveAll(product);
	}

	@Override
	public Product updateProduct(Product product) {

		Optional<Product> updateProduct = productRepo.findById(product.getProductId());
		if (!updateProduct.isPresent()) {
			throw new NoSuchElementExceptionHandeler();
		}

		if (product.getProductName().isEmpty() || product.getProductName().length() == 0) {
			throw new UserInputException();
		}
		if (product.getDescription().isEmpty() || product.getDescription().length() == 0) {
			throw new UserInputException();
		}
		if (product.getPrice() <= 0) {
			throw new UserInputException();
		}
		Product exisitingProduct = productRepo.findById(product.getProductId()).orElse(null);
		exisitingProduct.setProductId(product.getProductId());
		exisitingProduct.setProductName(product.getProductName());
		exisitingProduct.setPrice(product.getPrice());
		exisitingProduct.setDescription(product.getDescription());
		return productRepo.save(product);
	}

}
