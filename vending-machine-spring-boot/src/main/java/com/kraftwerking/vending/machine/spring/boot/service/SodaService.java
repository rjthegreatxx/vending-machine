package com.kraftwerking.vending.machine.spring.boot.service;

import com.kraftwerking.vending.machine.spring.boot.model.Soda;
import com.kraftwerking.vending.machine.spring.boot.repository.CashRepository;
import com.kraftwerking.vending.machine.spring.boot.repository.SodaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SodaService {

    @Autowired
    SodaRepository sodaRepository;

    @Autowired
    CashRepository cashRepository;


    public List<Soda> findAllOrByName(String name) {
        log.info("Find all sodas by name");
        List<Soda> sodas = new ArrayList<>();

        if (name == null)
            sodas.addAll(sodaRepository.findAll());
        else
            sodas.addAll(sodaRepository.findByNameContaining(name));

        if (sodas.isEmpty()) {
            log.info("No sodas found");
            return new ArrayList<>();
        }
        return sodas;
    }

    public Optional<Soda> findById(long id) {
        log.info("Find sodas by id " + id);
        return sodaRepository.findById(id);

    }

    public Soda saveSoda(Soda soda) {
        log.info("Save soda " + soda.getId());
        return sodaRepository
                .save(new Soda(soda.getName(), soda.getPrice(), soda.getQuantity()));
    }

    public Soda putSoda(Soda soda) {
        log.info("Put soda " + soda.getId());
        return sodaRepository
                .save(soda);
    }

    public void deleteById(long id) {
        log.info("Delete soda " + id);
        sodaRepository.deleteById(id);

    }

    public void deleteAll() {
        log.info("Delete all sodas");
        sodaRepository.deleteAll();

    }
}
