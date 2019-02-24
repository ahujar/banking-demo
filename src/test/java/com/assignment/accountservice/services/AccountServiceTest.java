package com.assignment.accountservice.services;

import com.assignment.accountservice.model.dto.Account;
import com.assignment.accountservice.model.entity.Accounts;
import com.assignment.accountservice.model.entity.Customers;
import com.assignment.accountservice.model.entity.enums.AccountType;
import com.assignment.accountservice.model.entity.enums.CurrencyType;
import com.assignment.accountservice.repositories.AccountsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountsRepository accountsRepository;

    @Test
    public void shouldGetAccountsByCustomerId() {

        Customers testCustomer = new Customers(12345L, "John", "Doe", null);
        Set<Accounts> accountSet = new HashSet<>();
        Accounts accountOne = new Accounts();
        accountOne.setAccountId(101L);
        accountOne.setAccountBalance(new BigDecimal(50000));
        accountOne.setAccountType(AccountType.SAVINGS);
        accountOne.setCurrencyType(CurrencyType.AUD);
        accountOne.setCustomer(testCustomer);
        accountOne.setBalanceDate(LocalDateTime.now());

        Accounts accountTwo = new Accounts();
        accountTwo.setAccountId(102L);
        accountTwo.setAccountBalance(new BigDecimal(80000));
        accountTwo.setAccountType(AccountType.SAVINGS);
        accountTwo.setCurrencyType(CurrencyType.AUD);
        accountTwo.setCustomer(testCustomer);
        accountTwo.setBalanceDate(LocalDateTime.now());

        accountSet.add(accountOne);
        accountSet.add(accountTwo);

        when(accountsRepository.findByCustomer_customerId(12345L)).thenReturn(accountSet);
        Optional<Set<Account>> accounts = accountService.getAccountsByCustomerId(12345L);
        assert accounts.isPresent();
        assertEquals(2,accounts.get().size());
    }

}