package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.models.Book;

public interface BookMapper {
   BookDTO toDto(Book entity);
   Book toEntity(BookDTO createDTO);
}
