package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.models.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
