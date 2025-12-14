package com.hotel.gestionClients.business.mapper;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.persistence.entity.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UtilisateurMapper {

    public UtilisateurDTO toDto(Utilisateur entity) {
        if (entity == null) return null;

        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(entity.getIdUtilisateur());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setTel(entity.getTel());
        dto.setDateNaissance(entity.getDateNaissance());

        if (entity.getAdresse() != null) {

            dto.setAdresse(AdresseMapper.toDto(entity.getAdresse()));
        }

        if (entity.getRole() != null) {
            dto.setRole(RoleMapper.toDto(entity.getRole()));
        }

        return dto;
    }

    public Utilisateur toEntity(UtilisateurDTO dto) {
        if (dto == null) return null;

        Utilisateur entity = new Utilisateur();
        entity.setIdUtilisateur(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setTel(dto.getTel());
        entity.setDateNaissance(dto.getDateNaissance());

        if (dto.getAdresse() != null) {
            entity.setAdresse(AdresseMapper.toEntity(dto.getAdresse()));
        }

        if (dto.getRole() != null) {
            entity.setRole(RoleMapper.toEntity(dto.getRole()));
        }

        return entity;
    }

    public List<UtilisateurDTO> toDtoList(List<Utilisateur> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Utilisateur> toEntityList(List<UtilisateurDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}