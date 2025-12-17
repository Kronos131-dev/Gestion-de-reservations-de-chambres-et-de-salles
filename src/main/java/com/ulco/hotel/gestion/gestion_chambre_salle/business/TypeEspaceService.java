package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.EspaceRepository;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public TypeEspace findById(Long id) {
        Optional<TypeEspace> typeOpt = typeEspaceRepository.findById(id);
        return typeOpt.orElseThrow(() ->
                new RuntimeException("Type d'espace non trouvé avec l'ID: " + id)
        );
    }

    public TypeEspace save(TypeEspace typeEspace) {
        return typeEspaceRepository.save(typeEspace);
    }


    public void deleteById(Long id) {
        List<Espace> espacesAssocies = espaceRepository.findByTypeEspaceId(id);

        if (!espacesAssocies.isEmpty()) {
            String idsEspaces = espacesAssocies.stream()
                    .map(espace -> espace.getId_espace().toString())
                    .collect(Collectors.joining(", "));

            throw new RuntimeException("Ce type d'espace est utilisé par " +
                    espacesAssocies.size() + " espace(s) : " + idsEspaces +
                    ". Vous devez d'abord supprimer ces espaces.");
        }
        typeEspaceRepository.deleteById(id);
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

