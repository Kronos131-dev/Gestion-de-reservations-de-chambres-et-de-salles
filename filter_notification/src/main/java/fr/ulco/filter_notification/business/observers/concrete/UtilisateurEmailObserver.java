package fr.ulco.filter_notification.business.observers.concrete;

import org.springframework.stereotype.Component;

// Crée une notification pour un utilisateur quand son email est modifié
@Component  // Permet d'utiliser l'annotation @Autowired
public class UtilisateurEmailObserver extends UtilisateurUpdateObserver {

    @Override
    protected String contenu() {
        return "Votre email a bien été modifié.";
    }
}
