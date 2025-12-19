package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaiemmentMapperTest {

    private final PaiementMapper paiementMapper = new PaiementMapper();

    @Test
    void shouldMapPaiementEntityToDto() {
        PaiementStatutEntity statutEntity = new PaiementStatutEntity();
        statutEntity.setIdStatut(1);

        PrestationEntity prestationEntity = new PrestationEntity();
        prestationEntity.setIdPrestation(1);

        PaiementEntity paiementEntity = new PaiementEntity();
        paiementEntity.setIdPaiement(1);
        paiementEntity.setPrestation(prestationEntity);
        paiementEntity.setStatut(statutEntity);
        paiementEntity.setPrix(BigDecimal.valueOf(300.50));
        paiementEntity.setDate(LocalDate.now());

        PaiementDto paiementDto = paiementMapper.toDto(paiementEntity);

        assertEquals(1, paiementDto.idPaiement());
        assertEquals(1, paiementDto.idStatut());
        assertEquals(1, paiementDto.idPrestation());
        assertEquals(BigDecimal.valueOf(300.50), paiementDto.prix());
    }



}

