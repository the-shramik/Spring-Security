package com.springsecurity.controller;

import com.springsecurity.model.Book;
import com.springsecurity.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        return ResponseEntity.ok(bookService.addBook(book));
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }

    @PatchMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @GetMapping("/viewAllBooks")
    public ResponseEntity<?> viewBooks() {
        return ResponseEntity.ok(bookService.viewBooks());
    }

    @GetMapping("/issueBook/{bookId}")
    public ResponseEntity<?> issueBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.issueBook(bookId));
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody Long bookId) {
        return ResponseEntity.ok(bookService.returnBook(bookId));
    }

    @GetMapping("/myBooks")
    public ResponseEntity<?> myBooks() {
        return ResponseEntity.ok(bookService.myBooks());
    }
}
