package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.MyListingDTO;
import com.example.book_club_proiect.mapper.MyListingMapper;
import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.MyListingRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyListingService {

    private final MyListingMapper myListingMapper;
    private final MyListingRepository myListingRepository;


    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    public MyListingService(MyListingMapper myListingMapper, MyListingRepository myListingRepository, UserRepository userRepository,
                            BookRepository bookRepository) {
        this.myListingMapper = myListingMapper;
        this.myListingRepository = myListingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<MyListingDTO> getAll() {
        return myListingRepository.findAll().stream().map(myListingMapper::toDto).collect(Collectors.toList());
    }

    public Optional<MyListingDTO> getById(Long id) {
        MyListing myListing = myListingRepository.findById(id).get();
        return Optional.of(myListingMapper.toDto(myListing));
    }

    public MyListing createMyListing(Long reading_person, Long book_title) throws UnsupportedOperationException {

        List<MyListing> myListings =
                myListingRepository.findMyListingsByReadingPersonAndBookTitle(reading_person, book_title);
        if (myListings.size() > 0) {
            throw new UnsupportedOperationException(
                    "You have already introduced this book with this user in your listing. Please choose another " +
                            "title or user"
            );
        }
        MyListing myListing = new MyListing();
        myListing.setReadingPerson(reading_person);
        myListing.setBookTitle(book_title);
        myListing.setUsers(userRepository.findById(reading_person).get());
        myListing.setBooks(bookRepository.findById(book_title).get());
        return myListingRepository.saveAndFlush(myListing);
    }

    public void deleteFromMyListing(String book_title, String first_name, String last_name) {
        List<MyListing> operatedList = new ArrayList<>();
        myListingRepository.findAll().stream()
                .filter(e -> e.getBooks().getBookTitle().equals(book_title))
                .filter(e -> e.getUsers().getFirstName().equals(first_name))
                .filter(e -> e.getUsers().getLastName().equals(last_name))
                .forEach(e -> operatedList.add(e));
        myListingRepository.deleteAll(operatedList);
    }

    public void deleteMyListingById(Long id) {
        myListingRepository.deleteById(id);
    }

}
