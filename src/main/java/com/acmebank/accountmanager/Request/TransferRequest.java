package com.acmebank.accountmanager.Request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@Builder
public class TransferRequest {
    @NonNull private final Integer from;
    @NonNull private final Integer to;
    @NonNull private final String currency;
    @NonNull private final BigDecimal amount;
}
