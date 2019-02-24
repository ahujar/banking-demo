package com.assignment.accountservice.services;

import com.assignment.accountservice.model.dto.Customer;
import com.assignment.accountservice.model.entity.Customers;
import com.assignment.accountservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerDetailsById(Long customerId) {
        Customers customer = customerRepository.findByCustomerId(customerId);
        if (customer != null) {
            return Optional.of(Customer.builder()
                    .customerId(customer.getCustomerId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .build());
        } else {
            return Optional.empty();
        }
    }

}
