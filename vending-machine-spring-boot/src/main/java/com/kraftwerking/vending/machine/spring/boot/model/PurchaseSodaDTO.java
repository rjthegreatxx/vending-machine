package com.kraftwerking.vending.machine.spring.boot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class PurchaseSodaDTO {

    long id;
    int quantity;
    BigDecimal depositAmount;
}