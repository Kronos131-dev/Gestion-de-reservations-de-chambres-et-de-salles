package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import java.util.List;

public interface IPaiementStatutService {

    PaiementStatutDto getById(Integer id);
    List<PaiementStatutDto> getAll();

}