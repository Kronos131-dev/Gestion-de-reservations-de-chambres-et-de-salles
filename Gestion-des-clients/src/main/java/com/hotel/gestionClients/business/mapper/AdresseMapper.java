package com.hotel.gestionClients.business.mapper;


import com.hotel.gestionClients.business.dto.AdresseDTO;
import com.hotel.gestionClients.persistence.entity.Adresse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdresseMapper {

    public static AdresseDTO toDto(Adresse entity) {
        if (entity == null) return null;

        AdresseDTO dto = new AdresseDTO();
        dto.setId(entity.getIdAdresse());
        dto.setNum(entity.getNum());
        dto.setRue(entity.getRue());
        dto.setVille(entity.getVille());
        dto.setCodePostal(entity.getCodePostal());
        dto.setPays(entity.getPays());

        return dto;
    }

    public static Adresse toEntity(AdresseDTO dto) {
        if (dto == null) return null;

        Adresse entity = new Adresse();
        entity.setIdAdresse(dto.getId());
        entity.setNum(dto.getNum());
        entity.setRue(dto.getRue());
        entity.setVille(dto.getVille());
        entity.setCodePostal(dto.getCodePostal());
        entity.setPays(dto.getPays());

        return entity;
    }

    public static List<AdresseDTO> toDtoList(List<Adresse> entities) {
        if (entities == null) return null;

        return entities.stream()
                .filter(Objects::nonNull)
                .map(AdresseMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Adresse> toEntityList(List<AdresseDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .filter(Objects::nonNull)
                .map(AdresseMapper::toEntity)
                .collect(Collectors.toList());
    }
}

