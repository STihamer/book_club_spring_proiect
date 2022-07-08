package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.services.MyListingService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("my_listing")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MyListingController {

    @Autowired
    private final MyListingService myListingService;

    public MyListingController(MyListingService myListingService) {
        this.myListingService = myListingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MyListing> getAll() {
        return myListingService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return myListingService.getById(id).isPresent() ? myListingService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public MyListing createMyListing(@RequestParam Long reading_person, @RequestParam Long book_title) {
        return myListingService.createMyListing(reading_person, book_title);
    }

}
