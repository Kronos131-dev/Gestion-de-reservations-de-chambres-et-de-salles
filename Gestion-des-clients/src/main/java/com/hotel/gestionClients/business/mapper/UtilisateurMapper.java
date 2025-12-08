package com.hotel.gestionClients.business.mapper;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.persistence.entity.Utilisateur;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UtilisateurMapper {

    public static UtilisateurDTO toDto(Utilisateur entity) {
        if (entity == null) return null;

        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(entity.getIdUtilisateur());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setTel(entity.getTel());
        dto.setDateNaissance(entity.getDateNaissance());  // OK

        dto.setAdresse(AdresseMapper.toDto(entity.getAdresse()));
        dto.setRole(RoleMapper.toDto(entity.getRole()));

        return dto;
    }

    public static Utilisateur toEntity(UtilisateurDTO dto) {
        if (dto == null) return null;

        Utilisateur entity = new Utilisateur();
        entity.setIdUtilisateur(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setTel(dto.getTel());
        entity.setDateNaissance(dto.getDateNaissance()); // OK

        entity.setAdresse(AdresseMapper.toEntity(dto.getAdresse()));
        entity.setRole(RoleMapper.toEntity(dto.getRole()));

        return entity;
    }

    public static List<UtilisateurDTO> toDtoList(List<Utilisateur> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(UtilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Utilisateur> toEntityList(List<UtilisateurDTO> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(UtilisateurMapper::toEntity)
                .collect(Collectors.toList());
    }
}
