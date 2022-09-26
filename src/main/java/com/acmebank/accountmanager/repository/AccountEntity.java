package com.acmebank.accountmanager.repository;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AccountEntity {

    @Id private Integer id;

    private String currency;

    private BigDecimal amount;

    public AccountEntity() {}

    public AccountEntity(Integer id, String currency, BigDecimal amount) {
        this.id = id;
        this.currency = currency;
        this.amount = amount;
    }
}