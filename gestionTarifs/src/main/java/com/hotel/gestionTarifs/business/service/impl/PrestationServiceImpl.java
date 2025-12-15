package com.hotel.gestionTarifs.business.service.impl;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.mapper.PrestationMapper;
import com.hotel.gestionTarifs.business.service.IPrestationService;
import com.hotel.gestionTarifs.persistence.entity.*;
import com.hotel.gestionTarifs.persistence.repository.ExtraRepository;
import com.hotel.gestionTarifs.persistence.repository.PrestationRepository;
import com.hotel.gestionTarifs.persistence.repository.ReservationRepository;
import com.hotel.gestionTarifs.persistence.repository.ServiceExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class PrestationServiceImpl implements IPrestationService {

    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private ServiceExtraRepository serviceExtraRepository;
    @Autowired
    private ExtraRepository extraRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    private final PrestationMapper mapper = PrestationMapper.getInstance();

    @Override
    public PrestationDTO createPrestationWithService(PrestationDTO dto, Integer idServiceSelected) {
        ServiceExtra service = serviceExtraRepository.findById(idServiceSelected).orElseThrow(() -> new RuntimeException("Service introuvable"));
        int qte = (dto.getQuantite() != null && dto.getQuantite() > 0) ? dto.getQuantite() : 1;

        BigDecimal prixUnitaire =service.getPrix();
        BigDecimal prixTotal= prixUnitaire.multiply(BigDecimal.valueOf(qte));

        Prestation entity = mapper.convertToEntity(dto);
        entity.setPrix(prixTotal);

        Prestation savedPrestation =prestationRepository.save(entity);
        Extra extraLink = new Extra();
        ExtraId extraId = new ExtraId();

        extraId.setIdPrestation(savedPrestation.getIdPrestation());
        extraId.setIdService(service.getIdService());
        extraLink.setId(extraId);
        extraLink.setPrestation(savedPrestation);
        extraLink.setServiceExtra(service);

        extraRepository.save(extraLink);
        return mapper.convertToDTO(savedPrestation);
    }

    @Override
    public PrestationDTO createPrestationSejour(Integer idReservation) {
        Reservation res = reservationRepository.findById(idReservation).orElseThrow(() -> new RuntimeException("Réservation introuvable"));
        if (res.getEspace() == null || res.getSaison() ==null || res.getDureeJours()== null) {
            throw new RuntimeException("Données manquantes (Espace, Saison ou Durée) pour la réservation " + idReservation);
        }
        BigDecimal prixBase = res.getEspace().getPrixBase();
        BigDecimal coeff =res.getSaison().getCoeffPrix();
        BigDecimal duree = BigDecimal.valueOf(res.getDureeJours());

        BigDecimal prixTotal =prixBase.multiply(coeff).multiply(duree);
        Prestation prestation = new Prestation();
        prestation.setPrix(prixTotal);
        prestation.setReservation(res);

        Prestation saved = prestationRepository.save(prestation);
        return mapper.convertToDTO(saved);
    }

    @Override
    public PrestationDTO create(PrestationDTO dto) {
        Prestation entity =mapper.convertToEntity(dto);
        return mapper.convertToDTO(prestationRepository.save(entity));
    }

    @Override
    public PrestationDTO update(Integer id, PrestationDTO dto) {
        Prestation entity = prestationRepository.findById(id).orElseThrow(() -> new RuntimeException("Prestation non trouvée"));
        entity.setPrix(dto.getPrix());

        if (dto.getIdReservation() != null) {
            Reservation r = new Reservation();
            r.setIdReservation(dto.getIdReservation());
            entity.setReservation(r);
        }
        if (dto.getIdPaiement() != null) {
            Paiement p = new Paiement();
            p.setIdpaiement(dto.getIdPaiement());
            entity.setPaiement(p);
        }
        return mapper.convertToDTO(prestationRepository.save(entity));
    }

    @Override
    public PrestationDTO getById(Integer id) {
        return mapper.convertToDTO(prestationRepository.findById(id).orElseThrow(() -> new RuntimeException("Prestation non trouvée")));
    }

    @Override
    public List<PrestationDTO> getAll() {
        return mapper.convertToDTOList(prestationRepository.findAll(
                org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "idPrestation")));
    }

    @Override
    public void delete(Integer id) {
        prestationRepository.deleteById(id);
    }
}