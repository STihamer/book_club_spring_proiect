package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.models.WaitingList;
import com.example.book_club_proiect.services.WaitingListService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waiting_list")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaitingListController {
    @Autowired
    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingList> getAll() {
        return waitingListService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return waitingListService.getById(id).isPresent() ? waitingListService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public WaitingList createMyWaitingList(
            @RequestParam Long user_id,
            @RequestParam Long book_for_reading) {
        return waitingListService.createMyWaitingList(user_id, book_for_reading);
    }
}
