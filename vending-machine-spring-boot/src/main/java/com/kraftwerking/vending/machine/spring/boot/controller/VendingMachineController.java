package com.kraftwerking.vending.machine.spring.boot.controller;

import com.kraftwerking.vending.machine.spring.boot.model.Soda;
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

}
