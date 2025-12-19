package com.yvain.clement.gpaiements.business.service;


import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import com.yvain.clement.gpaiements.business.mapper.PrestationMapper;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import com.yvain.clement.gpaiements.persistence.repository.PrestationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrestationServiceTest {

    @Mock
    private PrestationRepository prestationRepository;

    @Mock
    private PrestationMapper prestationMapper;

    @InjectMocks
    private PrestationService prestationService;

    @Test
    public void shouldGetById() {
        PrestationEntity prestationEntity = new PrestationEntity();
        PrestationDto prestationDto = new PrestationDto(1, 1, 1, BigDecimal.valueOf(100));

        when(prestationRepository.findById(1)).thenReturn(Optional.of(prestationEntity));
        when(prestationMapper.toDto(prestationEntity)).thenReturn(prestationDto);

        PrestationDto result = prestationService.getById(1);
        assertEquals(1, result.idPrestation());
    }

    @Test
    public void shouldGetPrixById() {
        PrestationEntity prestationEntity = new PrestationEntity();
        PrestationDto prestationDto = new PrestationDto(1, 1, 1, BigDecimal.valueOf(100));

        when(prestationRepository.findById(1)).thenReturn(Optional.of(prestationEntity));
        when(prestationMapper.toDto(prestationEntity)).thenReturn(prestationDto);

        BigDecimal result = prestationService.getPrixById(1);
        assertEquals(BigDecimal.valueOf(100), result);
    }
}
