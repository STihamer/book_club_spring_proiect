package com.example.book_club_proiect.repositories;


import com.example.book_club_proiect.models.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
    
}
