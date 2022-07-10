package com.example.book_club_proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.annotation.AliasFor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetUsersFromWaitingListWithBookTitle {

   private String first_name;
    private String last_name;

    private Long book_id;

    String book_title;
}
