package com.example.manager.business.service;

import com.example.manager.business.mapper.UtilisateurMapper;
import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.AdresseRepository;
import com.example.manager.persistence.repository.RoleRepository;
import com.example.manager.persistence.repository.UtilisateurRepository;
import com.example.manager.presentation.dto.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// Service métier pour gérer les utilisateurs
@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private AdresseRepository adresseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              RoleRepository roleRepository,
                              AdresseRepository adresseRepository,
                              PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.adresseRepository = adresseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Récupère tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupère les détails d'un utilisateur par son id
    public Utilisateur getUtilisateurDetails(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable")
                );
    }

    // Crée un nouvel utilisateur à partir d'un DTO
    public UtilisateurDTO createUtilisateur(UtilisateurDTO dto) {

        // Récupère le rôle et l'adresse depuis la base
        Role role = roleRepository.findById(dto.idRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Role introuvable"));
        Adresse adresse = adresseRepository.findById(dto.idAdresse())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Adresse introuvable"));

        // Crée l'entité et met à jour les champs
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurMapper.updateEntity(utilisateur, dto, role, adresse, passwordEncoder);

        // Sauvegarde en base
        utilisateurRepository.save(utilisateur);

        // Retourne le DTO correspondant
        return UtilisateurMapper.toDTO(utilisateur);
    }

    // Modifie un utilisateur existant
    public UtilisateurDTO modifyUtilisateur(Long id, UtilisateurDTO dto) {

        // Récupère l'utilisateur existant
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable")
                );

        // Récupère éventuellement le rôle si fourni
        Role role = null;
        if (dto.idRole() != null) {
            role = roleRepository.findById(dto.idRole())
                    .orElseThrow(() -> new RuntimeException("Role introuvable"));
        }

        // Récupère éventuellement l'adresse si fournie
        Adresse adresse = null;
        if (dto.idAdresse() != null) {
            adresse = adresseRepository.findById(dto.idAdresse())
                    .orElseThrow(() -> new RuntimeException("Adresse introuvable"));
        }

        // Met à jour l'entité
        UtilisateurMapper.updateEntity(utilisateur, dto, role, adresse, passwordEncoder);

        // Sauvegarde les modifications
        utilisateurRepository.save(utilisateur);

        // Retourne le DTO mis à jour
        return UtilisateurMapper.toDTO(utilisateur);
    }

    // Supprime un utilisateur par son id
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable")
                );
        utilisateurRepository.delete(utilisateur);
    }
}
