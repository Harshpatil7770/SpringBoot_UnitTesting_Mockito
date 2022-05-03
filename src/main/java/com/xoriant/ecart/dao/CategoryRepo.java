package com.xoriant.ecart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xoriant.ecart.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	@Query(value="update categories set category_name=? where category_id=?",nativeQuery = true)
	Category findByName(String categoryName);



}
