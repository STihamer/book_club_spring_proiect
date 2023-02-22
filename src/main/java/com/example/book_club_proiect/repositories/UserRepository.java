package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindUserByFirstNameAndUserEmail;
import com.example.book_club_proiect.dto.GetMyBooksAndRentingPersonAndReturningDay;
import com.example.book_club_proiect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select new com.example.book_club_proiect.dto.FindUserByFirstNameAndUserEmail(u" +
            ".firstName, u" +
            ".lastName, u.userAge, u.username, u.userEmail) from" +
            " users u where" +
            " u.firstName = :first_name and " +
            "u.userEmail = :user_email")
    List<FindUserByFirstNameAndUserEmail> findUsersByFirst_nameAndUser_email(@Param("first_name") String first_name,
                                                                             @Param("user_email") String email);


    @Query(value = "SELECT new com.example.book_club_proiect.dto.GetMyBooksAndRentingPersonAndReturningDay" +
            "(d.firstName, d.lastName, c.bookTitle, e.returnDate  ) "
            + "FROM users d  JOIN d.rentingTableList e join e.books c where d.firstName = :first_name and d" +
            ".lastName" +
            " = :last_name")
    List<GetMyBooksAndRentingPersonAndReturningDay> getMyBooksRenterReturnDateAndTitle(
            @Param("first_name") String first_name,
            @Param("last_name") String last_name);

    @Query("select u from users u where u.username = ?1")
    User findUserByUsername(String username);


    @Query("select u from users u where u.username = ?1")
    List<User> findUsersByUsername(String title);


}
