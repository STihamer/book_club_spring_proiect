package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.RolesDTO;
import com.example.book_club_proiect.dto.UserCreateDTO;
import com.example.book_club_proiect.dto.UserDTO;
import com.example.book_club_proiect.mapper.UserMapper;
import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.Roles;
import com.example.book_club_proiect.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toDto(User entity) {
        UserDTO dto = UserDTO.builder()
                .userId(entity.getUser_id())
                .firstName(entity.getFirst_name())
                .lastName(entity.getLast_name())
                .userAge(entity.getUser_age())
                .username(entity.getUsername())
                .userEmail(entity.getUser_email())
                .roleId(entity.getRole_id())
                .build();
        return dto;
    }

    @Override
    public User toEntity(UserCreateDTO createDTO) {
        return User.builder()
                .user_id(createDTO.getUserId())
                .first_name(createDTO.getFirstName())
                .last_name(createDTO.getLastName())
                .user_age(createDTO.getUserAge())
                .username(createDTO.getUsername())
                .user_email(createDTO.getUsername())
                .user_password(createDTO.getPassword())
                .role_id(createDTO.getRoleId())
                .build();
    }
}
