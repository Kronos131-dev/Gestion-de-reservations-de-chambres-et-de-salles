package com.ulco.dashboard.business;

import com.ulco.dashboard.persistence.EspaceRepository;
import org.springframework.stereotype.Service;

@Service
public class GraphBusiness {
    private final EspaceRepository espaceRepository;

    public GraphBusiness(EspaceRepository repo) {
        this.espaceRepository = repo;
    }

    public String getStatusAllRoom(){
        return espaceRepository.getAllStatus().toString();
    }
}
