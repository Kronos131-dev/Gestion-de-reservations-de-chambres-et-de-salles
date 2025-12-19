package fr.ulco.filter_notification.business.observers.concrete;

import fr.ulco.filter_notification.business.observers.UtilisateurObserver;
import fr.ulco.filter_notification.business.services.NotificationService;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Crée une notification pour un utilisateur quand il est modifié
public abstract class UtilisateurUpdateObserver implements UtilisateurObserver {

    @Override
    public void notify(Long id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow();

        notificationService.createNotification(contenu(), List.of(u));
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private NotificationService notificationService;

    protected abstract String contenu(); // Changement du texte selon l'attribut modifié
}
