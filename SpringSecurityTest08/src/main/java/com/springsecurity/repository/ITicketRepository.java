package com.springsecurity.repository;

import com.springsecurity.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM ticket WHERE user_id=?")
    Ticket findByUser(Long userId);
}
