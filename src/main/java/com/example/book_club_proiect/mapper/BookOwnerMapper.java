package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.dto.BookOwnerDTO;
import com.example.book_club_proiect.dto.UserCreateDTO;
import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.models.User;


public interface BookOwnerMapper {
    BookOwnerDTO toDto(BookOwner entity);
    BookOwner toEntity(BookOwnerDTO createDTO);
}
