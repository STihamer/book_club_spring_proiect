package com.example.book_club_proiect.controllers;


import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.models.RentingPeriods;
import com.example.book_club_proiect.services.RentingPeriodsService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("renting_periods")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentingPeriodsController {


    @Autowired
    private final RentingPeriodsService rentingPeriodsService;

    public RentingPeriodsController(RentingPeriodsService rentingPeriodsService) {
        this.rentingPeriodsService = rentingPeriodsService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<RentingPeriods> getAll() {
        return rentingPeriodsService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return rentingPeriodsService.getById(id).isPresent() ? rentingPeriodsService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteRentingPeriodById(@PathVariable Long id) {
        rentingPeriodsService.deleteRentingPeriod(id);
    }

    @PostMapping
    public RentingPeriods createRentingPeriod(@RequestBody final RentingPeriods rentingPeriods) {
        return rentingPeriodsService.createRentingPeriod(rentingPeriods);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RentingPeriods updateRentingPeriod(@PathVariable Long id, @RequestBody RentingPeriods rentingPeriods) {
        return rentingPeriodsService.updateRentingPeriod(id, rentingPeriods);
    }

}
