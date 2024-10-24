package com.springsecurity.service;

import com.springsecurity.model.Book;
import com.springsecurity.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {

    private final IBookRepository bookRepository;

    public String addBook(Book book) {
        bookRepository.save(book);

        return "Book added successfully..!";
    }

    public String deleteBook(Long bookId) {

        bookRepository.deleteById(bookId);

        if (bookRepository.findById(bookId).isEmpty()) {
            return "Book deleted successfully..!";
        } else {
            return "Book not deleted..!";
        }
    }

    public String updateBook(Book book) {

        Book existedBook = bookRepository.findById(book.getBookId()).get();

        existedBook.setBookName(book.getBookName());
        existedBook.setAuthor(book.getAuthor());
        existedBook.setPublisher(book.getPublisher());
        existedBook.setPrice(book.getPrice());

        bookRepository.save(existedBook);

        return "Book updated successfully..!";
    }

    public List<Book> viewBooks() {
       return bookRepository.findAll();
    }

    public String issueBook(Long bookId) {

       Book book=bookRepository.findById(bookId).get();

       return book.getBookName()+" issued successfully..!";
    }

    public String returnBook(Long bookId) {

        Book book=bookRepository.findById(bookId).get();

        return book.getBookName()+" returned successfully..!";
    }

    public String myBooks() {

        return "Here are your issued books!";
    }

}
