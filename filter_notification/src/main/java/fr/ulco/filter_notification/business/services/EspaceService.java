package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.business.mappers.EspaceMapper;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.repositories.EspaceRepository;
import fr.ulco.filter_notification.persistence.specifications.EspaceSpecifications;
import fr.ulco.filter_notification.presentation.dto.EspaceDTO;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import fr.ulco.filter_notification.presentation.dto.EspaceUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EspaceService {

    public List<EspaceDTO> findEspaces(EspaceFilterDTO filter) {
        List<Espace> espaces;

        if (filter == null) espaces = espaceRepository.findAll();
        else espaces = espaceRepository.findAll(EspaceSpecifications.filter(filter));

        return espaces
                .stream()
                .map(EspaceMapper::toDTO)
                .toList();
    }

    public EspaceDTO updateEspaceStatus(Long id, EspaceUpdateDTO dto) {
        Espace espace = espaceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Espace introuvable"));

        espace.setStatus(dto.status());

        espaceRepository.save(espace);

        return EspaceMapper.toDTO(espace);
    }

    @Autowired
    private EspaceRepository espaceRepository;
}
