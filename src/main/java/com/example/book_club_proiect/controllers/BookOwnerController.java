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
@RequestMapping("book_owner")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BookOwnerController {

    @Autowired
    private final BookOwnerService book_ownerService;


    public BookOwnerController(BookOwnerService book_ownerService) {
        this.book_ownerService = book_ownerService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<BookOwner> getAll() {
        return book_ownerService.getAll();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return book_ownerService.getById(id).isPresent() ? book_ownerService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        book_ownerService.deleteById(id);
    }

    @PostMapping
    public BookOwner createBook(@RequestBody final BookOwner book_owner) {
        return book_ownerService.createBook_Owner(book_owner);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public BookOwner updateBookOwner(@PathVariable Long id, @RequestBody BookOwner bookOwner) {
        return book_ownerService.updateBookOwner(id, bookOwner);
    }

}
