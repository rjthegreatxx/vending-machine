package com.kraftwerking.vending.machine.spring.boot.controller;

import com.kraftwerking.vending.machine.spring.boot.model.Soda;
import com.kraftwerking.vending.machine.spring.boot.service.SodaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Slf4j
@RequestMapping("/api")
public class SodaController {

    @Autowired
    SodaService sodaService;

    @GetMapping("/sodas")
    public ResponseEntity<List<Soda>> getAllSodas(@RequestParam(required = false) String name) {
        try {
            log.info("Get all sodas");
            List<Soda> sodas = sodaService.findAllOrByName(name);

            return new ResponseEntity<>(sodas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sodas/{id}")
    public ResponseEntity<Soda> getSodaById(@PathVariable("id") long id) {
        try {
            log.info("Get soda  " + id);
            Optional<Soda> sodaData = sodaService.findById(id);

            return sodaData.map(soda -> new ResponseEntity<>(soda, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sodas")
    public ResponseEntity<Soda> createSoda(@RequestBody Soda soda) {
        try {
            log.info("Save all sodas");
            Soda _soda = sodaService.saveSoda(soda);

            return new ResponseEntity<>(_soda, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sodas/{id}")
    public ResponseEntity<Soda> updateSoda(@PathVariable("id") long id, @RequestBody Soda soda) {
        try {
            log.info("Put soda  " + id);
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

    @DeleteMapping("/sodas/{id}")
    public ResponseEntity<HttpStatus> deleteSoda(@PathVariable("id") long id) {
        try {
            log.info("Delete soda  " + id);
            sodaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sodas")
    public ResponseEntity<HttpStatus> deleteAllSodas() {
        try {
            log.info("Delete all sodas");
            sodaService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
