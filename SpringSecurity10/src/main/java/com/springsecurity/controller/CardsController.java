package com.springsecurity.controller;

import com.springsecurity.model.Cards;
import com.springsecurity.repository.ICardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private ICardsRepository cardsRepository;

    @GetMapping("/myCards")
    public ResponseEntity<?> getCardsDetails(@RequestParam long id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);

        if (cards != null) {
            return ResponseEntity.ok(cards);
        } else {
            return null;
        }
    }
}
