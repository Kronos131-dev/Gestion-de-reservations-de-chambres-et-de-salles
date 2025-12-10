package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import org.springframework.stereotype.Component;

@Component
public class PrestationMapper {

    public static PrestationDto toDto(PrestationEntity entity) {
        if (entity == null) return null;

        return new PrestationDto(
                entity.getIdPrestation(),
                entity.getIdReservation(),
                entity.getPaiement().getIdPaiement(),
                entity.getPrix()
        );
    }

    public static PrestationEntity toEntity(PrestationDto dto, PaiementEntity paiement) {
        if (dto == null) return null;

        PrestationEntity entity = new PrestationEntity();
        entity.setIdPrestation(dto.idPrestation());
        entity.setIdReservation(dto.reservationId());
        entity.setPaiement(paiement);
        entity.setPrix(dto.prix());

        return entity;
    }
}