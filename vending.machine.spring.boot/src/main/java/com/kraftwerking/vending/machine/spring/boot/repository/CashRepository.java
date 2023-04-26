package com.kraftwerking.vending.machine.spring.boot.repository;

import com.kraftwerking.vending.machine.spring.boot.model.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Long> {

    List<Cash> findByTypeContaining(String type);

}
