package com.assignment.accountservice.repositories;

import com.assignment.accountservice.model.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Set<Accounts> findByCustomer_customerId(Long customerId);
}
