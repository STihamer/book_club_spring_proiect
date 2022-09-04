package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.mapper.BookMapper;
import com.example.book_club_proiect.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDTO toDto(Book entity) {
        BookDTO dto = BookDTO.builder()
                .bookId(entity.getBook_id())
                .bookTitle(entity.getBook_title())
                .authorFirstName(entity.getAuthor_fname())
                .authorLastName(entity.getAuthor_lname())
                .publishedYear(entity.getPublished())
                .build();
        return dto;
    }
}
