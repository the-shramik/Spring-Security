package com.springsecurity.repository;

import com.springsecurity.model.Loans;
import com.springsecurity.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoansRepository extends JpaRepository<Loans,Long> {

     List<Loans> findByCustomerIdOrderByStartDtDesc(Long customerId);

}
