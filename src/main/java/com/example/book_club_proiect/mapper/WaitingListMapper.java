package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.WaitingListDTO;
import com.example.book_club_proiect.models.WaitingList;

public interface WaitingListMapper {
    WaitingListDTO toDto(WaitingList entity);
}
