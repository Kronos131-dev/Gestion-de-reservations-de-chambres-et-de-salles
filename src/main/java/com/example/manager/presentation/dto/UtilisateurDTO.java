package com.example.manager.presentation.dto;

import java.time.LocalDate;

public record UtilisateurDTO(
        String nom,
        String prenom,
        String email,
        String password,
        String tel,
        LocalDate dateNaissance,
        Long idRole,
        Long idAdresse
) {}

