package fr.ulco.filter_notification.business.mappers;

import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;

public class UtilisateurMapper {

    public static UtilisateurDTO toDTO(Utilisateur u) {
        return new UtilisateurDTO(
                u.getIdUtilisateur(),
                u.getNotifications()
                        .stream()
                        .map(NotificationMapper::toDTO)
                        .toList()
        );
    }
}
