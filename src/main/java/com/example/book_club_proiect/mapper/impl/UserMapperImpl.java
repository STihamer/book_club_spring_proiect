package com.example.book_club_proiect.mapper.impl;

import com.example.book_club_proiect.dto.UserCreateDTO;
import com.example.book_club_proiect.dto.UserDTO;
import com.example.book_club_proiect.mapper.UserMapper;
import com.example.book_club_proiect.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toDto(User entity) {
        UserDTO dto = UserDTO.builder()
                .userId(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .userAge(entity.getUserAge())
                .username(entity.getUsername())
                .userEmail(entity.getUserEmail())
                .roleId(entity.getRoleId())
                .build();
        return dto;
    }

    @Override
    public User toEntity(UserCreateDTO createDTO) {
        return User.builder()
                .userId(createDTO.getUserId())
                .firstName(createDTO.getFirstName())
                .lastName(createDTO.getLastName())
                .userAge(createDTO.getUserAge())
                .username(createDTO.getUsername())
                .userEmail(createDTO.getUsername())
                .userPassword(createDTO.getPassword())
                .roleId(createDTO.getRoleId())
                .build();
    }
}
