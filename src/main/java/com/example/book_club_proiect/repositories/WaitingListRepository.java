package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.GetUsersFromWaitingListWithBookTitle;
import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

   @Query(value = "SELECT new com.example.book_club_proiect.dto.GetUsersFromWaitingListWithBookTitle" +
            "(u.first_name , u.last_name, b.book_id, bs.bookTitle ) from  books bs, users u join u" +
           ".waitingLists w join w.bookOwner b" +
           " where bs.bookId =b.book_id and u.first_name = :first_name and u.last_name = :last_name")
    List<GetUsersFromWaitingListWithBookTitle> getUsersFromWaitingListWithBookTitle(
           @Param("first_name") String first_name,
           @Param("last_name") String last_name);


    @Query("select wl from waiting_list wl where wl.user_id = ?1")
    List<WaitingList> findWaitingListsByUser_id(Long id);

}
