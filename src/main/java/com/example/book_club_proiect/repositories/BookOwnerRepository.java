package com.example.book_club_proiect.repositories;


import com.example.book_club_proiect.models.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {

    List<BookOwner> findBookOwnersByBookIdAndUserId(Long bookId, Long userId);

    List<BookOwner>findBookOwnersByUserId(Long userId);
    List<BookOwner> findBookOwnersByBookId(Long id);
}
