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

import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.service.BrandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/brand")
@Slf4j
public class BrandController {

	@Autowired
	BrandService brandService;

	@PostMapping("save")
	Brand addNewBrand(@RequestBody Brand brand) {
		String methodName = "addNewBrand() ";
		log.info(methodName + " called");
		return brandService.addNewBrand(brand);

	}

	@PostMapping("/saveAll")
	List<Brand> addNewListOfBrand(@RequestBody List<Brand> brands) {
		String methodName = "addNewListOfBrand() ";
		log.info(methodName + " called");
		return brandService.addNewListOfBrand(brands);

	}

	@PutMapping("/update")
	Brand updateBrand(@RequestBody Brand brand) {
		String methodName = "updateBrand() ";
		log.info(methodName + " called");
		return brandService.updateBrand(brand);
	}

	@PutMapping("/updateAll")
	List<Brand> updateListOfBrand(@RequestBody List<Brand> brands) {
		String methodName = "updateListOfBrand() ";
		log.info(methodName + " called");
		return brandService.updateListOfBrand(brands);

	}

	@GetMapping("/find/{brandId}")
	Optional<Brand> findById(@PathVariable int brandId) {
		String methodName = "findById() ";
		log.info(methodName + " called");
		return brandService.findById(brandId);

	}

	@GetMapping("/findAll")
	List<Brand> fetchAll() {
		String methodName = "fetchAll() ";
		log.info(methodName + " called");
		return brandService.fetchAll();
	}

	@DeleteMapping("/delete/{brandId}")
	void deleteById(@PathVariable int brandId) {
		String methodName = "fetchAll() ";
		log.info(methodName + " called");
		brandService.deleteById(brandId);
	}
}
