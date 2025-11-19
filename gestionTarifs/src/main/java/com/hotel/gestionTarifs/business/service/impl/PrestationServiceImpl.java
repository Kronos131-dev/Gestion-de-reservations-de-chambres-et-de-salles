package com.hotel.gestionTarifs.business.service.impl;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.mapper.PrestationMapper;
import com.hotel.gestionTarifs.business.service.IPrestationService;
import com.hotel.gestionTarifs.persistence.entity.Prestation;
import com.hotel.gestionTarifs.persistence.repository.PrestationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.gestionTarifs.persistence.entity.Reservation;
import com.hotel.gestionTarifs.persistence.entity.Paiement;

import java.util.List;

@Service
public class PrestationServiceImpl implements IPrestationService {

    @Autowired
    private PrestationRepository prestationRepository;

    private final PrestationMapper mapper = PrestationMapper.getInstance();

    @Override
    @Transactional
    public PrestationDTO create(PrestationDTO dto) {
        Prestation entity =mapper.convertToEntity(dto);
        Prestation saved =prestationRepository.save(entity);
        return mapper.convertToDTO(saved);
    }

    @Override
    @Transactional
    public PrestationDTO update(Integer id,PrestationDTO dto) {
        Prestation entity =prestationRepository.findById(id).orElseThrow(() -> new RuntimeException("Prestation non trouvée"));
        entity.setPrix(dto.getPrix());
        if (dto.getIdReservation() != null) {
            Reservation r = new Reservation();
            r.setIdReservation(dto.getIdReservation());
            entity.setReservation(r); }
        if (dto.getIdPaiement() !=null) {
            Paiement p= new Paiement();
            p.setIdpaiement(dto.getIdPaiement());
            entity.setPaiement(p);}
        Prestation updated =prestationRepository.save(entity);
        return mapper.convertToDTO(updated); }

    @Override
    public PrestationDTO getById(Integer id) {
        Prestation p =prestationRepository.findById(id).orElseThrow(() -> new RuntimeException("Prestation non trouvée"));
        return mapper.convertToDTO(p); }

    @Override
    public List<PrestationDTO> getAll() {
        return mapper.convertToDTOList(prestationRepository.findAll()); }

    @Override
    @Transactional
    public void delete(Integer id) {
        prestationRepository.deleteById(id);
    }
}
