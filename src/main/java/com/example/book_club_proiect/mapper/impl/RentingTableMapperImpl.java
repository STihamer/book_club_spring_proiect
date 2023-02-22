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
                .borrowedBy(entity.getBorrowedBy())
                .bookId(entity.getBookId())
                .borrowedDate(entity.getBorrowedDate())
                .rentingPeriod(entity.getRentingPeriod())
                .returnDate(entity.getReturnDate())
                .returnDateExtended(entity.getReturnDateExtended())
                .build();
        return dto;
    }

    @Override
    public RentingTable toEntity(RentingTableDTO createDTO) {
        return RentingTable.builder()
                .id(createDTO.getId())
                .borrowedBy(createDTO.getBorrowedBy())
                .bookId(createDTO.getBookId())
                .borrowedDate(createDTO.getBorrowedDate())
                .rentingPeriod(createDTO.getRentingPeriod())
                .returnDate(createDTO.getReturnDate())
                .returnDateExtended(createDTO.getReturnDateExtended())
                .build();
    }
}