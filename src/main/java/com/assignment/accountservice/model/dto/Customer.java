package com.assignment.accountservice.model.dto;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Customer {

    private Long customerId;
    private String firstName;
    private String lastName;
}
