package com.example.book_club_proiect.dto;


import com.example.book_club_proiect.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindUserByFirstNameAndUserEmail {
  private  String first_name;
  private  String last_name;
  private  Integer user_age;
  private  String username;
  private  String user_email;
}
