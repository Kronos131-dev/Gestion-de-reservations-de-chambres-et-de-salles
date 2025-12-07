package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.repositories.EspaceRepository;
import fr.ulco.filter_notification.persistence.specifications.EspaceSpecifications;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspaceService {

    public List<Espace> findAll() {
        return espaceRepository.findAll();
    }

    public List<Espace> findFiltered(EspaceFilterDTO filter) {
        return espaceRepository.findAll(EspaceSpecifications.filter(filter));
    }

    @Autowired
    private EspaceRepository espaceRepository;
}
