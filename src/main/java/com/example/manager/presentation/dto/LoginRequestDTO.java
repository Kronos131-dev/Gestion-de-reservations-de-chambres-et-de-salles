package com.example.manager.presentation.dto;

// DTO pour représenter les données de connexion reçues depuis le frontend
// Contient l'email et le mot de passe de l'utilisateur
public record LoginRequestDTO(String email, String password) {}
