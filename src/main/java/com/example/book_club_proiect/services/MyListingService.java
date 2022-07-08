package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.MyListingRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyListingService {
    @Autowired
    private final MyListingRepository myListingRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    public MyListingService(MyListingRepository myListingRepository, UserRepository userRepository,
                            BookRepository bookRepository) {
        this.myListingRepository = myListingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<MyListing> getAll() {
        return myListingRepository.findAll();
    }

    public Optional<MyListing> getById(Long id) {

        return myListingRepository.findById(id);
    }

    public MyListing createMyListing(Long reading_person, Long book_title) {
        MyListing myListing = new MyListing();
        myListing.setReading_person(reading_person);
        myListing.setBook_title(book_title);
        myListing.setUsers(userRepository.findById(reading_person).get());
        myListing.setBooks(bookRepository.findById(book_title).get());
        return myListingRepository.saveAndFlush(myListing);
    }
}
