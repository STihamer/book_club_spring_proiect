package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.UserDTO;
import com.example.book_club_proiect.models.User;

public interface UserMapper {
   UserDTO toDto(User entity);
}
