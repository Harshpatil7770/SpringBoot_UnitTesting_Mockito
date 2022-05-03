package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import com.xoriant.ecart.model.Category;

public interface CategoryService {

	Category addNewCategory(Category category);

	List<Category> addListOfCategory(List<Category> category);

	Category updateCategory(Category category);

	List<Category> updateListOfCategory(List<Category> category);

	 Optional<Category> findCategoryById(int categoryId);
	
	List<Category> fetchAll();
	
	void deleteCategory(int categoryId);
}
