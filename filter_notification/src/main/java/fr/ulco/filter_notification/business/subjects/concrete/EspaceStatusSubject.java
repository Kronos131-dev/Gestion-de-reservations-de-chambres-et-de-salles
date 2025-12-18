package fr.ulco.filter_notification.business.subjects.concrete;

import fr.ulco.filter_notification.business.observers.concrete.EspaceDisponibleObserver;
import fr.ulco.filter_notification.business.subjects.EspaceSubject;
import fr.ulco.filter_notification.persistence.entities.Espace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Notifie ses observers quand un espace change de status
@Component  // Permet d'utiliser l'annotation @Autowired
public class EspaceStatusSubject implements EspaceSubject {

    @Override
    public void notifyObservers(Espace e) {
        if (e.getStatus() == Espace.Status.DISPONIBLE) disponibleObserver.notify(e);
    }

    @Autowired
    private EspaceDisponibleObserver disponibleObserver;
}
