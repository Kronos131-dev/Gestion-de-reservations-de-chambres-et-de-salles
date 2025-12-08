package com.hotel.gestionClients.business.mapper;



import com.hotel.gestionClients.business.dto.RoleDTO;
import com.hotel.gestionClients.persistence.entity.Role;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDTO toDto(Role entity) {
        if (entity == null) return null;

        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getIdRole());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        dto.setNivAutorisation(entity.getNivAutorisation());

        return dto;
    }

    public static Role toEntity(RoleDTO dto) {
        if (dto == null) return null;

        Role entity = new Role();
        entity.setIdRole(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setNivAutorisation(dto.getNivAutorisation());

        return entity;
    }

    public static List<RoleDTO> toDtoList(List<Role> entities) {
        if (entities == null) return null;

        return entities.stream()
                .filter(Objects::nonNull)
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Role> toEntityList(List<RoleDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .filter(Objects::nonNull)
                .map(RoleMapper::toEntity)
                .collect(Collectors.toList());
    }
}
