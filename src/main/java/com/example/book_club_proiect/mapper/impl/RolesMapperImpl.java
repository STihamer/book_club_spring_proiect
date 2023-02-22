package com.example.book_club_proiect.mapper.impl;
import com.example.book_club_proiect.dto.RolesDTO;
import com.example.book_club_proiect.mapper.RolesMapper;
import com.example.book_club_proiect.models.Roles;
import org.springframework.stereotype.Component;

@Component
public class RolesMapperImpl implements RolesMapper {
    @Override
    public RolesDTO toDto(Roles entity) {
        RolesDTO dto = RolesDTO.builder()
                .roleId(entity.getRoleId())
                .roleName(entity.getRoleName())
                .build();
        return dto;
    }
}
