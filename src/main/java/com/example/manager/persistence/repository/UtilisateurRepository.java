package com.example.manager.persistence.repository;

import com.example.manager.persistence.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository pour gérer les utilisateurs en base de données
// Fournit les opérations CRUD grâce à JpaRepository
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Méthode pour rechercher un utilisateur par email
    Optional<Utilisateur> findByEmail(String email);
}
