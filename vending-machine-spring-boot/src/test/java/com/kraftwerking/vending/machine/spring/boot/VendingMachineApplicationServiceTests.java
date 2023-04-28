package com.kraftwerking.vending.machine.spring.boot;

import com.kraftwerking.vending.machine.spring.boot.model.*;
import com.kraftwerking.vending.machine.spring.boot.repository.CashRepository;
import com.kraftwerking.vending.machine.spring.boot.repository.SodaRepository;
import com.kraftwerking.vending.machine.spring.boot.service.CashService;
import com.kraftwerking.vending.machine.spring.boot.service.VendingMachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VendingMachineApplicationServiceTests {

	@Autowired
	CashService cashService;

	@Autowired
	VendingMachineService vendingMachineService;

	@Autowired
	SodaRepository repository;

	@Autowired
	CashRepository cashRepository;


	@Test
	public void should_find_totalcash() {
		TotalCashDTO totalCashDTO = cashService.calculateTotalCash();
		assertNotNull(totalCashDTO);
		assertTrue(totalCashDTO.getCashList().size() > 0);
	}

	@Test
	public void should_find_getcashresult() {
		GetCashDTO getCashDTO = new GetCashDTO("quarters", 1);
		ReturnCashDTO returnCashDTO = cashService.getCash(getCashDTO);
		assertNotNull(returnCashDTO);
		assertNotNull(returnCashDTO.getAmount());
	}

	@Test
	public void should_make_successful_purchase() {
		Soda soda = repository.save(new Soda("Caffeine Free Diet Coke", new BigDecimal("0.75"), 10));
		assertNotNull(soda);

		Cash cash = cashRepository.findByTypeContaining("Quarters").get(0);
		assertNotNull(cash);

		PurchaseSodaDTO purchaseSodaDTO = new PurchaseSodaDTO(soda.getId(),1, "0.75");
		ReturnSodaDTO returnSodaDTO = vendingMachineService.purchaseSoda(purchaseSodaDTO);
		assertNotNull(returnSodaDTO);
		assertEquals(returnSodaDTO.getQuantity(), 1);
		assertEquals(returnSodaDTO.getMsg(), "Enjoy!");
		assertEquals(returnSodaDTO.getChangeAmount(), "0.00");

		Soda _soda = repository.findById(soda.getId()).get();
		assertEquals(returnSodaDTO.getName(), _soda.getName());
		assertEquals(soda.getQuantity() - _soda.getQuantity(), 1);

		Cash _cash = cashRepository.findByTypeContaining("Quarters").get(0);
		assertEquals(_cash.getQuantity(), cash.getQuantity() + 3);

	}

	@Test
	public void should_make_successfulwithchange_purchase() {
		Soda soda = repository.save(new Soda("Caffeine Free Diet Coke", new BigDecimal("0.75"), 10));
		assertNotNull(soda);

		Cash cash = cashRepository.findByTypeContaining("Quarters").get(0);
		assertNotNull(cash);

		PurchaseSodaDTO purchaseSodaDTO = new PurchaseSodaDTO(soda.getId(),1, "1.25");
		ReturnSodaDTO returnSodaDTO = vendingMachineService.purchaseSoda(purchaseSodaDTO);
		assertNotNull(returnSodaDTO);
		assertEquals(returnSodaDTO.getQuantity(), 1);
		assertEquals(returnSodaDTO.getMsg(), "Enjoy!");
		assertEquals(returnSodaDTO.getChangeAmount(), "0.50");

		Soda _soda = repository.findById(soda.getId()).get();
		assertEquals(returnSodaDTO.getName(), _soda.getName());
		assertEquals(soda.getQuantity() - _soda.getQuantity(), 1);

		Cash _cash = cashRepository.findByTypeContaining("Quarters").get(0);
		assertEquals(_cash.getQuantity(), cash.getQuantity() + 3);
	}

	@Test
	public void should_make_soldout_purchase() {
		Soda soda = repository.save(new Soda("Caffeine Free Diet Coke", new BigDecimal("0.75"), 0));
		assertNotNull(soda);

		PurchaseSodaDTO purchaseSodaDTO = new PurchaseSodaDTO(soda.getId(),1, "0.75");
		ReturnSodaDTO returnSodaDTO = vendingMachineService.purchaseSoda(purchaseSodaDTO);
		assertNotNull(returnSodaDTO);
		assertEquals(returnSodaDTO.getQuantity(), 0);
		assertTrue(returnSodaDTO.getMsg().contains("sold out!"));
		assertEquals(returnSodaDTO.getQuantity(),0);
		assertEquals(returnSodaDTO.getChangeAmount(),purchaseSodaDTO.getDepositAmount());
	}

	@Test
	public void should_make_notenoughcash_purchase() {
		Soda soda = repository.save(new Soda("Caffeine Free Diet Coke", new BigDecimal("0.75"), 10));
		assertNotNull(soda);

		PurchaseSodaDTO purchaseSodaDTO = new PurchaseSodaDTO(soda.getId(),1, "0.25");
		ReturnSodaDTO returnSodaDTO = vendingMachineService.purchaseSoda(purchaseSodaDTO);
		assertNotNull(returnSodaDTO);
		assertEquals(returnSodaDTO.getQuantity(), 0);
		assertTrue(returnSodaDTO.getMsg().contains("Please deposit additional quarters"));
		assertEquals(returnSodaDTO.getQuantity(),0);
		assertEquals(returnSodaDTO.getChangeAmount(),purchaseSodaDTO.getDepositAmount());
	}

}
