package com.example.book_club_proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindBookByTitleInRentingTable {


    /*private String last_name;*/
    private String book_title;
    private LocalDate return_date;
    private String first_name;

}
