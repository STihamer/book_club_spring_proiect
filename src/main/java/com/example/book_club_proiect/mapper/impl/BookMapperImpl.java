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
                .bookId(entity.getBook_id())
                .bookTitle(entity.getBook_title())
                .authorFirstName(entity.getAuthor_fname())
                .authorLastName(entity.getAuthor_lname())
                .publishedYear(entity.getPublished())
                .build();
    }

    @Override
    public Book toEntity(BookDTO createDTO) {
        return Book.builder()
                .book_id(createDTO.getBookId())
                .book_title(createDTO.getBookTitle())
                .author_fname(createDTO.getAuthorFirstName())
                .author_lname(createDTO.getAuthorLastName())
                .published(createDTO.getPublishedYear())
                .build();
    }
}
