package com.acmebank.accountmanager.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
@Builder
public class Account {
    @Id
    private Integer id;
    private String currency;
    private BigDecimal amount;

    public Account(Integer id, String currency, BigDecimal amount) {
        this.id = id;
        this.currency = currency;
        this.amount = amount;
    }

    public Account() {
    }
}