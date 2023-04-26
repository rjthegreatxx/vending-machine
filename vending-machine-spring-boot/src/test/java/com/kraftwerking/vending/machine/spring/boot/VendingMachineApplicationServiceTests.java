package com.kraftwerking.vending.machine.spring.boot;

import com.kraftwerking.vending.machine.spring.boot.model.GetCashDTO;
import com.kraftwerking.vending.machine.spring.boot.model.ReturnCashDTO;
import com.kraftwerking.vending.machine.spring.boot.model.TotalCashDTO;
import com.kraftwerking.vending.machine.spring.boot.service.CashService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VendingMachineApplicationServiceTests {

	@Autowired
	CashService cashService;

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

}
