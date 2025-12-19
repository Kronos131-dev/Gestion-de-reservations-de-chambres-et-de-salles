package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.business.mappers.UtilisateurMapper;
import fr.ulco.filter_notification.business.subjects.concrete.UtilisateurUpdateSubject;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;
import fr.ulco.filter_notification.presentation.dto.UtilisateurUpdateDTO;
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

    public UtilisateurUpdateDTO updateUtilisateur(Long id, UtilisateurUpdateDTO dto) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

        if (dto.email() != null) u.setEmail(dto.email());
        if (dto.nom() != null) u.setNom(dto.nom());

        utilisateurRepository.save(u);

        // On informe qu'il y a un changement sur l'utilisateur
        updateSubject.notifyObservers(id, dto);

        return UtilisateurMapper.toUpdateDTO(u);
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurUpdateSubject updateSubject;
}
