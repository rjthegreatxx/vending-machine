package com.kraftwerking.vending.machine.spring.boot.service;

import com.kraftwerking.vending.machine.spring.boot.model.*;
import com.kraftwerking.vending.machine.spring.boot.repository.CashRepository;
import com.kraftwerking.vending.machine.spring.boot.repository.SalesHistoryRepository;
import com.kraftwerking.vending.machine.spring.boot.repository.SodaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class VendingMachineService {

    @Autowired
    SodaRepository sodaRepository;

    @Autowired
    CashRepository cashRepository;

    @Autowired
    SalesHistoryRepository salesHistoryRepository;

    public ReturnSodaDTO purchaseSoda(PurchaseSodaDTO purchaseSodaDTO) {
        log.info("Purchase soda id " + purchaseSodaDTO.getId());
        Soda _soda = sodaRepository.findById(purchaseSodaDTO.getId()).orElse(null);

        ReturnSodaDTO returnSodaDTO = null;

        try {
            if(_soda == null){
                returnSodaDTO = new ReturnSodaDTO(null, 0, "Soda id " + purchaseSodaDTO.getId() + " not found", purchaseSodaDTO.getDepositAmount());
            } else if(_soda.getQuantity() == 0){
                returnSodaDTO =  new ReturnSodaDTO(null, 0, "Soda " + _soda.getName() + " sold out!", purchaseSodaDTO.getDepositAmount());
            } else if(_soda.getPrice().compareTo(purchaseSodaDTO.getDepositAmount()) > 0){
                returnSodaDTO =  new ReturnSodaDTO(_soda.getName(), 0, "Please deposit additional quarters", purchaseSodaDTO.getDepositAmount());
            //purchase and log
            } else if(_soda.getPrice().compareTo(purchaseSodaDTO.getDepositAmount()) == 0){
                Cash _cash = cashRepository.findByTypeContaining("Quarters").get(0);
                int numberOfQuarters = (int)(purchaseSodaDTO.getDepositAmount().floatValue() / .25);
                _cash.setQuantity(_cash.getQuantity() + numberOfQuarters);
                cashRepository.save(_cash);

                _soda.setQuantity(_soda.getQuantity() - 1);
                sodaRepository.save(_soda);

                salesHistoryRepository.save(new SalesHistory(_soda.getName(), purchaseSodaDTO.getQuantity()));
                returnSodaDTO =  new ReturnSodaDTO(_soda.getName(), 1, "Enjoy!", new BigDecimal("0.00"));
            } else if(_soda.getPrice().compareTo(purchaseSodaDTO.getDepositAmount()) < 0){
                Cash _cash = cashRepository.findByTypeContaining("Quarters").get(0);

                int numberOfQuartersRequiredForPurchase = (int)(_soda.getPrice().floatValue() / .25);
                int numberOfQuartersDeposited = (int)(purchaseSodaDTO.getDepositAmount().floatValue() / .25);
                int numberOfQuartersToReturn = numberOfQuartersDeposited - numberOfQuartersRequiredForPurchase;
                double valToReturn = numberOfQuartersToReturn * .25;
                _cash.setQuantity(_cash.getQuantity() + numberOfQuartersRequiredForPurchase);
                cashRepository.save(_cash);

                _soda.setQuantity(_soda.getQuantity() - 1);
                sodaRepository.save(_soda);

                salesHistoryRepository.save(new SalesHistory(_soda.getName(), purchaseSodaDTO.getQuantity()));
                returnSodaDTO =  new ReturnSodaDTO(_soda.getName(), 1, "Enjoy!", new BigDecimal(String.format("%.2f", valToReturn)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return returnSodaDTO;
    }

}
