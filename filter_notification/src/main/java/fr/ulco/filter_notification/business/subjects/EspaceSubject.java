package fr.ulco.filter_notification.business.subjects;

import fr.ulco.filter_notification.persistence.entities.Espace;

// Permet de notifier des observers quand un espace est modifié
public interface EspaceSubject {
    void notifyObservers(Espace e);
}
