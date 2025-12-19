package com.example.manager.presentation.dto;

// DTO pour renvoyer la réponse d'authentification au frontend
// Contient le token JWT si la connexion est réussie et un message d'information
public record LoginResponseDTO(String token, String message) {}
