package com.xoriant.ecart.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.CategoryRepo;
import com.xoriant.ecart.globalexceptionhandeler.NoSuchElementExceptionHandeler;
import com.xoriant.ecart.globalexceptionhandeler.UserInputException;
import com.xoriant.ecart.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public Category addNewCategory(Category category) {
		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0) {
			throw new UserInputException();
		}
		String methodName = "addNewCategory() ";
		log.info(methodName + " called");
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> addListOfCategory(List<Category> category) {
		for (Category cat : category) {
			if (cat.getCategoryName().isEmpty() || cat.getCategoryName().length() == 0) {
				throw new UserInputException();
			}
		}
		String methodName = "addListOfCategory() ";
		log.info(methodName + " called");
		return categoryRepo.saveAll(category);
	}

	@Override
	public Category updateCategory(Category category) {

	//	Optional<Category> existingCategory = categoryRepo.findById(category.getCategoryId());
//		if (!existingCategory.isPresent()) {
//			throw new NoSuchElementExceptionHandeler();
//		}
		
		
		Category existingCat = categoryRepo.findById(category.getCategoryId()).orElse(null);
		existingCat.setCategoryId(category.getCategoryId());
		existingCat.setCategoryName(category.getCategoryName());
		if (category.getCategoryName().isEmpty() || category.getCategoryName().length() == 0) {
			throw new UserInputException();
		}
		String methodName = "updateCategory() ";
		log.info(methodName + " called");
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> updateListOfCategory(List<Category> category) {

		List<Category> categoryLists = new ArrayList<Category>();

		for (Category existingCategory : category) {
			if (existingCategory.getCategoryName().isEmpty() || existingCategory.getCategoryName().length() == 0) {
				throw new UserInputException();
			}
		}

		for (Category newCategory : category) {
			Optional<Category> existingCatId = categoryRepo.findById(newCategory.getCategoryId());
			if (!existingCatId.isPresent()) {
				throw new NoSuchElementExceptionHandeler();
			}
			Category cat = existingCatId.get();
			cat.setCategoryName(newCategory.getCategoryName());
			categoryRepo.save(cat);
			categoryLists.add(newCategory);

		}
		String methodName = "updateListOfCategory() ";
		log.info(methodName + " called");
		return categoryLists;
	}

	@Override
	public Optional<Category> findCategoryById(int categoryId) {
		String methodName = "findCategoryById() ";
		log.info(methodName + " called");
		Optional<Category> existingCategory = categoryRepo.findById(categoryId);
		if (!existingCategory.isPresent()) {
			throw new NoSuchElementExceptionHandeler();
		}
		return existingCategory;
	}

	@Override
	public List<Category> fetchAll() {
		String methodName = "fetchAll() ";
		log.info(methodName + " called");
		return categoryRepo.findAll();
	}

	@Override
	public void deleteCategory(int categoryId) {
		Optional<Category> existingId = categoryRepo.findById(categoryId);
		if (!existingId.isPresent()) {
			throw new NoSuchElementExceptionHandeler();
		}
		String methodName = "deleteCategory() ";
		log.info(methodName + " called");

		categoryRepo.deleteById(categoryId);
	}

}
