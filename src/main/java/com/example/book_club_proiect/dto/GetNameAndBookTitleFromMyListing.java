package com.example.book_club_proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetNameAndBookTitleFromMyListing {

    public String first_name;
    public String last_name;
    public String book_title;
}
