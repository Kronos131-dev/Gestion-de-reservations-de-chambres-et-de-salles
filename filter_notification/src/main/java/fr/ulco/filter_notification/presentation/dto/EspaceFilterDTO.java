package fr.ulco.filter_notification.presentation.dto;

import java.util.List;

public record EspaceFilterDTO (
        Long minNbPlaces,
        Long maxNbPlaces,
        Float minPrixBase,
        Float maxPrixBase,
        List<Long> typeEspaceIds,
        boolean estDisponible
) { }
