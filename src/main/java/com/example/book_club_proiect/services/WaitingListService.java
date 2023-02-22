package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.WaitingListDTO;
import com.example.book_club_proiect.mapper.WaitingListMapper;
import com.example.book_club_proiect.models.WaitingList;
import com.example.book_club_proiect.repositories.BookOwnerRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import com.example.book_club_proiect.repositories.WaitingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaitingListService {

    private final WaitingListRepository waitingListRepository;

    private final UserRepository userRepository;

    private final BookOwnerRepository bookOwnerRepository;

    private final WaitingListMapper waitingListMapper;

    public WaitingListService(WaitingListRepository waitingListRepository, UserRepository userRepository,
                              BookOwnerRepository bookOwnerRepository, WaitingListMapper waitingListMapper) {
        this.waitingListRepository = waitingListRepository;
        this.userRepository = userRepository;
        this.bookOwnerRepository = bookOwnerRepository;
        this.waitingListMapper = waitingListMapper;
    }

    public List<WaitingListDTO> getAll() {

        return waitingListRepository.findAll().stream().map(waitingListMapper::toDto).collect(Collectors.toList());
    }

    public Optional<WaitingListDTO> getById(Long id) {
        WaitingList waitingList = waitingListRepository.findById(id).get();
        return Optional.of(waitingListMapper.toDto(waitingList));
    }

    public WaitingList createMyWaitingList(Long user_id, Long book_for_reading, Boolean finished) throws UnsupportedOperationException {
        List<WaitingList> waitingLists = waitingListRepository.findWaitingListsByUserIdAndBookForReading(user_id,
                book_for_reading);
        if (waitingLists.size() > 0) {
            throw new UnsupportedOperationException(
                    "You have already the given value in waiting list. Please choose another book");
        }
        WaitingList waitingList = new WaitingList();
        waitingList.setUserId(user_id);
        waitingList.setFinished(finished);
        waitingList.setBookForReading(book_for_reading);
        waitingList.setUsers(userRepository.findById(user_id).get());
        waitingList.setBookOwner(bookOwnerRepository.findById(book_for_reading).get());

        return waitingListRepository.saveAndFlush(waitingList);
    }

    public WaitingList updateWaitingList(Long id, Boolean finished) {
        WaitingList existingSession = waitingListRepository.findById(id).get();
        existingSession.setFinished(finished);
        return waitingListRepository.saveAndFlush(existingSession);
    }

    public void deleteById(Long id) {
        waitingListRepository.deleteById(id);
    }

}
