package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.persistence.entities.Notification;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    public void createNotification(String contenu, List<Utilisateur> utilisateurs) {
        Notification notification = new Notification();

        notification.setDateCreation(LocalDateTime.now());
        notification.setContenu(contenu);
        notification.setUtilisateurs(utilisateurs);

        for (Utilisateur u : utilisateurs) u.addNotification(notification);

        notificationRepository.save(notification);
    }

    @Autowired
    private NotificationRepository notificationRepository;
}
