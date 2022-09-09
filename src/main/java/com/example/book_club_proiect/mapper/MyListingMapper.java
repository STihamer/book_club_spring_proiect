package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.dto.MyListingDTO;
import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.MyListing;

public interface MyListingMapper {
    MyListingDTO toDto(MyListing entity);
    MyListing toEntity(MyListingDTO createDTO);
}
