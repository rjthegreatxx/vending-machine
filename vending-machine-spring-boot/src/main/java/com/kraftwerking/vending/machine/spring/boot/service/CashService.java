package com.kraftwerking.vending.machine.spring.boot.service;

import com.kraftwerking.vending.machine.spring.boot.model.Cash;
import com.kraftwerking.vending.machine.spring.boot.repository.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CashService {

    @Autowired
    CashRepository cashRepository;
    public Cash saveCash(Cash cash) {
        Cash _cash= cashRepository
                .save(new Cash(cash.getType(), cash.getVal(), cash.getQuantity()));
        return _cash;
    }

    public Cash putCash(Cash cash) {
        Cash _cash = cashRepository
                .save(cash);
        return _cash;
    }

    public void deleteAll() {
        cashRepository.deleteAll();

    }

    public Optional<Cash> findById(long id) {
        Optional<Cash> cashData = cashRepository.findById(id);
        return  cashData;
    }

    public List<Cash> findAllOrByType(String type) {
        List<Cash> cash = new ArrayList<Cash>();

        if (type == null)
            cash.addAll(cashRepository.findAll());
        else
            cash.addAll(cashRepository.findByTypeContaining(type));

        if (cash.isEmpty()) {
            return new ArrayList<>();
        }
        return cash;
    }
}
