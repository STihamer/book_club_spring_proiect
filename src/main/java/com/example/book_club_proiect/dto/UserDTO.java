package com.example.book_club_proiect.dto;

import com.example.book_club_proiect.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private Integer userAge;
    private String username;
    private String userEmail;
    private Long roleId;
}
