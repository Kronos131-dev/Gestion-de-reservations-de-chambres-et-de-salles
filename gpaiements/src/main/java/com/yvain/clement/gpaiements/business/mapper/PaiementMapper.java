package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public PaiementDto toDto(PaiementEntity entity) {
        if (entity == null) return null;

        return new PaiementDto(
                entity.getIdPaiement(),
                entity.getStatut() != null ? entity.getStatut().getIdStatut() : null,
                entity.getPrix(),
                entity.getDate()
        );
    }

    public PaiementEntity toEntity(PaiementDto dto) {
        if (dto == null) return null;

        PaiementEntity entity = new PaiementEntity();
        entity.setIdPaiement(dto.idPaiement());
        entity.setPrix(dto.prix());
        entity.setDate(dto.date());

        return entity;
    }
}