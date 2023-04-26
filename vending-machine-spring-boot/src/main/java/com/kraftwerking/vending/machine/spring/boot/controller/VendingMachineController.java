package com.kraftwerking.vending.machine.spring.boot.controller;

import com.kraftwerking.vending.machine.spring.boot.model.Cash;
import com.kraftwerking.vending.machine.spring.boot.model.Soda;
import com.kraftwerking.vending.machine.spring.boot.service.CashService;
import com.kraftwerking.vending.machine.spring.boot.service.SodaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VendingMachineController {

    @Autowired
    SodaService sodaService;

    @Autowired
    CashService cashService;

    @GetMapping("/sodas")
    public ResponseEntity<List<Soda>> getAllSodas(@RequestParam(required = false) String name) {
        try {
            List<Soda> sodas = sodaService.findAllOrByName(name);

            return new ResponseEntity<>(sodas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cash")
    public ResponseEntity<List<Cash>> getAllCash(@RequestParam(required = false) String type) {
        try {
            List<Cash> cash = cashService.findAllOrByType(type);

            return new ResponseEntity<>(cash, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sodas/{id}")
    public ResponseEntity<Soda> getSodaById(@PathVariable("id") long id) {
        try {
            Optional<Soda> sodaData = sodaService.findById(id);

            if (sodaData.isPresent()) {
                return new ResponseEntity<>(sodaData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sodas")
    public ResponseEntity<Soda> createSoda(@RequestBody Soda soda) {
        try {
            Soda _soda = sodaService.saveSoda(soda);

            return new ResponseEntity<>(_soda, HttpStatus.CREATED);
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

    @PutMapping("/sodas/{id}")
    public ResponseEntity<Soda> updateSoda(@PathVariable("id") long id, @RequestBody Soda soda) {
        try {
            Optional<Soda> sodaData = sodaService.findById(id);

            if (sodaData.isPresent()) {
                Soda _soda = sodaData.get();
                _soda.setName(soda.getName());
                _soda.setPrice(soda.getPrice());
                _soda.setQuantity(soda.getQuantity());
                return new ResponseEntity<>(sodaService.putSoda(_soda), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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

    @DeleteMapping("/sodas/{id}")
    public ResponseEntity<HttpStatus> deleteSoda(@PathVariable("id") long id) {
        try {
            sodaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sodas")
    public ResponseEntity<HttpStatus> deleteAllSodas() {
        try {
            sodaService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
