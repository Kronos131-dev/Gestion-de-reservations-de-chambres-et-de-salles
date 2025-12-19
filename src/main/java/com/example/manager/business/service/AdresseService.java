package com.example.manager.business.service;

import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.repository.AdresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service métier pour gérer les adresses
@Service
@RequiredArgsConstructor
public class AdresseService {

    // Repository permettant l'accès à la base de données
    @Autowired
    private AdresseRepository adresseRepository;

    // Récupère toutes les adresses
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    // Crée et enregistre une nouvelle adresse
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }
}
