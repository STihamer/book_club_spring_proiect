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
                .readingPerson(entity.getReading_person())
                .bookTitle(entity.getBook_title())
                .build();
        return dto;
    }
}


