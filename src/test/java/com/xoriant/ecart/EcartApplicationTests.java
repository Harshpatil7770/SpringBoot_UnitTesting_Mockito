package com.xoriant.ecart;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.model.Category;

class EcartApplicationTests {

	@BeforeAll
	public static void beforeAllMethod() {
		System.out.println("Before All means execute only once before all tests");
	}

	@Test
	public void addNewCategory() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("SmartPhones");

		List<Brand> list = new ArrayList<Brand>();
		Brand brand = new Brand();
		brand.setBrandId(1);
		brand.setBrandName("I phone 12");
		brand.setBrandId(2);
		brand.setBrandName("I phone XR");
		list.add(brand);
		category.setBrandList(list);

	}
}
