package com.example.book_club_proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitingListDTO {
    private Long id;
    private Long userId;
    private Long bookForReading;
    private Boolean finished;
}
