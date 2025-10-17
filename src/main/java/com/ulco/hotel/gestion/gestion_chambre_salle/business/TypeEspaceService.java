package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeEspaceService {

    @Autowired
    private TypeEspaceRepository typeEspaceRepository;

    public List<TypeEspace> findAll() {
        return typeEspaceRepository.findAll();
    }

    public TypeEspace save(TypeEspace typeEspace) {
        return typeEspaceRepository.save(typeEspace);
    }


    public void deleteById(Long id) {
        typeEspaceRepository.deleteById(id);
    }
}

