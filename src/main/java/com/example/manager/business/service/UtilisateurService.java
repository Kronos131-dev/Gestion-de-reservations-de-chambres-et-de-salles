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

    public Utilisateur createUser(UtilisateurDTO dto) {
        Role role = roleRepository.findById(dto.idRole())
                .orElseThrow(() -> new RuntimeException("Role introuvable"));
        Adresse adresse = adresseRepository.findById(dto.idAdresse())
                .orElseThrow(() -> new RuntimeException("Adresse introuvable"));

        Utilisateur u = new Utilisateur();
        u.setNom(dto.nom());
        u.setPrenom(dto.prenom());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setTel(dto.tel());
        u.setDateNaissance(dto.dateNaissance());
        u.setRole(role);
        u.setAdresse(adresse);

        return utilisateurRepository.save(u);
    }
}
