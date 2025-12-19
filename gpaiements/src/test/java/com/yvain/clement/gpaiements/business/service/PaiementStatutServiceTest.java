package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import com.yvain.clement.gpaiements.business.mapper.PaiementMapper;
import com.yvain.clement.gpaiements.business.mapper.PaiementStatutMapper;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import com.yvain.clement.gpaiements.persistence.repository.PaiementStatutRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaiementStatutServiceTest {

    @Mock
    private PaiementStatutRepository paiementStatutRepository;

    @Mock
    private PaiementStatutMapper paiementStatutMapper;

    @InjectMocks
    private PaiementStatutService paiementStatutService;

    @Test
    public void shouldGetAllPaiementStatut() {
        PaiementStatutEntity paiementStatutEntity = new PaiementStatutEntity();
        when(paiementStatutRepository.findAll()).thenReturn(List.of(paiementStatutEntity));
        when(paiementStatutMapper.toDto(paiementStatutEntity)).thenReturn(new PaiementStatutDto(1, "En cours", "Traitement en cours"));

        List<PaiementStatutDto> result = paiementStatutService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    public void shouldGetPaiementStatutById() {
        PaiementStatutEntity paiementStatutEntity = new PaiementStatutEntity();
        PaiementStatutDto paiementStatutDto = new PaiementStatutDto(1, "En cours", "Traitement en cours");
        when(paiementStatutRepository.findById(1)).thenReturn(Optional.of(paiementStatutEntity));
        when(paiementStatutMapper.toDto(paiementStatutEntity)).thenReturn(paiementStatutDto);

        PaiementStatutDto result = paiementStatutService.getById(1);
        assertEquals(1, result.idStatut());
    }
}
