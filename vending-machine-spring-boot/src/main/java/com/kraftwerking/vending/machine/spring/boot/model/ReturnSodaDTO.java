package com.kraftwerking.vending.machine.spring.boot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ReturnSodaDTO {

    String name;
    int quantity;
    String msg;
    BigDecimal changeAmount;
}