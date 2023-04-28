package com.kraftwerking.vending.machine.spring.boot.controller;

import com.kraftwerking.vending.machine.spring.boot.model.Cash;
import com.kraftwerking.vending.machine.spring.boot.model.GetCashDTO;
import com.kraftwerking.vending.machine.spring.boot.model.ReturnCashDTO;
import com.kraftwerking.vending.machine.spring.boot.model.TotalCashDTO;
import com.kraftwerking.vending.machine.spring.boot.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CashController {

    @Autowired
    CashService cashService;


    @GetMapping("/cash")
    public ResponseEntity<List<Cash>> getAllCash(@RequestParam(required = false) String type) {
        try {
            List<Cash> _cash = cashService.findAllOrByType(type);

            return new ResponseEntity<>(_cash, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cash/total")
    public ResponseEntity<TotalCashDTO> getTotalCash() {
        try {
            TotalCashDTO totalCashDTO = cashService.calculateTotalCash();

            return new ResponseEntity<>(totalCashDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cash/getcash")
    public ResponseEntity<ReturnCashDTO> getCash(@RequestBody GetCashDTO getCashDTO) {
        try {
            ReturnCashDTO _returnCashDTO = cashService.getCash(getCashDTO);

            return new ResponseEntity<>(_returnCashDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cash")
    public ResponseEntity<Cash> createCash(@RequestBody Cash cash) {
        try {
            Cash _cash = cashService.saveCash(cash);

            return new ResponseEntity<>(_cash, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cash/{id}")
    public ResponseEntity<Cash> updateCash(@PathVariable("id") long id, @RequestBody Cash cash) {
        try {
            Optional<Cash> cashData = cashService.findById(id);

            if (cashData.isPresent()) {
                Cash _cash = cashData.get();
                _cash.setType(cash.getType());
                _cash.setVal(cash.getVal());
                _cash.setQuantity(cash.getQuantity());
                return new ResponseEntity<>(cashService.putCash(_cash), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cash")
    public ResponseEntity<HttpStatus> deleteAllCash() {
        try {
            cashService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
