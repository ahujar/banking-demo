package com.assignment.accountservice.services;

import com.assignment.accountservice.model.dto.Customer;
import com.assignment.accountservice.model.entity.Customers;
import com.assignment.accountservice.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Test
    public void getCustomerDetailsById() {

        Customers testCustomer = new Customers(12345L, "John", "Doe", null);
        when(customerRepository.findByCustomerId(12345L)).thenReturn(testCustomer);
        Optional<Customer> customerDetailsById = customerService.getCustomerDetailsById(12345L);
        assert customerDetailsById.isPresent();
        assertEquals(testCustomer.getCustomerId() ,customerDetailsById.get().getCustomerId().longValue());
    }
}