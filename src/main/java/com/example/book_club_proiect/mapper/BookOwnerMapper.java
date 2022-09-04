package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.BookOwnerDTO;
import com.example.book_club_proiect.models.BookOwner;


public interface BookOwnerMapper {
    BookOwnerDTO toDto(BookOwner entity);
}
