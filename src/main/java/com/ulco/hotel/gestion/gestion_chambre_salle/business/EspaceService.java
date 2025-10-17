package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.EspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspaceService {

    @Autowired
    private EspaceRepository espaceRepository;

    public List<Espace> findAll() {
        return espaceRepository.findAll();
    }

    public Espace save(Espace espace) {
        return espaceRepository.save(espace);
    }

    public void deleteById(Long id) {
        espaceRepository.deleteById(id);
    }
}