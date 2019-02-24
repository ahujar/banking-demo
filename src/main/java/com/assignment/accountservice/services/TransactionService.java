package com.assignment.accountservice.services;

import com.assignment.accountservice.model.dto.Transaction;
import com.assignment.accountservice.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class TransactionService {

    private final TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Optional<Set<Transaction>> getTransactionsForAccount(Long accountId) {

        Optional<Set<Transaction>> transaction = Optional.empty();

        Set<Transaction> transactionSet = transactionsRepository.findByAccount_accountId(accountId).stream().map(transactions -> Transaction.builder()
                .accountName(transactions.getAccount().getAccountName())
                .accountNumber(transactions.getAccount().getAccountId())
                .currencyCode(transactions.getAccount().getCurrencyType().name())
                .valueDate(transactions.getTransactionDate())
                .transactionAmount(transactions.getTransactionAmount())
                .transactionType(transactions.getTransactionType().name())
                .transactionNarrative(transactions.getTransactionNarrative())
                .build()).collect(toSet());

        if (!CollectionUtils.isEmpty(transactionSet)) {
            transaction = Optional.of(transactionSet);
        }
        return transaction;

    }

}
