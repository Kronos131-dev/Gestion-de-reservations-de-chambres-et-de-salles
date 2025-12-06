package fr.ulco.filter_notification.business;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.repositories.EspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspaceService {

    public List<Espace> findAll() {
        return espaceRepository.findAll();
    }

    @Autowired
    private EspaceRepository espaceRepository;
}
