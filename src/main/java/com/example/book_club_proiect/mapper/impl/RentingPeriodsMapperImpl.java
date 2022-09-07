package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.MyListingDTO;
import com.example.book_club_proiect.dto.RentingPeriodsDTO;
import com.example.book_club_proiect.mapper.RentingPeriodsMapper;
import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.models.RentingPeriods;
import org.springframework.stereotype.Component;

@Component
public class RentingPeriodsMapperImpl implements RentingPeriodsMapper {
    @Override
    public RentingPeriodsDTO toDto(RentingPeriods entity) {
        RentingPeriodsDTO dto = RentingPeriodsDTO.builder()
                    .id(entity.getId())
                    .rentingPeriod(entity.getRenting_period())
                    .build();
            return dto;

    }
}
