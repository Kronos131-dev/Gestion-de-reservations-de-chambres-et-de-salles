package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    public List<Utilisateur> findUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;
}
