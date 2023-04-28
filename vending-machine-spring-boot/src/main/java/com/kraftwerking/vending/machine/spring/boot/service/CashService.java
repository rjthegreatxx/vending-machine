package com.kraftwerking.vending.machine.spring.boot.service;

import com.kraftwerking.vending.machine.spring.boot.model.Cash;
import com.kraftwerking.vending.machine.spring.boot.model.GetCashDTO;
import com.kraftwerking.vending.machine.spring.boot.model.ReturnCashDTO;
import com.kraftwerking.vending.machine.spring.boot.model.TotalCashDTO;
import com.kraftwerking.vending.machine.spring.boot.repository.CashRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CashService {

    @Autowired
    CashRepository cashRepository;
    public Cash saveCash(Cash cash) {
        log.info("Save cash " + cash.getId());
        return cashRepository
                .save(new Cash(cash.getType(), cash.getVal(), cash.getQuantity()));
    }

    public Cash putCash(Cash cash) {
        log.info("Put cash " + cash.getId());
        return cashRepository
                .save(cash);
    }

    public void deleteAll() {
        log.info("Delete all cash ");
        cashRepository.deleteAll();

    }

    public Optional<Cash> findById(long id) {
        log.info("Find cash " + id);
        return cashRepository.findById(id);
    }

    public List<Cash> findAllOrByType(String type) {
        log.info("Find all cash or by type");
        List<Cash> cash = new ArrayList<>();

        if (type == null)
            cash.addAll(cashRepository.findAll());
        else
            cash.addAll(cashRepository.findByTypeContaining(type));

        if (cash.isEmpty()) {
            return new ArrayList<>();
        }
        return cash;
    }

    public TotalCashDTO calculateTotalCash() {
        log.info("Calculate total cash");
        List<Cash> cashList = new ArrayList<>(cashRepository.findAll());
        BigDecimal totalCash = new BigDecimal("0.00");

        totalCash = cashList.stream().map(c -> c.getVal().multiply(new BigDecimal(c.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TotalCashDTO(cashList, totalCash);
    }

    public ReturnCashDTO getCash(GetCashDTO getCashDTO) {
        log.info("Get cash " + getCashDTO.getQuantity() + " " + getCashDTO.getType());
        List<Cash> cashList = cashRepository.findByTypeContaining(getCashDTO.getType());
        ReturnCashDTO returnCashDTO = new ReturnCashDTO(getCashDTO.getType(), "Out of cash", new BigDecimal("0.00"));

        if(cashList.isEmpty() || cashList.get(0).getQuantity() < getCashDTO.getQuantity()){
            return returnCashDTO;
        }

        Cash _cash = cashList.get(0);
        int newQty = _cash.getQuantity() - getCashDTO.getQuantity();
        _cash.setQuantity(newQty);
        cashRepository.save(_cash);

        BigDecimal amt = _cash.getVal().multiply(new BigDecimal(getCashDTO.getQuantity()));
        returnCashDTO.setMsg("Dispensing...");
        returnCashDTO.setAmount(amt);

        return returnCashDTO;
    }
}
