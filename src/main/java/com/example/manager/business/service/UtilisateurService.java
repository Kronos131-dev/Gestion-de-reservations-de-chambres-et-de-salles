package com.example.manager.business.service;
import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.AdresseRepository;
import com.example.manager.persistence.repository.RoleRepository;
import com.example.manager.persistence.repository.UtilisateurRepository;
import com.example.manager.presentation.dto.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurDetails(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public Utilisateur createUtilisateur(UtilisateurDTO dto) {
        Utilisateur utilisateur = new Utilisateur();
        majUtilisateur(utilisateur, dto);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifyUtilisateur(Long id, UtilisateurDTO dto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        majUtilisateur(utilisateur, dto);
        return utilisateurRepository.save(utilisateur);
    }

    private void majUtilisateur(Utilisateur utilisateur, UtilisateurDTO dto) {
        Role role = roleRepository.findById(dto.idRole())
                .orElseThrow(() -> new RuntimeException("Role introuvable"));
        Adresse adresse = adresseRepository.findById(dto.idAdresse())
                .orElseThrow(() -> new RuntimeException("Adresse introuvable"));

        utilisateur.setNom(dto.nom());
        utilisateur.setPrenom(dto.prenom());
        utilisateur.setEmail(dto.email());
        utilisateur.setPassword(dto.password());
        utilisateur.setTel(dto.tel());
        utilisateur.setDateNaissance(dto.dateNaissance());
        utilisateur.setRole(role);
        utilisateur.setAdresse(adresse);
    }
}
