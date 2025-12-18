package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.business.mappers.UtilisateurMapper;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UtilisateurService {

    public List<UtilisateurDTO> findUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurMapper::toDTO)
                .toList();
    }

    public UtilisateurDTO findFirstUtilisateur() {
        return UtilisateurMapper.toDTO(utilisateurRepository.findAll().get(0));
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;
}
