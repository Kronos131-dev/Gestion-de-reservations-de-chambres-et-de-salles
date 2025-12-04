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

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private AdresseRepository adresseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, AdresseRepository adresseRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.adresseRepository = adresseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurDetails(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    }

    public UtilisateurDTO createUtilisateur(UtilisateurDTO dto) {
        Role role = roleRepository.findById(dto.idRole())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Role introuvable"));
        Adresse adresse = adresseRepository.findById(dto.idAdresse())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Adresse introuvable"));

        Utilisateur utilisateur = new Utilisateur();
        UtilisateurMapper.updateEntity(utilisateur, dto, role, adresse, passwordEncoder);

        utilisateurRepository.save(utilisateur);

        return UtilisateurMapper.toDTO(utilisateur);
    }


    public UtilisateurDTO modifyUtilisateur(Long id, UtilisateurDTO dto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
        Role role = null;
        if (dto.idRole() != null) {
            role = roleRepository.findById(dto.idRole())
                    .orElseThrow(() -> new RuntimeException("Role introuvable"));
        }

        Adresse adresse = null;
        if (dto.idAdresse() != null) {
            adresse = adresseRepository.findById(dto.idAdresse())
                    .orElseThrow(() -> new RuntimeException("Adresse introuvable"));
        }

        UtilisateurMapper.updateEntity(utilisateur, dto, role, adresse, passwordEncoder);

        utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.toDTO(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
        utilisateurRepository.delete(utilisateur);
    }

}
