package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.RentingTableDTO;
import com.example.book_club_proiect.mapper.RentingTableMapper;
import com.example.book_club_proiect.models.RentingTable;
import org.springframework.stereotype.Component;


@Component
public class RentingTableMapperImpl implements RentingTableMapper {
    @Override
    public RentingTableDTO toDto(RentingTable entity) {
        RentingTableDTO dto = RentingTableDTO.builder()
                .id(entity.getId())
                .borrowedBy(entity.getBorrowed_by())
                .bookId(entity.getBook_id())
                .borrowedDate(entity.getBorrowed_date())
                .rentingPeriod(entity.getRenting_period())
                .returnDate(entity.getReturn_date())
                .returnDateExtended(entity.getReturn_date_extended())
                .build();
        return dto;
    }
}