package fr.ulco.filter_notification.business.mappers;

import fr.ulco.filter_notification.persistence.entities.Notification;
import fr.ulco.filter_notification.presentation.dto.NotificationDTO;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification n) {
        return new NotificationDTO(
                n.getContenu(),
                n.getDateCreation()
        );
    }
}
