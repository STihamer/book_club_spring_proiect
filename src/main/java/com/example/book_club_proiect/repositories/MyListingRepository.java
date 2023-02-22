package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.GetNameAndBookTitleFromMyListing;
import com.example.book_club_proiect.models.MyListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyListingRepository extends JpaRepository<MyListing, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.GetNameAndBookTitleFromMyListing" +
            "(u.firstName, u.lastName, b.bookTitle) FROM books b join b.myListingList l " +
            "join l.users u where u.firstName = :first_name and u.lastName = :last_name ")
    List<GetNameAndBookTitleFromMyListing> getNameAndBookTitleFromMyListing(@Param("first_name") String first_name,
                                                                            @Param("last_name") String last_name);


    List<MyListing> findAllByReadingPerson(Long id);
    List<MyListing> findMyListingsByReadingPersonAndBookTitle(Long book, Long user);

    List<MyListing> findMyListingsByBookTitle(Long id);
}
