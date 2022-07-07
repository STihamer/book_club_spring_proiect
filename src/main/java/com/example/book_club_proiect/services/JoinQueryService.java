package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.*;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.RentingTableRepository;
import com.example.book_club_proiect.repositories.UserRepository;
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

    public List<BooksNonRentedResponse> getNonRentedBooks() {
        List<BooksNonRentedResponse> list = rentingTableRepository.getNonRentedBooks();
        list.forEach(l -> System.out.println(l));
        return list;
    }

    public List<GetMyBooksAndRentingPersonAndReturningDay> getMyBooksRenterReturnDateAndTitle() {
        List<GetMyBooksAndRentingPersonAndReturningDay> list = userRepository.getMyBooksRenterReturnDateAndTitle();
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


}
