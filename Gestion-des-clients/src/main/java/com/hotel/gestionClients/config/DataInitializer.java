package com.hotel.gestionClients.config;

import com.hotel.gestionClients.persistence.entity.Role;
import com.hotel.gestionClients.persistence.entity.Utilisateur;
import com.hotel.gestionClients.persistence.repository.RoleRepository;
import com.hotel.gestionClients.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Role roleClient = createRoleIfNotFound("CLIENT", "Client de l'hôtel", 1);
        Role roleAdmin = createRoleIfNotFound("ADMIN", "Administrateur", 10);

        Utilisateur monAdmin = utilisateurRepository.findByEmail("admin@hotel.com");

        if (monAdmin == null) {
            Utilisateur admin = new Utilisateur();
            admin.setNom("Admin");
            admin.setPrenom("System");
            admin.setEmail("admin@hotel.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // On crypte bien le MDP
            admin.setTel("0000000000");
            admin.setDateNaissance(new Date());
            admin.setRole(roleAdmin);

            utilisateurRepository.save(admin);
            System.out.println("Mon Administrateur créé : admin@hotel.com / admin123");
        } else {
            System.out.println("Ton admin (admin@hotel.com) existe déjà.");
        }
    }

    private Role createRoleIfNotFound(String nom, String description, Integer niveau) {
        Role role = roleRepository.findByNom(nom);
        if (role == null) {
            role = new Role(nom, description, niveau);
            role = roleRepository.save(role);
        }
        return role;
    }
}