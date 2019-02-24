package com.assignment.accountservice.repositories;

import com.assignment.accountservice.model.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {

    Customers findByCustomerId(Long customerId);
}
