package com.assignment.accountservice.model.entity;

import com.assignment.accountservice.model.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "TRANSACTIONS")
public class Transactions implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3057275461420965784L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    @NotNull
    @JsonBackReference
    private Accounts account;

    @Column(name="from_account_id")
    @NotNull
    private long fromAccountId;

    @Column(name = "transaction_amount", precision = 14, scale = 2, nullable=false)
    @NotNull
    private BigDecimal transactionAmount;

    @Column(name = "transaction_date")
    @NotNull
    private LocalDateTime transactionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_narrative", length = 250)
    @NotNull
    private String transactionNarrative;
}
