package com.assignment.accountservice.controllers;

import com.assignment.accountservice.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity getAccountDetailsForCustomerId(@PathVariable Long customerId) {

        return accountService.getAccountsByCustomerId(customerId)
                .map(accounts -> ResponseEntity.ok().body(accounts))
                .orElse(new ResponseEntity("CUSTOMER ID: "+customerId+" NOT FOUND ",HttpStatus.NOT_FOUND));
    }
}
