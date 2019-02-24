package com.assignment.accountservice.controllers;

import com.assignment.accountservice.AccountServiceApplication;
import com.assignment.accountservice.model.dto.Transaction;
import com.assignment.accountservice.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ AccountServiceApplication.class })
public class TransactionsControllerTest {

    private MockMvc mvc;

    @MockBean
    TransactionService transactionService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getTransactionsforAccountId() throws Exception {

        Set<Transaction> transactions = new HashSet<>();
        transactions.add(Transaction.builder()
                .accountName("PERSONAL ACCOUNT")
                .accountNumber(1234567L)
                .currencyCode("AUD")
                .transactionAmount(new BigDecimal(100))
                .transactionType("DEBIT")
                .build());
        transactions.add(Transaction.builder()
                .accountName("PERSONAL ACCOUNT")
                .accountNumber(1234567L)
                .currencyCode("AUD")
                .transactionAmount(new BigDecimal(10))
                .transactionType("CREDIT")
                .build());
        Optional<Set<Transaction>> optionalTransactionSet = Optional.of(transactions);
        when(transactionService.getTransactionsForAccount(anyLong())).thenReturn(optionalTransactionSet);
        mvc.perform(MockMvcRequestBuilders
                .get("/transactions/{accountId}",1234567))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].accountNumber").value("1234567"));
    }
}