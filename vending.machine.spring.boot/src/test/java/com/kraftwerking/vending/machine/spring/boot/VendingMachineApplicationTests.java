package com.kraftwerking.vending.machine.spring.boot;

import com.kraftwerking.vending.machine.spring.boot.model.Soda;
import com.kraftwerking.vending.machine.spring.boot.repository.SodaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VendingMachineApplicationTests {

	@Autowired
	SodaRepository repository;

	@Test
	void contextLoads() {
	}

	//@Test
	public void should_find_no_sodas_if_repository_is_empty() {
		Iterable<Soda> sodas = repository.findAll();
		List<Soda> sodasList = new ArrayList<Soda>();
		sodas.forEach(sodasList::add);

		assertTrue(sodasList.isEmpty());

	}

	@Test
	public void should_store_a_soda() {
		Soda soda = repository.save(new Soda("Coca Cola", new BigDecimal("0.75"), 10));

		assertNotNull(soda);
		assertTrue(soda.getName().equals("Coca Cola"));
		assertTrue(soda.getPrice().equals(new BigDecimal("0.75")));
		assertTrue(soda.getQuantity() == 10);
	}

	@Test
	public void should_find_all_sodas() {
		Soda soda1 = repository.save(new Soda("Diet Coke", new BigDecimal("0.75"), 10));

		Soda soda2 = repository.save(new Soda("Sprite", new BigDecimal("0.75"), 10));

		Soda soda3 = repository.save(new Soda("Mug Root Beer", new BigDecimal("0.75"), 10));

		Iterable<Soda> sodas = repository.findAll();
		List<Soda> sodasList = new ArrayList<Soda>();
		sodas.forEach(sodasList::add);
		assertTrue(sodasList.size() > 3);

	}

	@Test
	public void should_find_soda_by_id() {
		Soda soda1 = repository.save(new Soda("Orange Fanta", new BigDecimal("0.75"), 10));

		Soda soda2 = repository.save(new Soda("Cream Soda", new BigDecimal("0.75"), 10));

		Soda foundSoda = repository.findById(soda2.getId()).get();

		assertNotNull(foundSoda);
		assertEquals("Cream Soda", foundSoda.getName());
		assertTrue(foundSoda.getPrice().equals(new BigDecimal("0.75")));
	}

//	@Test
	public void should_delete_soda_by_id() {
		Soda soda1 = repository.save(new Soda("Grape Fanta", new BigDecimal("0.75"), 10));

		Soda soda2 = repository.save(new Soda("Cherry Coke", new BigDecimal("0.75"), 10));

		Soda soda3 = repository.save(new Soda("Mountain Dew", new BigDecimal("0.75"), 10));

		Iterable<Soda> sodas = repository.findAll();
		List<Soda> sodasList = new ArrayList<Soda>();
		sodas.forEach(sodasList::add);

		int size = sodasList.size();

		repository.deleteById(soda1.getId());

		sodas = repository.findAll();
		sodasList = new ArrayList<Soda>();
		sodas.forEach(sodasList::add);

		int size2 = sodasList.size();

		assertTrue(size > size2);

	}

//	@Test
	public void should_delete_all_sodas() {
		Soda soda3 = repository.save(new Soda("Gatorade", new BigDecimal("0.75"), 10));

		repository.deleteAll();

		Iterable<Soda> sodas = repository.findAll();
		List<Soda> sodasList = new ArrayList<Soda>();
		sodas.forEach(sodasList::add);
		assertTrue(sodasList.isEmpty());

	}
}
