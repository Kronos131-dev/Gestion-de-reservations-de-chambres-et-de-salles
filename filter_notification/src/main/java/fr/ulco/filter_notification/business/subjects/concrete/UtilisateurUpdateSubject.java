package fr.ulco.filter_notification.business.subjects.concrete;

import fr.ulco.filter_notification.business.observers.concrete.UtilisateurEmailObserver;
import fr.ulco.filter_notification.business.observers.concrete.UtilisateurNomObserver;
import fr.ulco.filter_notification.presentation.dto.UtilisateurUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Notifie ses observers quand un utilisateur est modifié
@Component  // Permet d'utiliser l'annotation @Autowired
public class UtilisateurUpdateSubject {

    public void notifyObservers(Long id, UtilisateurUpdateDTO u) {
            if (u.nom() != null) nomObserver.notify(id);        // le nom est modifié
            if (u.email() != null) emailObserver.notify(id);    // l'email est modifié
    }

    @Autowired
    private UtilisateurEmailObserver emailObserver;

    @Autowired
    private UtilisateurNomObserver nomObserver;
}
