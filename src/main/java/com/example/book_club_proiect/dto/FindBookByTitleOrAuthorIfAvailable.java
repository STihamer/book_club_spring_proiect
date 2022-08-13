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
public class FindBookByTitleOrAuthorIfAvailable {


    /*private String last_name;*/

    private Long book_id;
    private String author_fname;
    private String author_lname;
    private String book_title;
    private LocalDate return_date;
}
