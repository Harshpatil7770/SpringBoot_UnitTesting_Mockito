package com.xoriant.ecart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import com.xoriant.ecart.dao.CategoryRepo;
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.CategoryServiceImpl;

@SpringBootTest
public class CategoryServiceImplTest {

	// we inject repo in service layer
	@Mock
	CategoryRepo categoryRepo;

	@Mock
	Logger logger;

	// we inject the mock in service impl
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;

	private static Category category;

	@BeforeAll
	public static void beforeAllMethod() {
		category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("SmartPhones");
	}

	@Test
	public void addNewCategory() {

		when(logger.isInfoEnabled()).thenReturn(false);
		when(categoryRepo.save(category)).thenReturn(category);
		assertEquals(category, categoryServiceImpl.addNewCategory(category));
	}

	@Test
	public void addListOfCategory() {
		List<Category> lists = new ArrayList<Category>();
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("SmartPhones");
		category.setCategoryId(2);
		category.setCategoryName("Laptops");
		lists.add(category);
		when(logger.isInfoEnabled()).thenReturn(false);
		when(categoryRepo.saveAll(lists)).thenReturn(lists);
		assertEquals(lists, categoryServiceImpl.addListOfCategory(lists));
	}

	@Test
	public void updateCategory() {
		Category category = new Category(101, "Laptops", null);
		Optional<Category> category1 = Optional.of(category);

		when(categoryRepo.findById(101)).thenReturn(category1);
		category.setCategoryName("SmartPhones");
		when(categoryRepo.save(category)).thenReturn(category);
		assertThat(categoryServiceImpl.updateCategory(category)).isEqualTo(category);

	}

	@Test
	public void updateListOfCategory() {
		List<Category> catList = new ArrayList<Category>();
		Category category1 = new Category(101, "SmartPhones", null);
		Category category2 = new Category(102, "Laptops", null);
		Optional<Category> cat_1 = Optional.of(category1);
		when(categoryRepo.findById(101)).thenReturn(cat_1);
		category1.setCategoryName("Mens Wear");
		Optional<Category> cat_2 = Optional.of(category2);
		when(categoryRepo.findById(102)).thenReturn(cat_2);
		category1.setCategoryName("Ladies Wear");

		catList.add(category1);
		catList.add(category2);
		assertThat(categoryServiceImpl.updateListOfCategory(catList)).isEqualTo(catList);
	}

	@Test
	public void findCategoryById() {
		Optional<Category> cat = Optional.of(category);
		when(categoryRepo.findById(cat.get().getCategoryId())).thenReturn(cat);
		assertEquals(category, categoryServiceImpl.findCategoryById(cat.get().getCategoryId()).get());
	}

	@Test
	public void fetchAll() {
		List<Category> catLists = new ArrayList<Category>();
		catLists.add(category);
		List<Brand> brandLists = new ArrayList<Brand>();
		Category category1 = new Category(101, "Mens Wear", null);
		Category category2 = new Category(102, "Ladies Wear", null);
		catLists.add(category1);
		catLists.add(category2);

		when(categoryRepo.findAll()).thenReturn(catLists);
		assertEquals(3, categoryServiceImpl.fetchAll().size());
	}

	@Test
	public void deleteCategory() {
		categoryRepo.deleteById(category.getCategoryId());
		assertThat(categoryRepo.existsById(category.getCategoryId())).isFalse();
//		int brandId = 1;
//		brandService.deleteById(brandId);
//		verify(brandRepo, times(1)).deleteById(brandId);
	}

}
