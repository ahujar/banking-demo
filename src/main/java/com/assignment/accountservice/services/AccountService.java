package com.assignment.accountservice.services;

import com.assignment.accountservice.model.dto.Account;
import com.assignment.accountservice.repositories.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@Slf4j
public class AccountService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Optional<Set<Account>> getAccountsByCustomerId(Long customerId) {

        log.info("getting Account List for customer Id: " + customerId);
        Optional<Set<Account>> accountSet = Optional.empty();

        Set<Account> accounts = accountsRepository.findByCustomer_customerId(customerId).stream().map(
                account -> Account.builder()
                        .accountName(account.getAccountName())
                        .accountNumber(account.getAccountId())
                        .accountType(account.getAccountType().name())
                        .availableBalance(account.getAccountBalance())
                        .balanceDate(account.getBalanceDate())
                        .currencyCode(account.getCurrencyType().name())
                        .customerNumber(account.getCustomer().getCustomerId())
                        .build()
        ).collect(toSet());

        if (!CollectionUtils.isEmpty(accounts)) {
            accountSet = Optional.of(accounts);
        }
        return accountSet;
    }

}
