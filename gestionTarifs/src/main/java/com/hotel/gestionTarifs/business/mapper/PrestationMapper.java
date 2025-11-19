package com.hotel.gestionTarifs.business.mapper;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.persistence.entity.Paiement;
import com.hotel.gestionTarifs.persistence.entity.Prestation;
import com.hotel.gestionTarifs.persistence.entity.Reservation;

import java.util.ArrayList;
import java.util.List;

public class PrestationMapper {

    // Singleton
    private static PrestationMapper instance;

    private PrestationMapper() {}

    public static PrestationMapper getInstance() {
        if(instance==null)
        { instance = new PrestationMapper(); }
        return instance;
    }

    // DTO TO ENTITY
    public Prestation convertToEntity(PrestationDTO dto) {
        if (dto == null) return null;
        Prestation prestation = new Prestation();
        prestation.setIdPrestation(dto.getIdPrestation());
        prestation.setPrix(dto.getPrix());

        if (dto.getIdReservation() != null) {
            Reservation r =new Reservation();
            r.setIdReservation(dto.getIdReservation());
            prestation.setReservation(r); }

        if (dto.getIdPaiement() != null) {
            Paiement p = new Paiement();
            p.setIdpaiement(dto.getIdPaiement());
            prestation.setPaiement(p); }

        return prestation;
    }
    // ENTITY TO DTO
    public PrestationDTO convertToDTO(Prestation entity) {
        if (entity == null) return null;
        PrestationDTO dto = new PrestationDTO();
        dto.setIdPrestation(entity.getIdPrestation());
        dto.setPrix(entity.getPrix());
        if (entity.getReservation() != null)
            dto.setIdReservation(entity.getReservation().getIdReservation());

        if (entity.getPaiement() !=null)
            dto.setIdPaiement(entity.getPaiement().getIdpaiement());
        return dto;
    }

    //LIST ENTITY TO LIST DTO
    public List<PrestationDTO> convertToDTOList(List<Prestation> entities) {
        List<PrestationDTO> dtos = new ArrayList<>();
        if (entities == null) return dtos;
        for (Prestation p : entities) {
            dtos.add(convertToDTO(p));
        }
        return dtos;
    }

    //List DTO TO LIST ENTITY
    public List<Prestation> convertToEntityList(List<PrestationDTO> dtos) {
        List<Prestation> entities = new ArrayList<>();
        if (dtos == null) return entities;
        for (PrestationDTO dto : dtos) {
            entities.add(convertToEntity(dto));}
        return entities; }
}
