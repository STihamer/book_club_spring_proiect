package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.services.BookOwnerService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bookOwners")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BookOwnerController {

    @Autowired
    private final BookOwnerService bookOwnerService;


    public BookOwnerController(BookOwnerService bookOwnerService) {
        this.bookOwnerService = bookOwnerService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<BookOwner> getAll() {
        return bookOwnerService.getAll();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return bookOwnerService.getById(id).isPresent() ? bookOwnerService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        bookOwnerService.deleteById(id);
    }

    @PostMapping
    public BookOwner createBook(@RequestBody final BookOwner book_owner) {
        return bookOwnerService.createBook_Owner(book_owner);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public BookOwner updateBookOwner(@PathVariable Long id, @RequestBody BookOwner bookOwner) {
        return bookOwnerService.updateBookOwner(id, bookOwner);
    }

}
