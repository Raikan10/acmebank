package com.acmebank.accountmanager.Request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
@EqualsAndHashCode
public class AccountBalanceRequest {
    @NonNull
    private final Integer id;
}
