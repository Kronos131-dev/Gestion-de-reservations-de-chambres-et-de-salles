package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.EspaceRepository;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspaceRepository;

public class EspaceService {
    private final EspaceRepository espaceRepository;
    private final TypeEspaceRepository typeEspaceRepository;

    public EspaceService() {
        this.espaceRepository = new EspaceRepository();
        this.typeEspaceRepository = new TypeEspaceRepository();
    }
}
