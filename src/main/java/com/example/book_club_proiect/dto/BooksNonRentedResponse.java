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
public class BooksNonRentedResponse {
    private Long book_id;
    private String book_title;
    private String author_fname;
    private String author_lname;
    private LocalDate return_date;

}
