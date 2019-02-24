package com.assignment.accountservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder @Getter @Setter
public class Account {

    private Long accountNumber;
    private String accountType;
    private String accountName;
    private LocalDateTime balanceDate;
    private String currencyCode;
    private BigDecimal availableBalance;
    private Long customerNumber;
}
