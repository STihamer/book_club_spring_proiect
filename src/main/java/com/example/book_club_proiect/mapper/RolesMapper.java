package com.example.book_club_proiect.mapper;

import com.example.book_club_proiect.dto.RolesDTO;
import com.example.book_club_proiect.models.Roles;

public interface RolesMapper {
    RolesDTO toDto(Roles entity);
}
