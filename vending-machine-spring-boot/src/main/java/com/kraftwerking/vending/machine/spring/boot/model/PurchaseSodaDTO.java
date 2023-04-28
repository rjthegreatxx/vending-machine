package com.kraftwerking.vending.machine.spring.boot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PurchaseSodaDTO {

    long id;
    int quantity;
    String depositAmount;
}