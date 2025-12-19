package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.business.subjects.concrete.UtilisateurUpdateSubject;
import fr.ulco.filter_notification.persistence.entities.Notification;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;
import fr.ulco.filter_notification.presentation.dto.UtilisateurUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UtilisateurServiceTest {

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test   // GET: /api/utilisateurs
    void testGetAll() {
        List<Notification> emptyList = new ArrayList<>();

        Utilisateur u1 = new Utilisateur();
        u1.setIdUtilisateur(1L);
        u1.setNotifications(emptyList);

        Utilisateur u2 = new Utilisateur();
        u2.setIdUtilisateur(2L);
        u2.setNotifications(emptyList);

        when(utilisateurRepository.findAll()).thenReturn(List.of(u1, u2));

        List<UtilisateurDTO> result = utilisateurService.findUtilisateurs();

        assertEquals(2, result.size()); // [u1, u2]

        verify(utilisateurRepository).findAll();
    }

    @Test
    void testGetFirst() {
        List<Notification> emptyList = new ArrayList<>();

        Utilisateur u1 = new Utilisateur();
        u1.setIdUtilisateur(1L);
        u1.setNotifications(emptyList);

        Utilisateur u2 = new Utilisateur();
        u2.setIdUtilisateur(2L);
        u2.setNotifications(emptyList);

        when(utilisateurRepository.findAll()).thenReturn(List.of(u1, u2));

        UtilisateurDTO result = utilisateurService.findFirstUtilisateur();

        assertEquals(1L, result.idUtilisateur()); // u1

        verify(utilisateurRepository).findAll();
    }

    @Test
    void testUpdate() {
        Utilisateur u = new Utilisateur();
        u.setIdUtilisateur(1L);
        u.setNotifications(new ArrayList<>());
        u.setNom("toto");
        u.setEmail("toto@test.com");

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(u));
        when(utilisateurRepository.save(any())).thenReturn(u);

        UtilisateurUpdateDTO result = utilisateurService.updateUtilisateur(1L,
                new UtilisateurUpdateDTO("tata", null));

        assertSame(result.nom(), "tata");
        assertSame(result.email(), "toto@test.com");

        verify(utilisateurRepository).save(u);
    }

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurUpdateSubject updateSubject;
}
