package com.acmebank.accountmanager.Service;

import com.acmebank.accountmanager.Request.AccountBalanceRequest;
import com.acmebank.accountmanager.Request.TransferRequest;
import com.acmebank.accountmanager.Response.AccountBalanceResponse;
import com.acmebank.accountmanager.Response.TransferResponse;
import com.acmebank.accountmanager.exception.AccountNotFoundException;
import com.acmebank.accountmanager.exception.InsufficientBalanceException;
import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.repository.AccountRepository;
import com.acmebank.accountmanager.service.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock private AccountRepository accountRepository;
    @InjectMocks private AccountService accountService;

    Account account1,account2;

    @BeforeEach
    void setup(){
        account1  = Account.builder()
                    .id(12345678)
                    .currency("HKD")
                    .amount(BigDecimal.valueOf(100))
                    .build();
        account2  = Account.builder()
                .id(88888888)
                .currency("HKD")
                .amount(BigDecimal.valueOf(100))
                .build();
    }

    @AfterEach
    void teardown(){
        account1 = null;
        account2 = null;
    }


    @Test
    void testAccountNotFound(){
        when(accountRepository.findById(11111111)).thenReturn(Optional.empty());

        AccountBalanceRequest request = AccountBalanceRequest.builder().id(11111111).build();
        Throwable throwable = catchThrowable(() -> accountService.getAccountBalance(request));

        assertThat(throwable).isNotNull().isInstanceOf(AccountNotFoundException.class);
    }



    @Test
    void testGetAccountBalance() {
        when(accountRepository.findById(12345678)).thenReturn(Optional.of(account1));

        AccountBalanceRequest request = AccountBalanceRequest.builder().id(12345678).build();
        AccountBalanceResponse response = accountService.getAccountBalance(request);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getCurrency()).isEqualTo("HKD");
        assertThat(response.getBalance()).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    void testInsufficientBalance(){
        when(accountRepository.findById(12345678)).thenReturn(Optional.of(account1));
        when(accountRepository.findById(88888888)).thenReturn(Optional.of(account2));
        TransferRequest request = TransferRequest.builder()
                .from(12345678)
                .to(88888888)
                .amount(BigDecimal.valueOf(101))
                .currency("HKD")
                .build();

        Throwable throwable = catchThrowable(() ->  accountService.performTransfer(request));

        assertThat(throwable).isNotNull().isInstanceOf(InsufficientBalanceException.class);

    }


    @Test
    void performTransfer() {
        when(accountRepository.findById(12345678)).thenReturn(Optional.of(account1));
        when(accountRepository.findById(88888888)).thenReturn(Optional.of(account2));
        when(accountRepository.save(account1)).thenReturn(account1);
        when(accountRepository.save(account2)).thenReturn(account2);

        TransferRequest request = TransferRequest.builder()
                .from(12345678)
                .to(88888888)
                .amount(BigDecimal.valueOf(1))
                .currency("HKD")
                .build();

        TransferResponse response = accountService.performTransfer(request);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getCurrency()).isEqualTo("HKD");
        assertThat(response.getBalance()).isEqualTo(BigDecimal.valueOf(99));

    }
}
