package com.hotel.gestionTarifs.business.service;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;

import java.util.List;

public interface IPrestationService {

    PrestationDTO create(PrestationDTO dto);

    PrestationDTO update(Integer id,PrestationDTO dto);

    PrestationDTO getById(Integer id);

    List<PrestationDTO> getAll();

    void delete(Integer id);
}
