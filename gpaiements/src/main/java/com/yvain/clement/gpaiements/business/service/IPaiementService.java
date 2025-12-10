package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import java.util.List;

public interface IPaiementService {

    PaiementDto create(PaiementDto dto);
    PaiementDto getById(Integer id);
    List<PaiementDto> getAll();
    void delete(Integer id);
}