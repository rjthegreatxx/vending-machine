package com.kraftwerking.vending.machine.spring.boot.repository;

import com.kraftwerking.vending.machine.spring.boot.model.Soda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SodaRepository extends JpaRepository<Soda, Long> {

    List<Soda> findByNameContaining(String name);

}
