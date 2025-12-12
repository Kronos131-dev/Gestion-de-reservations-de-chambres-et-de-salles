package com.hotel.gestionClients.config;

import com.hotel.gestionClients.persistence.entity.Utilisateur;
import com.hotel.gestionClients.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur inconnu : " + email);
        }

        return new User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().getNom()))
        );
    }
}