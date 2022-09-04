package com.example.book_club_proiect.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentingTableDTO {

    private Long id;
    private Long borrowed_by;
    private Long book_id;
    private LocalDate borrowed_date;
    private Long renting_period;
    private LocalDate return_date;
    private Boolean return_date_extended;
}
