package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.BookOwnerDTO;
import com.example.book_club_proiect.mapper.BookOwnerMapper;
import com.example.book_club_proiect.models.BookOwner;
import org.springframework.stereotype.Component;

@Component
public class BookOwnerMapperImpl implements BookOwnerMapper {
    @Override
    public BookOwnerDTO toDto(BookOwner entity) {

        BookOwnerDTO dto = BookOwnerDTO.builder()
                .id(entity.getId())
                .bookId(entity.getBookId())
                .userId(entity.getUserId())
                .build();
        return dto;
    }

    @Override
    public BookOwner toEntity(BookOwnerDTO createDTO) {
        return BookOwner.builder()
                .id(createDTO.getId())
                .bookId(createDTO.getBookId())
                .userId(createDTO.getUserId())
                .build();
    }
}
