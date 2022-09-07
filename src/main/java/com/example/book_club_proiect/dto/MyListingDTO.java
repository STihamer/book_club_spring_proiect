package com.example.book_club_proiect.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyListingDTO {
    private Long id;
    private Long readingPerson;
    private Long bookTitle;

}
