package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.WaitingListDTO;
import com.example.book_club_proiect.mapper.WaitingListMapper;
import com.example.book_club_proiect.models.WaitingList;
import org.springframework.stereotype.Component;

@Component
public class WaitingListMapperImpl implements WaitingListMapper {
    @Override
    public WaitingListDTO toDto(WaitingList entity) {
        WaitingListDTO dto = WaitingListDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .bookForReading(entity.getBookForReading())
                .finished(entity.getFinished())
                .build();
        return dto;
    }

    @Override
    public WaitingList toEntity(WaitingListDTO createDTO) {
        return WaitingList.builder()
                .id(createDTO.getId())
                .userId(createDTO.getUserId())
                .bookForReading(createDTO.getBookForReading())
                .finished(createDTO.getFinished())
                .build();
    }
}
