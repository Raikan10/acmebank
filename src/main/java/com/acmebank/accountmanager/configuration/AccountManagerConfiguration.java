package com.acmebank.accountmanager.configuration;

import com.acmebank.accountmanager.model.AccountRepository;
import com.acmebank.accountmanager.repository.AccountEntityRepository;
import com.acmebank.accountmanager.repository.JpaAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.acmebank.accountmanager.repository")
public class AccountManagerConfiguration {

    @Bean
    public AccountRepository accountRepository(AccountEntityRepository accountEntityRepository) {
        return new JpaAccountRepository(accountEntityRepository);
    }

}