package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.dto.GetUsersFromWaitingListWithBookTitle;
import com.example.book_club_proiect.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

    @Query(value = "SELECT new com.example.book_club_proiect.dto.GetUsersFromWaitingListWithBookTitle" +
            "(u.firstName , u.lastName, b.bookId, bs.bookTitle ) from  books bs, users u join u" +
            ".waitingLists w join w.bookOwner b" +
            " where bs.bookId =b.bookId and u.firstName = :first_name and u.lastName = :last_name")
    List<GetUsersFromWaitingListWithBookTitle> getUsersFromWaitingListWithBookTitle(
            @Param("first_name") String first_name,
            @Param("last_name") String last_name);



    List<WaitingList> findWaitingListsByUserId(Long id);
    List<WaitingList> findWaitingListsByBookForReading(Long id);

    List<WaitingList> findWaitingListsByUserIdAndBookForReading(Long userId, Long bookForReading);
}
