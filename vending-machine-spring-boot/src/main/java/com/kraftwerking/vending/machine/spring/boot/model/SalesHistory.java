package com.kraftwerking.vending.machine.spring.boot.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sales_history")
public class SalesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    public SalesHistory(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}