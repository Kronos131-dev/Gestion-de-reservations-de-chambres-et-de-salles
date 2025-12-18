package fr.ulco.filter_notification.presentation.dto;

import java.util.List;

public record UtilisateurDTO (
        Long idUtilisateur,
        List<NotificationDTO> notifications
){ }
