package com.yvain.clement.gpaiements.business.service;


import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.business.mapper.PaiementMapper;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import com.yvain.clement.gpaiements.persistence.repository.PaiementRepository;
import com.yvain.clement.gpaiements.persistence.repository.PaiementStatutRepository;
import com.yvain.clement.gpaiements.persistence.repository.PrestationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaiementServiceTest {

    @Mock
    private PaiementRepository paiementRepository;

    @Mock
    private PaiementStatutRepository statutRepository;

    @Mock
    private PaiementMapper paiementMapper;

    @Mock
    private PrestationRepository prestationRepository;

    @InjectMocks
    private PaiementService paiementService;


    @Test
     void shouldCreatePaiement() {
        PaiementDto paiementDto = new PaiementDto(
                null,
                1,
                10,
                BigDecimal.valueOf(100),
                LocalDate.now()
        );

        PaiementEntity paiementEntity = new PaiementEntity();
        PaiementEntity paiementEntity2 = new PaiementEntity();

        PaiementStatutEntity paiementStatutEntity = new PaiementStatutEntity();
        PrestationEntity prestationEntity = new PrestationEntity();

        when(paiementMapper.toEntity(paiementDto)).thenReturn(paiementEntity);
        when(statutRepository.findById(1)).thenReturn(Optional.of(paiementStatutEntity));
        when(prestationRepository.findById(1)).thenReturn(Optional.of(prestationEntity));
        when(paiementRepository.save(paiementEntity)).thenReturn(paiementEntity2);
        when(paiementMapper.toDto(paiementEntity2)).thenReturn(new PaiementDto(1, 1, 10, BigDecimal.valueOf(100), LocalDate.now()));

        PaiementDto result = paiementService.create(paiementDto);

        assertNotNull(result);
        verify(paiementRepository).save(paiementEntity);
    }

    @Test
    void shouldGetPaiementById() {
        PaiementEntity paiementEntity = new PaiementEntity();
        PaiementDto paiementDto = new PaiementDto(
                1, 1, 10, BigDecimal.valueOf(100), LocalDate.now()
        );
        when(paiementRepository.findById(1)).thenReturn(Optional.of(paiementEntity));
        when(paiementMapper.toDto(paiementEntity)).thenReturn(paiementDto);

        PaiementDto result = paiementService.getById(1);
        assertEquals(1, result.idPaiement());
    }

    @Test
    void shouldgetAllPaiement() {
        PaiementEntity paiementEntity = new PaiementEntity();
        when(paiementRepository.findAll()).thenReturn(List.of(paiementEntity));
        when(paiementMapper.toDto(paiementEntity)).thenReturn(new PaiementDto(1, 1, 10, BigDecimal.valueOf(10), LocalDate.now()));
        List<PaiementDto> result = paiementService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void shouldDeletePaiement() {
        PaiementEntity paiementEntity = new PaiementEntity();
        when(paiementRepository.findById(1)).thenReturn(Optional.of(paiementEntity));

        paiementService.delete(1);

        verify(paiementRepository).delete(paiementEntity);
    }
}
