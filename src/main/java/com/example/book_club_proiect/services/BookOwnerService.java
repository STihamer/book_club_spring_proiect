package com.example.book_club_proiect.services;


import com.example.book_club_proiect.dto.BookOwnerDTO;
import com.example.book_club_proiect.mapper.BookOwnerMapper;
import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.models.WaitingList;
import com.example.book_club_proiect.repositories.BookOwnerRepository;
import com.example.book_club_proiect.repositories.MyListingRepository;
import com.example.book_club_proiect.repositories.WaitingListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookOwnerService {

    private final BookOwnerRepository bookOwnerRepository;
    private final WaitingListRepository waitingListRepository;
    private final BookOwnerMapper bookOwnerMapper;

    public BookOwnerService(BookOwnerRepository bookOwnerRepository, MyListingRepository myListingRepository,
                            WaitingListRepository waitingListRepository, BookOwnerMapper bookOwnerMapper) {
        this.bookOwnerRepository = bookOwnerRepository;
        this.waitingListRepository = waitingListRepository;
        this.bookOwnerMapper = bookOwnerMapper;
    }

    public List<BookOwnerDTO> getAll() {

        return bookOwnerRepository.findAll().stream().map(bookOwnerMapper::toDto).collect(Collectors.toList());
    }

    public Optional<BookOwnerDTO> getById(Long id) {
        BookOwner bookOwner = bookOwnerRepository.findById(id).get();
        BookOwnerDTO bookOwnerDTO = bookOwnerMapper.toDto(bookOwner);
        return Optional.of(bookOwnerDTO);
    }


    // camelCase instead of underscore
    public BookOwnerDTO createBookOwner(BookOwnerDTO createDTO) throws UnsupportedOperationException {
        List<BookOwner> bookOwners =
                bookOwnerRepository.findBookOwnersByBookIdAndUserId(createDTO.getBookId(), createDTO.getUserId());

        if (bookOwners.size() > 0) {
            throw new UnsupportedOperationException(
                    "Book owner data with this owner and book title already exists. Please choose other values");
        }
        BookOwner bookOwnerToSave = bookOwnerMapper.toEntity(createDTO);
        BookOwner savedBookOwner = bookOwnerRepository.save(bookOwnerToSave);
        return bookOwnerMapper.toDto(savedBookOwner);
    }

    public BookOwner deleteById(Long id) throws UnsupportedOperationException {
        List<WaitingList> waitingLists = waitingListRepository.findWaitingListsByBookForReading(id);
        BookOwner bookOwner = bookOwnerRepository.findById(id).get();
        if (waitingLists.size() > 0) {
            throw new UnsupportedOperationException("Sorry, you can not delete the chosen book owner. It is used in " +
                    "other tables.");
        }
        bookOwnerRepository.deleteById(id);
        return bookOwner;
    }

    public BookOwner updateBookOwner(Long id, BookOwner bookOwner) {
        BookOwner existingSession = bookOwnerRepository.findById(id).get();
        BeanUtils.copyProperties(bookOwner, existingSession, "id");
        return bookOwnerRepository.saveAndFlush(existingSession);
    }

}
