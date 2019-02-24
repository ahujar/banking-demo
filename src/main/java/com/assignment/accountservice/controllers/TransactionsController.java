package com.assignment.accountservice.controllers;

import com.assignment.accountservice.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("transactions")
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity getTransactionsforAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionsForAccount(accountId)
                .map(transactions -> ResponseEntity.ok().body(transactions))
                .orElse(new ResponseEntity("AccountID: " + accountId + " NOT FOUND", HttpStatus.NOT_FOUND));
    }
}
