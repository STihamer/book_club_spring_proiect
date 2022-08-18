package com.example.book_club_proiect.controllers;


import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.services.BookService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BooksController {

    @Autowired
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return bookService.getById(id).isPresent() ? bookService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteBookById(@PathVariable Long id) {bookService.deleteBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody final Book book) {
        return bookService.createBook(book);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }


}
