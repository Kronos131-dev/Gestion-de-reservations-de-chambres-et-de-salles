package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrestationMapper {
    @Mapping(target = "reservationId", source = "reservation.idReservation")
    @Mapping(target = "paiementId", source = "paiement.idPaiement")
    PrestationDto toDto(PrestationEntity entity);

    @Mapping(target = "reservation.idReservation", source = "reservationId")
    @Mapping(target = "paiement.idPaiement", source = "paiementId")
    PrestationEntity toEntity(PrestationDto dto);
}
