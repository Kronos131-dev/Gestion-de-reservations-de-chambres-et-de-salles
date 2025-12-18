package fr.ulco.filter_notification.business.observers;

import fr.ulco.filter_notification.persistence.entities.Espace;

// Permet de créer une notification pour les utilisateurs donnés quand un espace est modifié
public interface EspaceObserver {
    void notify(Espace e);
}
