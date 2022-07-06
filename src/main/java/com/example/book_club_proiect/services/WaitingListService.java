package com.example.book_club_proiect.services;

import com.example.book_club_proiect.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitingListService {
    @Autowired
    private final WaitingListRepository waitingListRepository;

    public WaitingListService(WaitingListRepository waitingListRepository) {
        this.waitingListRepository = waitingListRepository;
    }
}
