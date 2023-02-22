package com.example.book_club_proiect.controllers;


import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.services.BookService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return bookService.getById(id).isPresent() ? bookService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)

    public ResponseEntity deleteBookById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.deleteBookById(id));
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody BookDTO createDTO) {
        try {
            return ResponseEntity.ok(bookService.createBook(createDTO));
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        if (id.equals(bookDTO.getBookId())) {
            try {
                return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
            } catch (UnsupportedOperationException exception) {
                return ResponseEntity.badRequest().body(exception.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("The id is not correct");
    }


    @GetMapping("/findBooksByTitleOrAuthorName")
    public ResponseEntity<List<BookDTO>> findBookByTitleOrByAuthorName(
            @RequestParam(value = "searching", required = false) String searching
    ) {
        return ResponseEntity.ok(bookService.findBookByTitleOrByAuthorName(searching));
    }
}
