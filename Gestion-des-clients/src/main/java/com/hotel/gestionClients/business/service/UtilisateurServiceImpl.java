package com.hotel.gestionClients.business.service;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.business.mapper.AdresseMapper;
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

    @Override
    public List<UtilisateurDTO> getAllClients() {
        Role clientRole = roleRepository.findByNom("CLIENT");
        List<Utilisateur> utilisateurs = utilisateurRepository.findByRole(clientRole);
        return UtilisateurMapper.toDtoList(utilisateurs);
    }

    @Override
    public UtilisateurDTO getClientById(Long id) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);
        return utilisateurOpt.map(UtilisateurMapper::toDto).orElse(null);
    }

    @Override
    public UtilisateurDTO createClient(UtilisateurDTO utilisateurDTO) {

        Utilisateur utilisateur = UtilisateurMapper.toEntity(utilisateurDTO);

        if (utilisateurDTO.getPassword() != null && !utilisateurDTO.getPassword().isBlank()) {
            String hashed = passwordEncoder.encode(utilisateurDTO.getPassword());
            utilisateur.setPassword(hashed);
        }

        // Rôle = CLIENT
        Role clientRole = roleRepository.findByNom("CLIENT");
        utilisateur.setRole(clientRole);


        if (utilisateurDTO.getAdresse() != null) {
            Adresse adresse = AdresseMapper.toEntity(utilisateurDTO.getAdresse());

            adresse.setIdAdresse(null);

            boolean adresseVide =
                    (adresse.getNum() == null || adresse.getNum().isBlank()) &&
                            (adresse.getRue() == null || adresse.getRue().isBlank()) &&
                            (adresse.getVille() == null || adresse.getVille().isBlank()) &&
                            (adresse.getCodePostal() == null || adresse.getCodePostal().isBlank()) &&
                            (adresse.getPays() == null || adresse.getPays().isBlank());

            if (!adresseVide) {
                adresse = adresseRepository.save(adresse);
                utilisateur.setAdresse(adresse);
            }
        }

        Utilisateur saved = utilisateurRepository.save(utilisateur);

        return UtilisateurMapper.toDto(saved);
    }

    @Override
    public UtilisateurDTO updateClient(Long id, UtilisateurDTO utilisateurDetailsDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur == null) {
            return null;
        }

        utilisateur.setNom(utilisateurDetailsDTO.getNom());
        utilisateur.setPrenom(utilisateurDetailsDTO.getPrenom());
        utilisateur.setEmail(utilisateurDetailsDTO.getEmail());
        utilisateur.setTel(utilisateurDetailsDTO.getTel());
        utilisateur.setDateNaissance(utilisateurDetailsDTO.getDateNaissance());

        if (utilisateurDetailsDTO.getPassword() != null &&
                !utilisateurDetailsDTO.getPassword().isBlank()) {

            String hashed = passwordEncoder.encode(utilisateurDetailsDTO.getPassword());
            utilisateur.setPassword(hashed);
        }

        if (utilisateurDetailsDTO.getAdresse() != null) {

            Adresse adresse;

            if (utilisateurDetailsDTO.getAdresse().getId() != null) {
                adresse = adresseRepository
                        .findById(utilisateurDetailsDTO.getAdresse().getId())
                        .orElse(new Adresse());
            } else {
                adresse = new Adresse();
            }

            adresse.setNum(utilisateurDetailsDTO.getAdresse().getNum());
            adresse.setRue(utilisateurDetailsDTO.getAdresse().getRue());
            adresse.setVille(utilisateurDetailsDTO.getAdresse().getVille());
            adresse.setCodePostal(utilisateurDetailsDTO.getAdresse().getCodePostal());
            adresse.setPays(utilisateurDetailsDTO.getAdresse().getPays());

            boolean adresseVide =
                    (adresse.getNum() == null || adresse.getNum().isBlank()) &&
                            (adresse.getRue() == null || adresse.getRue().isBlank()) &&
                            (adresse.getVille() == null || adresse.getVille().isBlank()) &&
                            (adresse.getCodePostal() == null || adresse.getCodePostal().isBlank()) &&
                            (adresse.getPays() == null || adresse.getPays().isBlank());

            if (adresseVide) {
                utilisateur.setAdresse(null);
            } else {
                adresse = adresseRepository.save(adresse);
                utilisateur.setAdresse(adresse);
            }
        }

        Utilisateur updated = utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.toDto(updated);
    }

    @Override
    public void deleteClient(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
