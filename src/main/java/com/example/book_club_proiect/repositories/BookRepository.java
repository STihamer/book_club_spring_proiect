package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindBookByTitleInRentingTable;
import com.example.book_club_proiect.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleInRentingTable" +
            "(d.book_title, c.return_date, e.first_name) FROM books d join d.rentingTableList c " +
            "join c.users e " +
            "where d" +
            ".book_title = " +
            ":book_title")
    List<FindBookByTitleInRentingTable> findMyBokByGivenTitle(@Param("book_title") String str);

    @Query("select b from books b where b.book_title =:book_title " +
            " and (b.author_fname = :author_fname or b.author_lname = :author_lname) or (b.author_lname = " +
            ":author_lname or b.author_fname = :author_fname) or b.book_title =:book_title ")
    List<Book> findBooksByTitleOrAuthor(
            @Param("book_title") String title,
            @Param("author_fname") String author_fname,
            @Param("author_lname") String author_lname
    );

}