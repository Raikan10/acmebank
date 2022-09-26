package com.acmebank.accountmanager.Response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ErrorResponse {
    private final String message;
    private final boolean success = false;

    public ErrorResponse(String message) {
        this.message = message;
    }
}