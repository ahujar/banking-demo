package com.assignment.accountservice.repositories;

import com.assignment.accountservice.model.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {

    Set<Transactions> findByAccount_accountId(Long accoutId);
}
