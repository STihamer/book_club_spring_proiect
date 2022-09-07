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
    private Long borrowedBy;
    private Long bookId;
    private LocalDate borrowedDate;
    private Long rentingPeriod;
    private LocalDate returnDate;
    private Boolean returnDateExtended;
}
