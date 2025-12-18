package fr.ulco.filter_notification.business.observers.concrete;

import fr.ulco.filter_notification.business.observers.EspaceObserver;
import fr.ulco.filter_notification.business.services.NotificationService;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Crée une notification pour tous les utilisateurs quand un espace devient disponible
@Component  // Permet d'utiliser l'annotation @Autowired
public class EspaceDisponibleObserver implements EspaceObserver {

    @Override
    public void notify(Espace e) {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        String contenu = "L'espace " + e.getIdEspace() + " est désormais disponible.";

        notificationService.createNotification(contenu, utilisateurs);
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private NotificationService notificationService;
}
