package fr.ulco.filter_notification.business.services;

import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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
        Utilisateur u = new Utilisateur();
        u.setIdUtilisateur(1L);

        when(utilisateurRepository.findAll()).thenReturn(List.of(u));

        List<Utilisateur> result = utilisateurService.findUtilisateurs();

        assertEquals(1, result.size());

        verify(utilisateurRepository).findAll();
    }

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;
}
