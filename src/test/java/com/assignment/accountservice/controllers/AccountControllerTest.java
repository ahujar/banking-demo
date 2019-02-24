package com.assignment.accountservice.controllers;

import com.assignment.accountservice.AccountServiceApplication;
import com.assignment.accountservice.model.dto.Account;
import com.assignment.accountservice.model.entity.enums.AccountType;
import com.assignment.accountservice.services.AccountService;
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
import java.time.LocalDateTime;
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
public class AccountControllerTest {

    private MockMvc mvc;

    @MockBean
    AccountService accountService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAccountDetailsForCustomerId() throws Exception{

        Set<Account> accounts = new HashSet<>();
        accounts.add(Account.builder()
                .accountNumber(1001L)
                .accountType(AccountType.SAVINGS.name())
                .accountName("PERSONAL ACCOUNT")
                .customerNumber(1234567L)
                .availableBalance(new BigDecimal(1000))
                .balanceDate(LocalDateTime.now())
                .build());
        accounts.add(Account.builder()
                .accountNumber(1002L)
                .accountType(AccountType.SAVINGS.name())
                .accountName("PERSONAL ACCOUNT")
                .customerNumber(1234567L)
                .availableBalance(new BigDecimal(3000))
                .balanceDate(LocalDateTime.now())
                .build());
        Optional<Set<Account>> optionalAccountSet = Optional.of(accounts);

        when(accountService.getAccountsByCustomerId(anyLong())).thenReturn(optionalAccountSet);
        mvc.perform(MockMvcRequestBuilders
                .get("/accounts/{customerId}",1234567))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].customerNumber").value("1234567"));
    }
}