package fr.ulco.filter_notification.presentation.dto;

import java.time.LocalDateTime;

public record NotificationDTO (
        String contenu,
        LocalDateTime dateCreation
) { }
