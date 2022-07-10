package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.*;
import com.example.book_club_proiect.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinQueryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentingTableRepository rentingTableRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private MyListingRepository myListingRepository;

    public List<BooksNonRentedResponse> getNonRentedBooks() {
        List<BooksNonRentedResponse> list = rentingTableRepository.getNonRentedBooks();
        list.forEach(l -> System.out.println(l));
        return list;
    }

    public List<GetMyBooksAndRentingPersonAndReturningDay>
    getMyBooksRenterReturnDateAndTitle(String first_name, String last_name) {
        List<GetMyBooksAndRentingPersonAndReturningDay> list =
                userRepository.getMyBooksRenterReturnDateAndTitle(first_name, last_name);
        list.forEach(l -> System.out.println(l));
        return list;
    }

    public List<FindBookByTitleInRentingTable> findMyBokByGivenTitle(String str) {
        List<FindBookByTitleInRentingTable> list = bookRepository.findMyBokByGivenTitle(str);
        list.forEach(l -> System.out.println(l));
        return list;
    }

    public List<FindBookByTitleOrAuthorIfAvailable> findAllBookByAuthorNameOrBookTitle(String book_title,
                                                                                       String author_fname,
                                                                                       String author_lname) {
        List<FindBookByTitleOrAuthorIfAvailable> list =
                rentingTableRepository.findAllBookByAuthorNameOrBookTitle(book_title, author_fname, author_lname);
        list.forEach(l -> System.out.println(l));
        return list;
    }

    public FindUserByFirstNameAndUserEmail findUsersByFirst_nameAndUser_email(String first_name,
                                                                              String user_email) {
        List<FindUserByFirstNameAndUserEmail> list =
                userRepository.findUsersByFirst_nameAndUser_email(first_name, user_email);

        return list.get(0);
    }

    public List<GetUsersFromWaitingListWithBookTitle>
    getUsersFromWaitingListWithBookTitle(String first_name, String last_name) {
        List<GetUsersFromWaitingListWithBookTitle> list =
                waitingListRepository.getUsersFromWaitingListWithBookTitle(first_name, last_name);
        return list;
    }

    public List<GetNameAndBookTitleFromMyListing>
    getNameAndBookTitleFromMyListing(String first_name, String last_name) {
        List<GetNameAndBookTitleFromMyListing> list =
                myListingRepository.getNameAndBookTitleFromMyListing(first_name, last_name);
        return list;
    }

}
