package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.springframework.stereotype.Component;

@Component
public class PaiementStatutMapper {

    public static PaiementStatutDto toDto(PaiementStatutEntity entity) {
        if (entity == null) return null;

        return new PaiementStatutDto(
                entity.getIdStatut(),
                entity.getCode(),
                entity.getDescription()
        );
    }

    public static PaiementStatutEntity toEntity(PaiementStatutDto dto) {
        if (dto == null) return null;

        PaiementStatutEntity entity = new PaiementStatutEntity();
        entity.setIdStatut(dto.idStatut());
        entity.setCode(dto.code());
        entity.setDescription(dto.description());

        return entity;
    }
}