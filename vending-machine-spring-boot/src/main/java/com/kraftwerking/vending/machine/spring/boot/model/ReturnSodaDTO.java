package com.kraftwerking.vending.machine.spring.boot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReturnSodaDTO {

    String name;
    int quantity;
    String msg;
    String changeAmount;
}