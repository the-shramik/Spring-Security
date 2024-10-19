package com.springsecurity.repository;

import com.springsecurity.model.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountTransactionsRepository extends JpaRepository<AccountTransactions,Long> {

    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(Long customerId);
}
