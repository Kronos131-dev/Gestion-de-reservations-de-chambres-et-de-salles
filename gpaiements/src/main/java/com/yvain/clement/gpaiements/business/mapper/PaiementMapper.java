package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public static PaiementDto toDto(PaiementEntity entity) {
        if (entity == null) return null;

        return new PaiementDto(
                entity.getIdPaiement(),
                entity.getStatut().getIdStatut(),
                entity.getPrix(),
                entity.getDate()
        );
    }

    public static PaiementEntity toEntity(PaiementDto dto, PaiementStatutEntity statut) {
        if (dto == null) return null;

        PaiementEntity entity = new PaiementEntity();
        entity.setIdPaiement(dto.idPaiement());
        entity.setStatut(statut);
        entity.setPrix(dto.prix());
        entity.setDate(dto.date());

        return entity;
    }
}