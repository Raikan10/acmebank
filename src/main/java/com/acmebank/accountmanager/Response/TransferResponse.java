package com.acmebank.accountmanager.Response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
@EqualsAndHashCode
@Getter
@Builder
public class TransferResponse {
    private final boolean success;
    private final String message;
    private final String currency;
    private final BigDecimal balance;
}
