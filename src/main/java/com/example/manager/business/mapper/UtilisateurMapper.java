package com.example.manager.business.mapper;

import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.presentation.dto.UtilisateurDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UtilisateurMapper {

    public static UtilisateurDTO toDTO(Utilisateur entity) {
        if (entity == null) return null;

        return new UtilisateurDTO(
                entity.getNom(),
                entity.getPrenom(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getTel(),
                entity.getDateNaissance(),
                entity.getRole() != null ? entity.getRole().getId() : null,
                entity.getAdresse() != null ? entity.getAdresse().getId() : null
        );
    }
    public static Utilisateur toEntity(
            UtilisateurDTO dto,
            Role role,
            Adresse adresse
    ) {
        if (dto == null) return null;

        Utilisateur entity = new Utilisateur();
        entity.setNom(dto.nom());
        entity.setPrenom(dto.prenom());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setTel(dto.tel());
        entity.setDateNaissance(dto.dateNaissance());
        entity.setRole(role);
        entity.setAdresse(adresse);

        return entity;
    }
    public static void updateEntity(Utilisateur entity, UtilisateurDTO dto, Role role, Adresse adresse, PasswordEncoder encoder) {
        entity.setNom(dto.nom());
        entity.setPrenom(dto.prenom());
        entity.setEmail(dto.email());
        entity.setTel(dto.tel());
        entity.setDateNaissance(dto.dateNaissance());

        entity.setRole(role);
        entity.setAdresse(adresse);

        if (dto.password() != null && !dto.password().isEmpty()) {
            entity.setPassword(encoder.encode(dto.password()));
        }
    }

}


