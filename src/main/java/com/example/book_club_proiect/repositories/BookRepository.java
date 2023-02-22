package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindBookByTitleInRentingTable;
import com.example.book_club_proiect.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleInRentingTable" +
            "(d.bookTitle, c.returnDate, e.firstName) FROM books d join d.rentingTableList c " +
            "join c.users e " +
            "where d" +
            ".bookTitle = " +
            ":book_title")
    List<FindBookByTitleInRentingTable> findMyBokByGivenTitle(@Param("book_title") String str);

    List<Book> findBooksByBookTitle(String title);

    List<Book> findBooksByBookTitleContainingIgnoreCase(String title);

    List<Book> findBooksByAuthorLnameContainingIgnoreCase(String firstName);

    List<Book> findBooksByAuthorFnameContainingIgnoreCase(String lastName);

}