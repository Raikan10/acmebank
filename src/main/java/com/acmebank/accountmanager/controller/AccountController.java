package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.Request.AccountBalanceRequest;
import com.acmebank.accountmanager.Response.AccountBalanceResponse;
import com.acmebank.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public AccountBalanceResponse getAccountBalance(@PathVariable Integer id) {
        AccountBalanceRequest request = AccountBalanceRequest.builder().id(id).build();
        return accountService.getAccountBalance(request);
    }
}
