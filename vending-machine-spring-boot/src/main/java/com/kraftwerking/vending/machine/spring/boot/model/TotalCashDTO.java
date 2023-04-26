package com.kraftwerking.vending.machine.spring.boot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class TotalCashDTO {

    List<Cash> cashList;
    BigDecimal totalCash;

}