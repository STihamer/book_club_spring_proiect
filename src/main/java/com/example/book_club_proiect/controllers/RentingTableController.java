package com.example.book_club_proiect.controllers;


import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.services.RentingTableService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("renting_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentingTableController {

    @Autowired
    private final RentingTableService rentingTableService;


    public RentingTableController(RentingTableService rentingTableService) {
        this.rentingTableService = rentingTableService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RentingTable> getAll() {
        return rentingTableService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return rentingTableService.getById(id).isPresent() ? rentingTableService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public RentingTable createRentingTable(@RequestBody final RentingTable rentingTable) {
        return rentingTableService.createRentingTable(rentingTable);
    }
}
