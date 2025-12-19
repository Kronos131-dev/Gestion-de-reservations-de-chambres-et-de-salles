package fr.ulco.filter_notification.business.observers;

// Permet de créer une notification pour un utilisateur quand il est modifié
public interface UtilisateurObserver {
    void notify(Long id);
}
