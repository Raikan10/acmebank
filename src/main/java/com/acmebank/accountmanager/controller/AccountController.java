package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.Request.AccountBalanceRequest;
import com.acmebank.accountmanager.Request.TransferRequest;
import com.acmebank.accountmanager.Response.AccountBalanceResponse;
import com.acmebank.accountmanager.Response.TransferResponse;
import com.acmebank.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "accounts/{id}", produces = "application/json")
    public AccountBalanceResponse getAccountBalance(@PathVariable Integer id) {
        AccountBalanceRequest request = AccountBalanceRequest.builder().id(id).build();
        return accountService.getAccountBalance(request);
    }

    @PostMapping(path = "transfer", produces = "application/json")
    public TransferResponse performTransfer(@RequestBody TransferRequest request) {
        return accountService.performTransfer(request);
    }
}
