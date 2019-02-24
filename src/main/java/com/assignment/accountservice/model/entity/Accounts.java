package com.assignment.accountservice.model.entity;


import com.assignment.accountservice.model.entity.enums.AccountType;
import com.assignment.accountservice.model.entity.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode(exclude = "transactions")
@Entity
@Table(name = "ACCOUNTS")
public class Accounts implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3057275461420965784L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long accountId;

    @Column(name = "account_name", length = 250)
    @NotNull
    private String accountName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", length = 3)
    private CurrencyType currencyType;

    @Column(name = "account_balance", precision = 14, scale = 2, nullable=false)
    @NotNull
    private BigDecimal accountBalance;

    @Column(name = "balance_date")
    @NotNull
    private LocalDateTime balanceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    @JsonBackReference
    private Customers customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<Transactions> transactions;

    public Accounts() {
        this.accountBalance = new BigDecimal(0.00);
    }
}
