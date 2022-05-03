package com.xoriant.ecart.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.ecart.dao.BrandRepo;
import com.xoriant.ecart.globalexceptionhandeler.NoSuchElementExceptionHandeler;
import com.xoriant.ecart.globalexceptionhandeler.UserInputException;
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.model.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandRepo brandRepo;

	@Override
	public Brand addNewBrand(Brand brand) {
		if (brand.getBrandName().isEmpty() || brand.getBrandName().length() == 0) {
			throw new UserInputException();
		}
		String methodName = "addNewBrand() ";
		log.info(methodName + " called");
		return brandRepo.save(brand);
	}

	@Override
	public List<Brand> addNewListOfBrand(List<Brand> brands) {

		for (Brand brand : brands) {
			if (brand.getBrandName().isEmpty() || brand.getBrandName().length() == 0) {
				throw new UserInputException();
			}
		}
		String methodName = "addNewBrand() ";
		log.info(methodName + " called");
		return brandRepo.saveAll(brands);
	}

	@Override
	public Brand updateBrand(Brand brand) {
		Optional<Brand> existingbrandId = brandRepo.findById(brand.getBrandId());

		if (!existingbrandId.isPresent()) {
			throw new NoSuchElementExceptionHandeler();
		}
		Brand existingBrand = brandRepo.findById(brand.getBrandId()).orElse(null);
		existingBrand.setBrandId(brand.getBrandId());
		existingBrand.setBrandName(brand.getBrandName());
		String methodName = "updateBrand() ";
		log.info(methodName + " called");
		return brandRepo.save(brand);
	}

	@Override
	public List<Brand> updateListOfBrand(List<Brand> brands) {

		List<Brand> brandLists = new ArrayList<Brand>();

		for (Brand eachBrand : brands) {
			if (eachBrand.getBrandName().isEmpty() || eachBrand.getBrandName().length() == 0) {
				throw new UserInputException();
			}

			Optional<Brand> existingBrand = brandRepo.findById(eachBrand.getBrandId());
			if (!existingBrand.isPresent()) {
				throw new NoSuchElementExceptionHandeler();
			}

			Brand newBrand = existingBrand.get();
			// newCategory.setCategoryId(eachBrand.getBrandId());
			newBrand.setBrandName(eachBrand.getBrandName());
			brandRepo.save(newBrand);
			brandLists.add(eachBrand);
		}

		String methodName = "updateListOfBrand() ";
		log.info(methodName + " called");
		return brandLists;
	}

	@Override
	public Optional<Brand> findById(int brandId) {
		Optional<Brand> existingBrand = brandRepo.findById(brandId);
		if (!existingBrand.isPresent()) {
			throw new NoSuchElementExceptionHandeler();
		}
		String methodName = "findById() ";
		log.info(methodName + " called");

		return brandRepo.findById(brandId);
	}

	@Override
	public List<Brand> fetchAll() {
		String methodName = "fetchAll() ";
		log.info(methodName + " called");
		return brandRepo.findAll();
	}

	@Override
	public void deleteById(int brandId) {

		Optional<Brand> existingBrand = brandRepo.findById(brandId);
		if (existingBrand.isPresent()) {
			throw new NoSuchElementExceptionHandeler();
		}
		String methodName = "deleteById() ";
		log.info(methodName + " called");
		brandRepo.deleteById(brandId);

	}
}
