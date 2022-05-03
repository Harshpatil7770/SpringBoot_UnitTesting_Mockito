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
import org.springframework.boot.test.context.SpringBootTest;

import com.xoriant.ecart.dao.BrandRepo;
import com.xoriant.ecart.model.Brand;
import com.xoriant.ecart.model.Category;
import com.xoriant.ecart.service.BrandServiceImpl;

@SpringBootTest
public class BrandServiceImplTest {

	@Mock
	BrandRepo brandRepo;

	@InjectMocks
	BrandServiceImpl brandServiceImpl;

	private static Brand brand;

	@BeforeAll
	public static void beforeAll() {
		brand = new Brand();
		brand.setBrandId(1);
		brand.setBrandName("Samsung");
	}

	@Test
	public void addNewBrand() {
		when(brandRepo.save(brand)).thenReturn(brand);
		assertEquals(brand, brandServiceImpl.addNewBrand(brand));
	}

	@Test
	public void addNewListOfBrand() {
		List<Brand> brandLists = new ArrayList<Brand>();
		brandLists.add(brand);
		Brand brand1 = new Brand();
		brand1.setBrandId(102);
		brand1.setBrandName("Vivo");
		brandLists.add(brand1);
		when(brandRepo.saveAll(brandLists)).thenReturn(brandLists);
		assertEquals(brandLists, brandServiceImpl.addNewListOfBrand(brandLists));

	}

	@Test
	public void updateBrand() {

		Optional<Brand> existBrand = Optional.of(brand);
		when(brandRepo.findById(brand.getBrandId())).thenReturn(existBrand);
		brand.setBrandName("Oppo");
		when(brandRepo.save(brand)).thenReturn(brand);
		assertThat(brandServiceImpl.updateBrand(brand)).isEqualTo(brand);
	}

	@Test
	public void updateListOfBrand() {
		List<Brand> listsOfBrand = new ArrayList<Brand>();

		Optional<Brand> existBrand1 = Optional.of(brand);
		when(brandRepo.findById(brand.getBrandId())).thenReturn(existBrand1);
		brand.setBrandName("Oppo");

		Brand brand1 = new Brand(101, "Vivo", null);
		Optional<Brand> existBrand2 = Optional.of(brand1);
		when(brandRepo.findById(brand1.getBrandId())).thenReturn(existBrand2);
		brand1.setBrandName("Karbon");

		listsOfBrand.add(brand);
		listsOfBrand.add(brand1);

		when(brandRepo.saveAll(listsOfBrand)).thenReturn(listsOfBrand);
		assertThat(brandServiceImpl.updateListOfBrand(listsOfBrand)).isEqualTo(listsOfBrand);

	}

	@Test
	public void findById() {
		Optional<Brand> existingBrand = Optional.of(brand);

		when(brandRepo.findById(brand.getBrandId())).thenReturn(existingBrand);
		assertEquals(existingBrand, brandServiceImpl.findById(brand.getBrandId()));
	}

	@Test
	public void fetchAll() {
		List<Brand> ListsOfBrand = new ArrayList<Brand>();

		ListsOfBrand.add(brand);
		Brand brand1 = new Brand(101, "Oppo", null);
		ListsOfBrand.add(brand1);
		when(brandRepo.findAll()).thenReturn(ListsOfBrand);
		assertEquals(2, brandServiceImpl.fetchAll().size());
	}

	@Test
	public void deleteById() {
		brandRepo.deleteById(brand.getBrandId());
		assertThat(brandRepo.existsById(brand.getBrandId())).isFalse();
	}
}
