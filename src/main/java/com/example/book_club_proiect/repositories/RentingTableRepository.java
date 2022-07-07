package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.BooksNonRentedResponse;
import com.example.book_club_proiect.dto.FindBookByTitleInRentingTable;
import com.example.book_club_proiect.dto.FindBookByTitleOrAuthorIfAvailable;
import com.example.book_club_proiect.models.RentingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface RentingTableRepository extends JpaRepository<RentingTable, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.BooksNonRentedResponse(d.book_title, e.return_date) "
            + "FROM books d Left JOIN d.rentingTableList e where e.return_date is null")
    List<BooksNonRentedResponse> getNonRentedBooks();


    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleOrAuthorIfAvailable" +
            "( d.author_fname, d.author_lname, d.book_title, c.return_date) FROM books d join d" +
            ".rentingTableList c " +
            "where d.book_title = :book_title or d.author_fname = :author_fname or d.author_lname = :author_lname")
    List<FindBookByTitleOrAuthorIfAvailable> findAllBookByAuthorNameOrBookTitle
            (@Param("book_title") String book_title,
             @Param("author_fname") String author_fname,
             @Param("author_lname") String author_lname);
}
