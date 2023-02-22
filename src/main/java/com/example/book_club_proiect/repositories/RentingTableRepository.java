package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.BooksNonRentedResponse;
import com.example.book_club_proiect.dto.FindBookByTitleOrAuthorIfAvailable;
import com.example.book_club_proiect.models.RentingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentingTableRepository extends JpaRepository<RentingTable, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.BooksNonRentedResponse" +
            "(d.bookId, d.bookTitle, d.authorFname,d.authorLname,e.returnDate)"
            + "FROM books d Left JOIN d.rentingTableList e where e.returnDate is null")
    List<BooksNonRentedResponse> getNonRentedBooks();


    @Query(value = "SELECT new com.example.book_club_proiect.dto.FindBookByTitleOrAuthorIfAvailable" +
            "( d.bookId, d.authorFname, d.authorLname, d.bookTitle, c.returnDate) FROM books d join d" +
            ".rentingTableList c " +
            "where d.bookTitle = :book_title or d.authorFname = :author_fname or d.authorLname = :author_lname")
    List<FindBookByTitleOrAuthorIfAvailable> findAllBookByAuthorNameOrBookTitle
            (@Param("book_title") String book_title,
             @Param("author_fname") String author_fname,
             @Param("author_lname") String author_lname);

    @Query(value = "select r from renting_table r where r.id = :renting_table_id")
    RentingTable findRentingTableByIdAndChangeRenting_period(
            @Param("renting_table_id") Long renting_table_id);


    List<RentingTable> findRentingTablesByBorrowedBy(Long id);

    List<RentingTable> findRentingTablesByBookId(Long id);

    List<RentingTable> findRentingTablesByBorrowedByAndBookId(Long borrower, Long bookId);


}
