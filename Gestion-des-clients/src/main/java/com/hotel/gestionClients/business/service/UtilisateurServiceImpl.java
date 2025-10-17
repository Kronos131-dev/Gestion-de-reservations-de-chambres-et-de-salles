package com.hotel.gestionClients.business.service;

import com.hotel.gestionClients.persistence.entity.Utilisateur;
import com.hotel.gestionClients.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> getAllClients() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur getClientById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.orElse(null);
    }

    @Override
    public Utilisateur createClient(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateClient(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur != null) {
            utilisateur.setNom(utilisateurDetails.getNom());
            utilisateur.setPrenom(utilisateurDetails.getPrenom());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setPassword(utilisateurDetails.getPassword());
            utilisateur.setTel(utilisateurDetails.getTel());
            utilisateur.setDateNaissance(utilisateurDetails.getDateNaissance());
            utilisateur.setAdresse(utilisateurDetails.getAdresse());
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
