package com.example.book_club_proiect.controllers;


import com.example.book_club_proiect.dto.RentingTableDTO;
import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.services.RentingTableService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rentingTables")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentingTableController {

    @Autowired
    private final RentingTableService rentingTableService;


    public RentingTableController(RentingTableService rentingTableService) {
        this.rentingTableService = rentingTableService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RentingTableDTO>> getAll() {
        return ResponseEntity.ok(rentingTableService.getAll());
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return rentingTableService.getById(id).isPresent() ? rentingTableService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity createMyRentingTable(
            @RequestParam Long borrowed_by,
            @RequestParam Long book_id,
            @RequestParam Long renting_period) {
        try {
            return ResponseEntity.ok(rentingTableService.createMyRentingTable(borrowed_by, book_id, renting_period));
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RentingTable findRentingTableByIdAndChangeRenting_period
            (@PathVariable Long id,
             @RequestParam(value = "period", required = false) Long period) {
        return rentingTableService.findRentingTableByIdAndChangeRenting_period(id, period);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteRentingTableById(@PathVariable Long id) {
        rentingTableService.deleteRentingTableById(id);
    }


    @GetMapping("/findBooksByTitleOrAuthorName")
    public ResponseEntity<List<RentingTableDTO>> findBooksByTitleOrAuthorName(
            @RequestParam(value = "book_title", required = false) String bookTitle,
            @RequestParam(value = "author_fname", required = false) String authorFirstName,
            @RequestParam(value = "author_lname", required = false) String authorLastName
    ) {
        return ResponseEntity.ok(rentingTableService.findRentingTablesByBookTitleOrAuthorName(bookTitle,
                authorFirstName, authorLastName));
    }
}
