package com.hotel.gestionClients.business.service;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.business.mapper.UtilisateurMapper;
import com.hotel.gestionClients.persistence.entity.Adresse;
import com.hotel.gestionClients.persistence.entity.Role;
import com.hotel.gestionClients.persistence.entity.Utilisateur;
import com.hotel.gestionClients.persistence.repository.AdresseRepository;
import com.hotel.gestionClients.persistence.repository.RoleRepository;
import com.hotel.gestionClients.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    public List<UtilisateurDTO> getAllClients() {
        Role clientRole = roleRepository.findByNom("CLIENT");
        List<Utilisateur> utilisateurs = utilisateurRepository.findByRole(clientRole);
        return utilisateurMapper.toDtoList(utilisateurs);
    }

    @Override
    public UtilisateurDTO getClientById(Long id) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);
        return utilisateurOpt.map(utilisateurMapper::toDto).orElse(null);
    }

    @Override
    public UtilisateurDTO createClient(UtilisateurDTO utilisateurDTO) {

        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);

        if (utilisateurDTO.getPassword() != null && !utilisateurDTO.getPassword().isBlank()) {
            String hashed = passwordEncoder.encode(utilisateurDTO.getPassword());
            utilisateur.setPassword(hashed);
        }

        Role clientRole = roleRepository.findByNom("CLIENT");
        utilisateur.setRole(clientRole);

        if (utilisateur.getAdresse() != null) {
            Adresse adr = utilisateur.getAdresse();
            boolean adresseVide = estAdresseVide(adr);

            if (adresseVide) {
                utilisateur.setAdresse(null);
            }
        }

        Utilisateur saved = utilisateurRepository.save(utilisateur);

        return utilisateurMapper.toDto(saved);
    }

    @Override
    public UtilisateurDTO updateClient(Long id, UtilisateurDTO dto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur == null) {
            return null;
        }

        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setTel(dto.getTel());
        utilisateur.setDateNaissance(dto.getDateNaissance());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            utilisateur.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getAdresse() != null) {
            Adresse adresseEntity = utilisateur.getAdresse();

            if (adresseEntity == null) {
                adresseEntity = new Adresse();
            }

            adresseEntity.setNum(dto.getAdresse().getNum());
            adresseEntity.setRue(dto.getAdresse().getRue());
            adresseEntity.setVille(dto.getAdresse().getVille());
            adresseEntity.setCodePostal(dto.getAdresse().getCodePostal());
            adresseEntity.setPays(dto.getAdresse().getPays());

            if (estAdresseVide(adresseEntity)) {
                utilisateur.setAdresse(null);

            } else {
                utilisateur.setAdresse(adresseEntity);
            }
        }

        Utilisateur updated = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(updated);
    }

    @Override
    public void deleteClient(Long id) {
        utilisateurRepository.deleteById(id);
    }

    private boolean estAdresseVide(Adresse adresse) {
        if (adresse == null) return true;
        return (adresse.getNum() == null || adresse.getNum().isBlank()) &&
                (adresse.getRue() == null || adresse.getRue().isBlank()) &&
                (adresse.getVille() == null || adresse.getVille().isBlank()) &&
                (adresse.getCodePostal() == null || adresse.getCodePostal().isBlank()) &&
                (adresse.getPays() == null || adresse.getPays().isBlank());
    }
}