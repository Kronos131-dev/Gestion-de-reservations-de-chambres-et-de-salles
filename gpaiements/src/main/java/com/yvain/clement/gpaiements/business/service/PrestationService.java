package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import com.yvain.clement.gpaiements.business.mapper.PrestationMapper;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import com.yvain.clement.gpaiements.persistence.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class PrestationService implements IPrestationService {

    private final PrestationRepository prestationRepository;
    private final PrestationMapper prestationMapper;

    @Autowired
    public PrestationService(PrestationRepository prestationRepository, PrestationMapper prestationMapper) {
        this.prestationRepository = prestationRepository;
        this.prestationMapper = prestationMapper;
    }

    @Override
    public PrestationDto getById(Integer id) {
        PrestationEntity prestationEntity = prestationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestation introuvable"));
        return prestationMapper.toDto(prestationEntity);
    }

    @Override
    public BigDecimal getPrixById(Integer id) {
        PrestationEntity prestationEntity = prestationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestation introuvable"));
        return prestationEntity.getPrix();
    }
}
