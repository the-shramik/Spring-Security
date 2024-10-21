package com.springsecurity.service;

import com.springsecurity.exception.TicketNotFoundException;
import com.springsecurity.model.Ticket;
import com.springsecurity.repository.ITicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl {

    private final ITicketRepository ticketRepository;

    public Ticket generateTicket(@RequestBody Ticket ticket){
      return ticketRepository.save(ticket);
    }

    public Ticket viewTicketByTickerNo(@PathVariable Long ticketNumber){
     return ticketRepository.findById(ticketNumber).get();
    }

    public String deleteTicket(@PathVariable Long ticketNumber){
        ticketRepository.deleteById(ticketNumber);

        if(ticketRepository.findById(ticketNumber).isEmpty()){
            return "Ticket deleted successfully..!";
        }
        return "Ticket not deleted..!";
    }


    public Ticket updateTicket(Ticket ticket) throws TicketNotFoundException {

        Ticket existedTicket=ticketRepository.findById(ticket.getTicketNumber()).orElseThrow(
                ()->new TicketNotFoundException("Ticket not present"));

        existedTicket.setDestination(ticket.getDestination());
        existedTicket.setPrice(ticket.getPrice());
        existedTicket.setUser(ticket.getUser());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }


    public Ticket viewTicketByUser(@PathVariable Long userId){
       return ticketRepository.findByUser(userId);
    }

    public String contact(){
      return "Here are the contact info for inquiry..!";
    }

    public String service(){
     return "Here are our services..!";
    }
}
