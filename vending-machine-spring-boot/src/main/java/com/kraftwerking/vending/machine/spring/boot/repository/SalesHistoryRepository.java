package com.kraftwerking.vending.machine.spring.boot.repository;

import com.kraftwerking.vending.machine.spring.boot.model.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Long> {

}