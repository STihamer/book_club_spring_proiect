package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.services.WaitingListService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("waiting_list")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaitingListController {
    @Autowired
    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }
}
