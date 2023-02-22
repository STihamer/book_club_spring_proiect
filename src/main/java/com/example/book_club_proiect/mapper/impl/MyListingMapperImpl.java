package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.MyListingDTO;
import com.example.book_club_proiect.mapper.MyListingMapper;
import com.example.book_club_proiect.models.MyListing;
import org.springframework.stereotype.Component;

@Component
public class MyListingMapperImpl implements MyListingMapper {
    @Override
    public MyListingDTO toDto(MyListing entity) {
        MyListingDTO dto = MyListingDTO.builder()
                .id(entity.getId())
                .readingPerson(entity.getReadingPerson())
                .bookTitle(entity.getBookTitle())
                .build();
        return dto;
    }

    @Override
    public MyListing toEntity(MyListingDTO createDTO) {
        return MyListing.builder()
                .id(createDTO.getId())
                .readingPerson(createDTO.getReadingPerson())
                .bookTitle(createDTO.getBookTitle())
                .build();
    }
}


