package com.example.book_club_proiect.repositories;


import com.example.book_club_proiect.models.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
    @Query("select bo from book_owner bo where bo.book_id = ?1 and bo.user_id = ?2")
    List<BookOwner> findBookOwnersByBook_idAndUser_id(Long bookId, Long userId);
}
