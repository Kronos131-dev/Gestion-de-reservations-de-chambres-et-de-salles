package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.persistence.entities.Notification;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;
}
