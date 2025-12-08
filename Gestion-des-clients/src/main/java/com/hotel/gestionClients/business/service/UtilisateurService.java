package com.hotel.gestionClients.business.service;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;

import java.util.List;

public interface UtilisateurService {

    List<UtilisateurDTO> getAllClients();
    UtilisateurDTO getClientById(Long id);
    UtilisateurDTO createClient(UtilisateurDTO utilisateurDTO);
    UtilisateurDTO updateClient(Long id, UtilisateurDTO utilisateurDTO);
    void deleteClient(Long id);
}
