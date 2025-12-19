package com.example.manager.presentation.dto;

import java.time.LocalDate;

// DTO pour transférer les données d'un utilisateur entre le frontend et le backend
// Contient les informations de base et les références vers le rôle et l'adresse
public record UtilisateurDTO(
        String nom,           // Nom de l'utilisateur
        String prenom,        // Prénom de l'utilisateur
        String email,         // Email unique
        String password,      // Mot de passe (plain text, sera hashé côté backend)
        String tel,           // Numéro de téléphone
        LocalDate dateNaissance, // Date de naissance
        Long idRole,          // Identifiant du rôle associé
        Long idAdresse        // Identifiant de l'adresse associée
) {}
