package com.xoriant.ecart;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.xoriant.ecart.dao.ProductRepo;
import com.xoriant.ecart.model.Product;
import com.xoriant.ecart.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceImplTest {

	@Mock
	ProductRepo productRepo;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	private static Product product;

	@BeforeAll
	public static void beforeAll() {
		product = new Product();
		product.setProductId(101);
		product.setProductName("Oppo F1f");
		product.setPrice(15999);
		product.setDescription("selfi Expert");

	}

	@Test
	public void addNewProduct() {

		when(productRepo.save(product)).thenReturn(product);
		assertEquals(product, productServiceImpl.addNewProduct(product));
	}

	@Test
	public void addNewListProduct() {
		List<Product> productLists = new ArrayList<Product>();
		productLists.add(product);
		Product product1 = new Product(102, "Samsung Galaxy Core", 12500, "Galaxy Series");
		productLists.add(product1);
		when(productRepo.saveAll(productLists)).thenReturn(productLists);
		assertEquals(productLists, productServiceImpl.addNewListProduct(productLists));
	}

	@Test
	public void updateProduct() {
		Optional<Product> exitProduct = Optional.of(product);
		when(productRepo.findById(product.getProductId())).thenReturn(exitProduct);
		product.setProductName("Samsung Galaxy s2");
		product.setPrice(52000);
		product.setDescription("Galaxy Series");
		when(productRepo.save(product)).thenReturn(product);
		assertEquals(product, productServiceImpl.updateProduct(product));
	}
}
