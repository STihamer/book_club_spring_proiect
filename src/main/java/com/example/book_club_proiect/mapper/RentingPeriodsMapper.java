package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.RentingPeriodsDTO;
import com.example.book_club_proiect.models.RentingPeriods;


public interface RentingPeriodsMapper {
   RentingPeriodsDTO toDto(RentingPeriods entity);
}
