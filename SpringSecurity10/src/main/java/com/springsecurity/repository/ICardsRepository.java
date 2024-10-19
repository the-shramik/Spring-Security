package com.springsecurity.repository;

import com.springsecurity.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardsRepository extends JpaRepository<Cards,Long> {
    List<Cards> findByCustomerId(Long customerId);
}
