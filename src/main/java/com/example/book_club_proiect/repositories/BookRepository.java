package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindBookByTitleInRentingTable;
import com.example.book_club_proiect.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleInRentingTable" +
            "(d.book_title, c.return_date, e.first_name) FROM books d join d.rentingTableList c " +
            "join c.users e " +
            "where d" +
            ".book_title = " +
            ":book_title")
    List<FindBookByTitleInRentingTable> findMyBokByGivenTitle(@Param("book_title") String str);

    @Query("select b from books b where b.book_title =:book_title")
    List<Book> findBooksByTitle(
            @Param("book_title") String title
    );

    @Query("select b from books b where b.author_fname =:author_fname")
    List<Book> findBooksByAuthorFirstName(
            @Param("author_fname") String author_fname
    );

    @Query("select b from books b where b.author_lname =:author_lname")
    List<Book> findBooksByAuthorLastName(
            @Param("author_lname") String author_lname
    );

    @Query("select b from books b where b.book_title = ?1")
    Boolean existsByBook_title(String book_title);
}