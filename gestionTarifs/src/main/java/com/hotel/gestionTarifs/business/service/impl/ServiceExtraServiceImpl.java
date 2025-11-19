package com.hotel.gestionTarifs.business.service.impl;

import com.hotel.gestionTarifs.business.dto.ServiceExtraDTO;
import com.hotel.gestionTarifs.business.mapper.ServiceExtraMapper;
import com.hotel.gestionTarifs.business.service.ServiceExtraService;
import com.hotel.gestionTarifs.persistence.entity.ServiceExtra;
import com.hotel.gestionTarifs.persistence.repository.ServiceExtraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceExtraServiceImpl implements ServiceExtraService {

    private final ServiceExtraRepository repository;

    public ServiceExtraServiceImpl(ServiceExtraRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ServiceExtraDTO> getAllServiceExtras() {
        return ServiceExtraMapper.toDTOList(repository.findAll());
    }

    @Override
    public ServiceExtraDTO getServiceExtraById(Integer id) {
        return repository.findById(id)
                .map(ServiceExtraMapper::toDTO)
                .orElseThrow(() ->
                        new IllegalArgumentException("Le service extra avec l'identifiant " + id + " est introuvable.")
                );
    }

    @Override
    public ServiceExtraDTO createServiceExtra(ServiceExtraDTO serviceExtraDTO) {
        ServiceExtra entity = ServiceExtraMapper.toEntity(serviceExtraDTO);
        return ServiceExtraMapper.toDTO(repository.save(entity));
    }

    @Override
    public ServiceExtraDTO updateServiceExtra(Integer id, ServiceExtraDTO serviceExtraDTO) {
        ServiceExtra existing = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Impossible de modifier : service extra introuvable (id=" + id + ").")
                );

        existing.setNom(serviceExtraDTO.getNom());
        existing.setDescription(serviceExtraDTO.getDescription());
        existing.setPrix(serviceExtraDTO.getPrix());

        return ServiceExtraMapper.toDTO(repository.save(existing));
    }

    @Override
    public void deleteServiceExtra(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Suppression impossible : service extra avec id=" + id + " inexistant.");
        }
        repository.deleteById(id);
    }
}
