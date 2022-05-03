package com.xoriant.ecart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/save")
	public Category addNewCategory(@RequestBody Category category) {
		String methodName = "addNewCategory() ";
		log.info(methodName + " called");
		return categoryService.addNewCategory(category);

	}

	@PostMapping("/saveAll")
	public List<Category> addNewListOfCategory(@RequestBody List<Category> categories) {
		String methodName = "addNewListOfCategory() ";
		log.info(methodName + " called");
		return categoryService.addListOfCategory(categories);
	}

	@PutMapping("/update")
	public Category updateCategory(@RequestBody Category category) {
		String methodName = "updateCategory() ";
		log.info(methodName + " called");
		return categoryService.updateCategory(category);
	}

	@PutMapping("/updateAll")
	public List<Category> updateListOfCategory(@RequestBody List<Category> category) {
		String methodName = "updateListOfCategory() ";
		log.info(methodName + " called");
		return categoryService.updateListOfCategory(category);
	}

	@GetMapping("/{categoryId}")
	public Optional<Category> findCategoryById(@PathVariable int categoryId) {
		String methodName = "updateListOfCategory() ";
		log.info(methodName + " called");
		return categoryService.findCategoryById(categoryId);
	}

	@GetMapping("/fetchAll")
	public List<Category> findAll() {
		String methodName = "findAll() ";
		log.info(methodName + " called");
		return categoryService.fetchAll();

	}

	@DeleteMapping("/delete/{categoryId}")
	public void deleteCategory(@PathVariable int categoryId) {
		String methodName = "deleteCategory() ";
		log.info(methodName + " called");
		categoryService.deleteCategory(categoryId);
	}
}
