package com.xoriant.ecart.service;

import java.util.List;
import java.util.Optional;

import com.xoriant.ecart.model.Brand;

public interface BrandService {

	Brand addNewBrand(Brand brand);

	List<Brand> addNewListOfBrand(List<Brand> brands);

	Brand updateBrand(Brand brand);

	List<Brand> updateListOfBrand(List<Brand> brands);

	Optional<Brand> findById(int brandId);

	List<Brand> fetchAll();

	void deleteById(int brandId);
}
