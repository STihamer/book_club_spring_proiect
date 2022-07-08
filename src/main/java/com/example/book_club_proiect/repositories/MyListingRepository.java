package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.MyListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyListingRepository extends JpaRepository<MyListing, Long> {


}
