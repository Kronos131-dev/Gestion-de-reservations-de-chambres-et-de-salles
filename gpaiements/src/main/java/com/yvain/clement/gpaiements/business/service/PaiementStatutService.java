package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import com.yvain.clement.gpaiements.business.mapper.PaiementStatutMapper;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import com.yvain.clement.gpaiements.persistence.repository.PaiementStatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaiementStatutService implements IPaiementStatutService{

    private final PaiementStatutRepository paiementStatutRepository;
    private final PaiementStatutMapper paiementStatutMapper;

    @Autowired
    public PaiementStatutService(PaiementStatutRepository paiementStatutRepository, PaiementStatutMapper paiementStatutMapper) {
        this.paiementStatutRepository = paiementStatutRepository;
        this.paiementStatutMapper = paiementStatutMapper;
    }

    @Override
    public PaiementStatutDto getById(Integer id) {
        PaiementStatutEntity paiementStatutEntity = paiementStatutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Statut paiement introuvable"));

        return paiementStatutMapper.toDto(paiementStatutEntity);
    }

    @Override
    public List<PaiementStatutDto> getAll() {
        List<PaiementStatutEntity> paiementStatutEntities = paiementStatutRepository.findAll();
        List<PaiementStatutDto> paiementStatutDtos = new ArrayList<>();
        paiementStatutEntities.forEach(paiementStatutEntity -> paiementStatutDtos.add(paiementStatutMapper.toDto(paiementStatutEntity)));
        return paiementStatutDtos;
    }

}


