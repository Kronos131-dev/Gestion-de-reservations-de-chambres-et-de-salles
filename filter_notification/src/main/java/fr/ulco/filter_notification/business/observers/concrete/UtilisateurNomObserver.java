package fr.ulco.filter_notification.business.observers.concrete;

import org.springframework.stereotype.Component;

// Crée une notification pour un utilisateur quand son nom est modifié
@Component  // Permet d'utiliser l'annotation @Autowired
public class UtilisateurNomObserver extends UtilisateurUpdateObserver {

    @Override
    protected String contenu() {
        return "Votre nom a bien été modifié.";
    }
}
