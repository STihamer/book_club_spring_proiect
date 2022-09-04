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

    private Long user_id;
    private String first_name;
    private String last_name;
    private Integer user_age;
    private String username;
    private String user_email;
    private Long role_id;
    private Roles roles;
}
