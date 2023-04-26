package com.kraftwerking.vending.machine.spring.boot.service;

import com.kraftwerking.vending.machine.spring.boot.model.Soda;
import com.kraftwerking.vending.machine.spring.boot.repository.CashRepository;
import com.kraftwerking.vending.machine.spring.boot.repository.SodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SodaService {

    @Autowired
    SodaRepository sodaRepository;

    @Autowired
    CashRepository cashRepository;


    public List<Soda> findAllOrByName(String name) {
        List<Soda> sodas = new ArrayList<>();

        if (name == null)
            sodas.addAll(sodaRepository.findAll());
        else
            sodas.addAll(sodaRepository.findByNameContaining(name));

        if (sodas.isEmpty()) {
            return new ArrayList<>();
        }
        return sodas;
    }

    public Optional<Soda> findById(long id) {
        return sodaRepository.findById(id);

    }

    public Soda saveSoda(Soda soda) {
        return sodaRepository
                .save(new Soda(soda.getName(), soda.getPrice(), soda.getQuantity()));
    }

    public Soda putSoda(Soda soda) {
        return sodaRepository
                .save(soda);
    }

    public void deleteById(long id) {
        sodaRepository.deleteById(id);

    }

    public void deleteAll() {
        sodaRepository.deleteAll();

    }
}
