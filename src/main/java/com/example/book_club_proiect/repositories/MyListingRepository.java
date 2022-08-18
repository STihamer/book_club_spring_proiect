package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.GetNameAndBookTitleFromMyListing;
import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.models.RentingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MyListingRepository extends JpaRepository<MyListing, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.GetNameAndBookTitleFromMyListing" +
            "(u.first_name, u.last_name, b.book_title) FROM books b join b.myListingList l " +
            "join l.users u where u.first_name = :first_name and u.last_name = :last_name ")
    List<GetNameAndBookTitleFromMyListing> getNameAndBookTitleFromMyListing(@Param("first_name") String first_name,
                                                                            @Param("last_name") String last_name);

}
