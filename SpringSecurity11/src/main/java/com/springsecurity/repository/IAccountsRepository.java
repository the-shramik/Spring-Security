package com.springsecurity.repository;

import com.springsecurity.model.Accounts;
import org.hibernate.query.JpaTuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts,Long> {

    Accounts findByCustomerId(Long customerId);
}
