package com.example.book_club_proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String bookTitle;
    private String authorFirstName;
    private String authorLastName;
    private Integer publishedYear;
}
