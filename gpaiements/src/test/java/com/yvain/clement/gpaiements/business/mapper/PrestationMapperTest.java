package com.yvain.clement.gpaiements.business.mapper;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PrestationMapperTest {

    private PrestationMapper prestationMapper = new PrestationMapper();

    @Test
    public void shouldMapPrestationEntityToDto() {
        PrestationEntity entity = new PrestationEntity();
        entity.setIdPrestation(1);
        entity.setPrix(BigDecimal.valueOf(300.50));
        entity.setIdReservation(12);

        PrestationDto prestationDto = prestationMapper.toDto(entity);
        assertEquals(1, prestationDto.idPrestation());
        assertEquals(BigDecimal.valueOf(300.50), prestationDto.prix());
        assertEquals(12, prestationDto.reservationId());
    }
}
