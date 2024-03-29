package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.dto.WaitingListDTO;
import com.example.book_club_proiect.models.WaitingList;
import com.example.book_club_proiect.services.WaitingListService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/waitingLists")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaitingListController {

    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<WaitingListDTO>> getAll() {
        return ResponseEntity.ok(waitingListService.getAll());
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getById(@PathVariable Long id) {
        return waitingListService.getById(id).isPresent() ? waitingListService.getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity createMyWaitingList(
            @RequestParam Long user_id,
            @RequestParam Boolean finished,
            @RequestParam Long book_for_reading) {
        try {
            return ResponseEntity.ok(waitingListService.createMyWaitingList(user_id, book_for_reading, finished));
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public WaitingList updateWaitingList(@PathVariable Long id, @RequestParam Boolean finished) {
        return waitingListService.updateWaitingList(id, finished);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteWaitingListById(@PathVariable Long id) {
        waitingListService.deleteById(id);
    }

}
