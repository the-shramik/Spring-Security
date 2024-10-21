package com.springsecurity.controller;

import com.springsecurity.exception.TicketNotFoundException;
import com.springsecurity.model.Ticket;
import com.springsecurity.service.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketServiceImpl ticketService;


    @GetMapping("/viewTicketByTicketNo/{ticketNumber}")
    public ResponseEntity<?> viewTicketByTickerNo(@PathVariable Long ticketNumber){
     return ResponseEntity.ok(ticketService.viewTicketByTickerNo(ticketNumber));
    }

    @DeleteMapping("/deleteTicket/{ticketNumber}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long ticketNumber){
      return ResponseEntity.ok(ticketService.deleteTicket(ticketNumber));
    }


    @PatchMapping("/updateTicket")
    public ResponseEntity<?> updateTicket(@RequestBody Ticket ticket) throws TicketNotFoundException {
          return ResponseEntity.ok(ticketService.updateTicket(ticket));
    }

    @GetMapping("/getAllTickets")
    public ResponseEntity<?> getAllTickets(){
         return ResponseEntity.ok(ticketService.getAllTickets());
    }


    @PostMapping("/getTicket")
    public ResponseEntity<?> generateTicket(@RequestBody Ticket ticket){
        return ResponseEntity.ok(ticketService.generateTicket(ticket));
    }

    @GetMapping("/viewTicketByUser/{userId}")
    public ResponseEntity<?> viewTicketByUser(@PathVariable Long userId){
        return ResponseEntity.ok(ticketService.viewTicketByUser(userId));
    }

    @GetMapping("/contact")
    public ResponseEntity<?> contact(){
       return ResponseEntity.ok(ticketService.contact());
    }

    @GetMapping("/service")
    public ResponseEntity<?> service(){
       return ResponseEntity.ok(ticketService.service());
    }
}
