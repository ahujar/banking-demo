package com.assignment.accountservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class Transaction {

    private Long accountNumber;
    private String accountName;
    private LocalDateTime valueDate;
    private String currencyCode;
    private BigDecimal transactionAmount;
    private String transactionType;
    private String transactionNarrative;

}