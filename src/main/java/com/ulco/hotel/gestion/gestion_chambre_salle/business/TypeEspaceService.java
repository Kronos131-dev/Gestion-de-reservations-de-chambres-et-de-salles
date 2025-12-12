package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.EspaceRepository;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeEspaceService {

    @Autowired
    private TypeEspaceRepository typeEspaceRepository;

    @Autowired
    private EspaceRepository espaceRepository;

    public List<TypeEspace> findAll() {
        return typeEspaceRepository.findAll();
    }

    public TypeEspace save(TypeEspace typeEspace) {
        return typeEspaceRepository.save(typeEspace);
    }


    public void deleteById(Long id, boolean deleteEspaces) {

        List<Espace> espaces_idType = espaceRepository.findByTypeEspaceId(id);

        if (!espaces_idType.isEmpty() && !deleteEspaces) {
            String id_espaces = espaces_idType.stream()
                    .map(espace -> espace.getid_espace().toString())
                    .collect(Collectors.joining(" - "));

            throw new RuntimeException("Ce type d'espace est utilisé par " + espaces_idType.size() +
                    " espace(s) : " + id_espaces + ". Souhaitez-vous les supprimer aussi ?");
        }


        if (!espaces_idType.isEmpty() && deleteEspaces) {
            espaceRepository.deleteAll(espaces_idType);
        }

        if (typeEspaceRepository.existsById(id)) {
            typeEspaceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Type d'espace non trouvé avec l'id: " + id);
        }
    }

    public List<Espace> getEspacesAssocies(Long idType) {
        return espaceRepository.findByTypeEspaceId(idType);
    }

    public TypeEspace update(Long id, TypeEspace typeEspaceModif){
        return typeEspaceRepository.findById(id)
                .map(typeEspace -> {
                    typeEspace.setNom_espace(typeEspaceModif.getNom_espace());
                    typeEspace.setDescription(typeEspaceModif.getDescription());
                    return typeEspaceRepository.save(typeEspace);
                })
                .orElseThrow(() -> new RuntimeException("Type d'espace non trouvé"));
    }
}

