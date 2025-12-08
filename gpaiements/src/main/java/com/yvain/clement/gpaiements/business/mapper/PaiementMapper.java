package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper {
    @Mapping(target = "statutId", source = "statut.idStatut")
    PaiementDto toDto(PaiementEntity entity);

    @Mapping(target = "statut.idStatut", source = "statutId")
    PaiementEntity toEntity(PaiementDto dto);
}
