package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.mapper.BookMapper;
import com.example.book_club_proiect.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDTO toDto(Book entity) {
        return BookDTO.builder()
                .bookId(entity.getBookId())
                .bookTitle(entity.getBookTitle())
                .authorFirstName(entity.getAuthorFname())
                .authorLastName(entity.getAuthorLname())
                .publishedYear(entity.getPublished())
                .build();
    }

    @Override
    public Book toEntity(BookDTO createDTO) {
        return Book.builder()
                .bookId(createDTO.getBookId())
                .bookTitle(createDTO.getBookTitle())
                .authorFname(createDTO.getAuthorFirstName())
                .authorLname(createDTO.getAuthorLastName())
                .published(createDTO.getPublishedYear())
                .build();
    }
}
