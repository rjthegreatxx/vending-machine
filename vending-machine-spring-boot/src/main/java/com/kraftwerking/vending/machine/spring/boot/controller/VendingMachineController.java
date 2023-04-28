package com.kraftwerking.vending.machine.spring.boot.controller;

import com.kraftwerking.vending.machine.spring.boot.model.PurchaseSodaDTO;
import com.kraftwerking.vending.machine.spring.boot.model.ReturnSodaDTO;
import com.kraftwerking.vending.machine.spring.boot.service.VendingMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Slf4j
@RequestMapping("/api")
public class VendingMachineController {

    @Autowired
    VendingMachineService vendingMachineService;

    @PostMapping("/vendingmachine/purchase")
    public ResponseEntity<ReturnSodaDTO> createCash(@RequestBody PurchaseSodaDTO purchaseSodaDTO) {
        try {
            log.info("Purchase soda " + purchaseSodaDTO.getId());
            ReturnSodaDTO returnSodaDTO = vendingMachineService.purchaseSoda(purchaseSodaDTO);

            return new ResponseEntity<>(returnSodaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
