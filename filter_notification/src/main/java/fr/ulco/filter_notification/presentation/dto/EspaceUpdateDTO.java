package fr.ulco.filter_notification.presentation.dto;

import fr.ulco.filter_notification.persistence.entities.Espace;

public record EspaceUpdateDTO (
        Espace.Status status
) { }
