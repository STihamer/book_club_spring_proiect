package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindBookByTitleInRentingTable;
import com.example.book_club_proiect.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleInRentingTable" +
            "(d.book_title, c.return_date, e.first_name) FROM books d join d.rentingTableList c " +
            "join c.users e " +
            "where d" +
            ".book_title = " +
            ":book_title")
    List<FindBookByTitleInRentingTable> findMyBokByGivenTitle(@Param("book_title") String str);
}