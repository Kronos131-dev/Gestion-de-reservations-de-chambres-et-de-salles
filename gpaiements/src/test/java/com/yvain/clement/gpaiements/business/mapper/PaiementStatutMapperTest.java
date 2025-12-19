package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaiementStatutMapperTest {

    private PaiementStatutMapper paiementStatutMapper;

    @Test
    void shouldMapPaiementStatutEntityToDto() {
        PaiementStatutEntity statutEntity = new PaiementStatutEntity();
        statutEntity.setIdStatut(1);
        statutEntity.setCode("En cour");
        statutEntity.setDescription("Paiement en traitement");

        PaiementStatutDto statutDto = paiementStatutMapper.toDto(statutEntity);

        assertEquals(1, statutDto.idStatut());
        assertEquals("En cour", statutDto.code());
        assertEquals("Paiement en traitement", statutDto.description());


    }
}
