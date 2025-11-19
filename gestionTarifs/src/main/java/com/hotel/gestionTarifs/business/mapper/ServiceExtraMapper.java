package com.hotel.gestionTarifs.business.mapper;

import com.hotel.gestionTarifs.business.dto.ServiceExtraDTO;
import com.hotel.gestionTarifs.persistence.entity.ServiceExtra;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceExtraMapper {

    public static ServiceExtraDTO toDTO(ServiceExtra entity) {
        if (entity == null) return null;
        return new ServiceExtraDTO(
                entity.getIdService(),
                entity.getNom(),
                entity.getDescription(),
                entity.getPrix()
        );
    }

    public static ServiceExtra toEntity(ServiceExtraDTO dto) {
        if (dto == null) return null;
        ServiceExtra entity = new ServiceExtra();
        entity.setIdService(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());
        entity.setPrix(dto.getPrix());
        return entity;
    }

    public static List<ServiceExtraDTO> toDTOList(List<ServiceExtra> entities) {
        return entities.stream()
                .map(ServiceExtraMapper::toDTO)
                .collect(Collectors.toList());
    }
}
