package com.hotel.gestionTarifs.business.service;

import com.hotel.gestionTarifs.business.dto.ServiceExtraDTO;
import java.util.List;

public interface ServiceExtraService {

    List<ServiceExtraDTO> getAllServiceExtras();

    ServiceExtraDTO getServiceExtraById(Integer id);

    ServiceExtraDTO createServiceExtra(ServiceExtraDTO serviceExtraDTO);

    ServiceExtraDTO updateServiceExtra(Integer id, ServiceExtraDTO serviceExtraDTO);

    void deleteServiceExtra(Integer id);
}
