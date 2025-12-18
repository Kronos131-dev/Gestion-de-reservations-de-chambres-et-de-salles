package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.business.mapper.PaiementMapper;
import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import com.yvain.clement.gpaiements.persistence.repository.PaiementRepository;
import com.yvain.clement.gpaiements.persistence.repository.PaiementStatutRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaiementService implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final PaiementStatutRepository statutRepository;
    private final PaiementMapper paiementMapper;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository, PaiementStatutRepository statutRepository, PaiementMapper paiementMapper) {
        this.paiementRepository = paiementRepository;
        this.statutRepository = statutRepository;
        this.paiementMapper = paiementMapper;
    }

    @Override
    @Transactional
    public PaiementDto create(PaiementDto paiementDto) {
        PaiementEntity paiementEntity = paiementMapper.toEntity(paiementDto);
        if (paiementDto.statutId() != null) {
            PaiementStatutEntity statut = statutRepository.findById(paiementDto.statutId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Statut inexistant"));
            paiementEntity.setStatut(statut);
        }
        PaiementEntity savedEntity = paiementRepository.save(paiementEntity);
        return paiementMapper.toDto(savedEntity);
    }

    public PaiementDto getById(Integer id) {
        PaiementEntity paiementEntity = paiementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paiement introuvable"));
        return paiementMapper.toDto(paiementEntity);
    }

    public List<PaiementDto> getAll() {
        List<PaiementEntity> paiementEntities = paiementRepository.findAll();
        List<PaiementDto> paiementDtos = new ArrayList<>();
        paiementEntities.forEach(paiement -> paiementDtos.add(paiementMapper.toDto(paiement)));
        return paiementDtos;
    }

    public void delete(Integer id) {
        PaiementEntity paiementEntity = paiementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paiement introuvable"));
        paiementRepository.delete(paiementEntity);
    }
}