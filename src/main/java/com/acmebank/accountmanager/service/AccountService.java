package com.acmebank.accountmanager.service;


import com.acmebank.accountmanager.Request.AccountBalanceRequest;
import com.acmebank.accountmanager.Request.TransferRequest;
import com.acmebank.accountmanager.Response.AccountBalanceResponse;
import com.acmebank.accountmanager.Response.TransferResponse;
import com.acmebank.accountmanager.exception.InsufficientBalanceException;
import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public
class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public AccountBalanceResponse getAccountBalance(AccountBalanceRequest request){
        return createAccountBalanceResponse(getAccount(request.getId()));
    }

    @Transactional
    public TransferResponse performTransfer(TransferRequest request){
        Account from = getAccount(request.getFrom());
        Account to = getAccount(request.getTo());
        if(request.getAmount().compareTo(from.getAmount()) > 0){
            throw new InsufficientBalanceException();
        }
        from.setAmount(from.getAmount().subtract(request.getAmount()));
        to.setAmount(to.getAmount().add(request.getAmount()));

        accountRepository.save(from);
        accountRepository.save(to);

        return createTransferResponse(from);
    }

    private AccountBalanceResponse createAccountBalanceResponse(Account account){
        return AccountBalanceResponse.builder()
                .success(true)
                .balance(account.getAmount())
                .currency(account.getCurrency())
                .build();
    }

    private TransferResponse createTransferResponse(Account from){
        return TransferResponse.builder()
                .success(true)
                .balance(from.getAmount())
                .currency(from.getCurrency())
                .message("Transfer was successful")
                .build();
    }

    private Account getAccount(Integer accountId){
        return accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
    }
}
