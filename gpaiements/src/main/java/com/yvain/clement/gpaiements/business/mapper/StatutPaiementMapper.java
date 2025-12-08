package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatutPaiementMapper {
    PaiementStatutDto toDto(PaiementStatutEntity entity);
    PaiementStatutEntity toEntity(PaiementStatutDto dto);
}
