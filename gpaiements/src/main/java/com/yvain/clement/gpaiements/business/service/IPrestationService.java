package com.yvain.clement.gpaiements.business.service;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;

import java.math.BigDecimal;
import java.util.List;

public interface IPrestationService {

    PrestationDto getById(Integer id);
    BigDecimal getPiceById(Integer id);
}
