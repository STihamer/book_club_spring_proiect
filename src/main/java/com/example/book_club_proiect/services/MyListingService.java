package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.MyListingRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void deleteFromMyListing(String book_title, String first_name, String last_name) {
        List<MyListing> operatedList = new ArrayList<>();
        myListingRepository.findAll().stream()
                .filter(e -> e.getBooks().getBook_title().equals(book_title))
                .filter(e -> e.getUsers().getFirst_name().equals(first_name))
                .filter(e -> e.getUsers().getLast_name().equals(last_name))
                .forEach(e -> operatedList.add(e));
        myListingRepository.deleteAll(operatedList);

    }

    public void deleteMyListingById(Long id) {
        myListingRepository.deleteById(id);
    }

}
