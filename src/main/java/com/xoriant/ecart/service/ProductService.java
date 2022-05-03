package com.xoriant.ecart.service;

import com.xoriant.ecart.model.Product;
import java.util.*;

public interface ProductService {

	Product addNewProduct(Product product);

	List<Product> addNewListProduct(List<Product> product);

	Product updateProduct(Product product);
}
