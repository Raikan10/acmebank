package com.acmebank.accountmanager.Response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
@Getter
public class AccountBalanceResponse {
    private final boolean success;
    private final BigDecimal amount;
    private final String currency;
}
