package com.kraftwerking.vending.machine.spring.boot.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cash")
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "val")
    private BigDecimal val;

    @Column(name = "quantity")
    private int quantity;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public Cash(String type, BigDecimal val, int quantity) {
        this.type = type;
        this.val = val;
        this.quantity = quantity;
    }
}