package com.assignment.accountservice.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode(exclude = "accountsList")
@Entity
@Table(name = "CUSTOMERS")
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "first_name", length = 250)
    @NotNull
    private String firstName;

    @Column(name = "last_name", length = 250)
    @NotNull
    private String lastName;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Accounts> accountsList;

    public Customers() {
        this.accountsList = new HashSet<>();
    }
}
