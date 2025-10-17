package com.hotel.gestionClients.business.service;

import com.hotel.gestionClients.persistence.entity.Utilisateur;
import java.util.List;

public interface UtilisateurService {

    List<Utilisateur> getAllClients();
    Utilisateur getClientById(Long id);
    Utilisateur createClient(Utilisateur utilisateur);
    Utilisateur updateClient(Long id, Utilisateur utilisateurDetails);
    void deleteClient(Long id);
}
