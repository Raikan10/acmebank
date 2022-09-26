package com.acmebank.accountmanager.service;


import com.acmebank.accountmanager.Request.AccountBalanceRequest;
import com.acmebank.accountmanager.Response.AccountBalanceResponse;
import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.repository.AccountEntityRepository;
import com.acmebank.accountmanager.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public
class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public AccountBalanceResponse getAccountBalance(AccountBalanceRequest request){
        Account account = accountRepository.findById(request.getId()).orElseThrow(AccountNotFoundException::new);
        return createResponse(account);
    }

    private AccountBalanceResponse createResponse(Account account){
        return AccountBalanceResponse.builder()
                .success(true)
                .amount(account.getAmount())
                .currency(account.getCurrency())
                .build();
    }
}
