package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.BookOwnerDTO;
import com.example.book_club_proiect.dto.RentingTableDTO;
import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.models.RentingTable;


public interface RentingTableMapper {

    RentingTableDTO toDto(RentingTable entity);
    RentingTable toEntity(RentingTableDTO createDTO);
}
