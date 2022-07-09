package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.FindUserByFirstNameAndUserEmail;
import com.example.book_club_proiect.dto.GetMyBooksAndRentingPersonAndReturningDay;
import com.example.book_club_proiect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

   @Query(value = "select new com.example.book_club_proiect.dto.FindUserByFirstNameAndUserEmail(u.first_name, u" +
           ".last_name, u.user_age, u.username, u.user_email) from" +
           " users u where" +
           " u.first_name = :first_name and " +
           "u.user_email = :user_email")
   List<FindUserByFirstNameAndUserEmail> findUsersByFirst_nameAndUser_email(@Param("first_name") String first_name,
                                                                            @Param("user_email") String email);


   @Query(value = "SELECT new com.example.book_club_proiect.dto.GetMyBooksAndRentingPersonAndReturningDay" +
           "(d.first_name, d.last_name, c.book_title, e.return_date ) "
           + "FROM users d  JOIN d.rentingTableList e join e.books c where d.first_name = :first_name and d.last_name" +
           " = :last_name")
   List<GetMyBooksAndRentingPersonAndReturningDay> getMyBooksRenterReturnDateAndTitle(
           @Param("first_name") String first_name,
           @Param("last_name") String last_name);

}
