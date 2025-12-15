package fr.ulco.filter_notification.presentation.dto;

import fr.ulco.filter_notification.persistence.entities.Espace;

public record EspaceDTO (
        // Long idEspace,
        Long nbPlaces,
        Float prixBase,
        Espace.Status status,
        Long idTypeEspace,
        String nomEspace
){}
