package com.ulco.dashboard.business;

import com.ulco.dashboard.persistence.Espace;
import com.ulco.dashboard.persistence.EspaceRepository;
import com.ulco.dashboard.persistence.TypeEspace;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphBusiness {
    private final EspaceRepository espaceRepository;

    public GraphBusiness(EspaceRepository repo) {
        this.espaceRepository = repo;
    }

    public List<Espace.Status> getStatusAllRoom(){
        return espaceRepository.getAllStatus();
    }

    public List<Float>  getAllEspacesPrices(){
        return espaceRepository.getAllEspacesPrices();
    }

    public List<Float> getAllEspacesPlaces(){return espaceRepository.getAllEspacesPlaces();}

    public List<Object[]>  getAllTypeEspacesAndCount(){return espaceRepository.getAllEspacesTypesAndCount();}

}
