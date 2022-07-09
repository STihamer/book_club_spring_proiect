package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.WaitingList;
import com.example.book_club_proiect.repositories.BookOwnerRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import com.example.book_club_proiect.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WaitingListService {
    @Autowired
    private final WaitingListRepository waitingListRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookOwnerRepository bookOwnerRepository;
    public WaitingListService(WaitingListRepository waitingListRepository, UserRepository userRepository,
                              BookOwnerRepository bookOwnerRepository) {
        this.waitingListRepository = waitingListRepository;
        this.userRepository = userRepository;
        this.bookOwnerRepository = bookOwnerRepository;
    }

    public List<WaitingList> getAll() {
        return waitingListRepository.findAll();
    }

    public Optional<WaitingList> getById(Long id) {

        return waitingListRepository.findById(id);
    }

    public WaitingList createMyWaitingList(Long user_id, Long book_for_reading) {
        WaitingList waitingList = new WaitingList();
        waitingList.setUser_id(user_id);
        waitingList.setBook_for_reading(book_for_reading);
        waitingList.setUsers(userRepository.findById(user_id).get());
        waitingList.setBookOwner(bookOwnerRepository.findById(book_for_reading).get());

        return waitingListRepository.saveAndFlush(waitingList);
    }


}
