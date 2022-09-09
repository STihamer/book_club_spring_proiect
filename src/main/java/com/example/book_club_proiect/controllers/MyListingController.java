package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.dto.MyListingDTO;
import com.example.book_club_proiect.services.BookService;
import com.example.book_club_proiect.services.MyListingService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/myListings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MyListingController {


    private final MyListingService myListingService;

    public MyListingController(MyListingService myListingService) {
        this.myListingService = myListingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MyListingDTO>> getAll() {

        return ResponseEntity.ok(myListingService.getAll());
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return myListingService.getById(id).isPresent() ? myListingService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity createMyListing(@RequestParam Long reading_person, @RequestParam Long book_title) {
        try {
            return ResponseEntity.ok(myListingService.createMyListing(reading_person, book_title));
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @RequestMapping(value = "/api/deleteMyListing", method = RequestMethod.DELETE)
    public void deleteFromMyListing(
            @RequestParam String book_title,
            @RequestParam String first_name,
            @RequestParam String last_name) {
        myListingService.deleteFromMyListing(book_title, first_name, last_name);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMyListingById(@PathVariable Long id) {
        myListingService.deleteMyListingById(id);
    }


}
