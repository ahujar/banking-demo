package com.assignment.accountservice.services;

import com.assignment.accountservice.model.dto.Transaction;
import com.assignment.accountservice.model.entity.Accounts;
import com.assignment.accountservice.model.entity.Customers;
import com.assignment.accountservice.model.entity.Transactions;
import com.assignment.accountservice.model.entity.enums.AccountType;
import com.assignment.accountservice.model.entity.enums.CurrencyType;
import com.assignment.accountservice.model.entity.enums.TransactionType;
import com.assignment.accountservice.repositories.TransactionsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionsRepository transactionsRepository;

    @Test
    public void getTransactionsForAccount() {

        Customers testCustomer = new Customers(12345L, "John", "Doe", null);

        Accounts accountOne = new Accounts();
        accountOne.setAccountId(101L);
        accountOne.setAccountName("Personal Account");
        accountOne.setAccountBalance(new BigDecimal(50000));
        accountOne.setAccountType(AccountType.SAVINGS);
        accountOne.setCurrencyType(CurrencyType.AUD);
        accountOne.setCustomer(testCustomer);
        accountOne.setBalanceDate(LocalDateTime.now());

        Set<Transactions> transactionsSet = new HashSet<>();
        Transactions transactionOne = new Transactions();

        transactionOne.setAccount(accountOne);
        transactionOne.setFromAccountId(1000567L);
        transactionOne.setTransactionDate(LocalDateTime.now());
        transactionOne.setTransactionAmount(new BigDecimal(100));
        transactionOne.setTransactionId(10001);
        transactionOne.setTransactionType(TransactionType.CREDIT);
        transactionOne.setTransactionNarrative("SENDING MONEY");


        Transactions transactionTwo = new Transactions();

        transactionTwo.setAccount(accountOne);
        transactionTwo.setFromAccountId(1000567L);
        transactionTwo.setTransactionDate(LocalDateTime.now());
        transactionTwo.setTransactionAmount(new BigDecimal(500));
        transactionTwo.setTransactionId(10002);
        transactionTwo.setTransactionType(TransactionType.CREDIT);
        transactionTwo.setTransactionNarrative("SENDING MONEY");

        transactionsSet.add(transactionOne);
        transactionsSet.add(transactionTwo);
        when(transactionsRepository.findByAccount_accountId(1002L)).thenReturn(transactionsSet);
        Optional<Set<Transaction>> transactionsForAccount = transactionService.getTransactionsForAccount(1002L);
        assert transactionsForAccount.isPresent();
        assertEquals(2,transactionsForAccount.get().size());
    }
}