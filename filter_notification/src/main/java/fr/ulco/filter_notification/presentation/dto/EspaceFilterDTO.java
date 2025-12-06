package fr.ulco.filter_notification.presentation.dto;

public record EspaceFilterDTO (
        Long minNbPlaces,
        Long maxNbPlaces,
        Float minPrixBase,
        Float maxPrixBase,
        Long idTypeEspace
) { }
