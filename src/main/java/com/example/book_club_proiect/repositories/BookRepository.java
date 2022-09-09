package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindBookByTitleInRentingTable;
import com.example.book_club_proiect.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleInRentingTable" +
            "(d.bookTitle, c.return_date, e.first_name) FROM books d join d.rentingTableList c " +
            "join c.users e " +
            "where d" +
            ".bookTitle = " +
            ":book_title")
    List<FindBookByTitleInRentingTable> findMyBokByGivenTitle(@Param("book_title") String str);

    @Query("select b from books b where b.bookTitle =:book_title")
    List<Book> findBooksByTitle(
            @Param("book_title") String title
    );

    @Query("select b from books b where b.authorFname =:author_fname")
    List<Book> findBooksByAuthorFirstName(
            @Param("author_fname") String author_fname
    );

    @Query("select b from books b where b.authorLname =:author_lname")
    List<Book> findBooksByAuthorLastName(
            @Param("author_lname") String author_lname
    );

    @Query("select b from books b where b.bookTitle = ?1")
    Boolean existsByBook_title(String book_title);

    @Query("select b from books b where b.bookId = ?1")
    List<Book> findBooksByBook_id(Long id);

}