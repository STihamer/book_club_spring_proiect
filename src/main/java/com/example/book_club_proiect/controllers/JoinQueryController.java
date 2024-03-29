package com.example.book_club_proiect.controllers;

import com.example.book_club_proiect.dto.*;
import com.example.book_club_proiect.services.JoinQueryService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class JoinQueryController {
    @Autowired
    private JoinQueryService joinQueryService;


    @GetMapping("api/booksNonRented")
    public ResponseEntity<List<BooksNonRentedResponse>> getNonRentedBooks() {
        return new ResponseEntity<List<BooksNonRentedResponse>>(joinQueryService.getNonRentedBooks(), HttpStatus.OK);
    }

    @GetMapping("api/userTitleAndReturnDate")
    public ResponseEntity<List<GetMyBooksAndRentingPersonAndReturningDay>>
    getMyBooksRenterReturnDateAndTitle(
            @RequestParam(value = "first_name", required = false) String first_name,
            @RequestParam(value = "last_name", required = false) String last_name
    ) {
        return new ResponseEntity<List<GetMyBooksAndRentingPersonAndReturningDay>>
                (joinQueryService.getMyBooksRenterReturnDateAndTitle
                        (first_name, last_name), HttpStatus.OK);
    }

    @RequestMapping(value = "api/oneBookByTitle", method = RequestMethod.GET)
    public ResponseEntity<List<FindBookByTitleInRentingTable>> findMyBokByGivenTitle(@RequestParam(value =
            "book_title",
            required = false) String book_title) {
        return new ResponseEntity<List<FindBookByTitleInRentingTable>>(joinQueryService.findMyBokByGivenTitle(book_title),
                HttpStatus.OK);
    }

    @RequestMapping(value = "api/bookAvailabilityByAuthorOrTitle", method = RequestMethod.GET)
    public ResponseEntity<List<FindBookByTitleOrAuthorIfAvailable>> findAllBookByAuthorNameOrBookTitle
            (@RequestParam(value = "book_title", required = false) String book_title,
             @RequestParam(value = "author_fname", required = false) String author_fname,
             @RequestParam(value = "author_lname", required = false) String author_lname) {
        return new ResponseEntity<List<FindBookByTitleOrAuthorIfAvailable>>
                (joinQueryService.findAllBookByAuthorNameOrBookTitle
                        (book_title, author_fname, author_lname),
                        HttpStatus.OK);
    }

    @RequestMapping(value = "api/firstNameAndEmail", method = RequestMethod.GET)
    public ResponseEntity<FindUserByFirstNameAndUserEmail> findUserByFirstNameAndUser_email
            (@RequestParam(value = "first_name", required =
                    false) String first_name,
             @RequestParam(value = "user_email", required = false) String user_email) {
        return new ResponseEntity<FindUserByFirstNameAndUserEmail>
                (joinQueryService.findUsersByFirst_nameAndUser_email(first_name, user_email),
                        HttpStatus.OK);
    }

    @RequestMapping(value = "api/waitingPersonsAndBookTitle", method = RequestMethod.GET)
    public ResponseEntity<List<GetUsersFromWaitingListWithBookTitle>> getUsersFromWaitingListWithBookTitle(
            @RequestParam(value = "first_name",
                    required = false) String first_name,
            @RequestParam(value = "last_name",
                    required = false) String last_name)
           {
        return new ResponseEntity<List<GetUsersFromWaitingListWithBookTitle>>
                (joinQueryService.getUsersFromWaitingListWithBookTitle(first_name, last_name),
                        HttpStatus.OK);
    }
    @RequestMapping(value = "api/myListingsByUser", method = RequestMethod.GET)
    public ResponseEntity<List<GetNameAndBookTitleFromMyListing>> getNameAndBookTitleFromMyListing(
            @RequestParam(value = "first_name",
                    required = false) String first_name,
            @RequestParam(value = "last_name",
                    required = false) String last_name)
    {
        return new ResponseEntity<List<GetNameAndBookTitleFromMyListing>>
                (joinQueryService.getNameAndBookTitleFromMyListing(first_name, last_name),
                        HttpStatus.OK);
    }


}
