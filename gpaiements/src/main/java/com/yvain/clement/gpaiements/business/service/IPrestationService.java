package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import java.util.List;

public interface IPrestationService {

    PrestationDto create(PrestationDto dto);
    PrestationDto getById(Integer id);
    List<PrestationDto> getByReservation(Integer reservationId);

}
