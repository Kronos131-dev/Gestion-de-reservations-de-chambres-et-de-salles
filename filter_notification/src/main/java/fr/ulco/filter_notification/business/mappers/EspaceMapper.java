package fr.ulco.filter_notification.business.mappers;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceDTO;

// Espace => EspaceDTO
public class EspaceMapper {

    public static EspaceDTO toDTO(Espace e) {
        return new EspaceDTO(
                e.getIdEspace(),
                e.getNbPlaces(),
                e.getPrixBase(),
                e.getStatus(),
                e.getTypeEspace().getIdType(),
                e.getTypeEspace().getNomEspace()
        );
    }
}
