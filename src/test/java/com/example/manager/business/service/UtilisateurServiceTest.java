package com.example.manager.business.service;

import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.AdresseRepository;
import com.example.manager.persistence.repository.RoleRepository;
import com.example.manager.persistence.repository.UtilisateurRepository;
import com.example.manager.presentation.dto.UtilisateurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AdresseRepository adresseRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //initialise des mocks
    }

    //Test du comportement du GET all
    @Test
    void testGetAllUtilisateurs_shouldReturnList() {
        Utilisateur u1 = new Utilisateur();
        u1.setId(1L);
        Utilisateur u2 = new Utilisateur();
        u2.setId(2L);

        when(utilisateurRepository.findAll()).thenReturn(List.of(u1, u2));

        List<Utilisateur> result = utilisateurService.getAllUtilisateurs();

        assertEquals(2, result.size());
        assertTrue(result.contains(u1));
        assertTrue(result.contains(u2));
    }
    //Test du comportement du GET id en cas de succès
    @Test
    void TestGetUtilisateurDetails_existingUser_shouldReturnUser() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(user));

        Utilisateur result = utilisateurService.getUtilisateurDetails(1L);

        assertEquals(1L, result.getId());
    }

    //Test du comportement du GET id en cas d'echec
    @Test
    void testGetUtilisateurDetails_nonExistingUser_shouldThrow404() {
        when(utilisateurRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> utilisateurService.getUtilisateurDetails(999L));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    //Test du comportement du POST
    @Test
    void testCreateUtilisateur_success() {
        Role role = new Role();
        role.setId(1L);
        role.setNom("CLIENT");

        Adresse adresse = new Adresse();
        adresse.setId(1L);

        UtilisateurDTO dto = new UtilisateurDTO(
                "Nom", "Prenom", "email@test.com", "pwd",
                "0123456789", LocalDate.of(1990, 1, 1),
                1L, 1L
        );

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));
        when(passwordEncoder.encode("pwd")).thenReturn("encodedPwd");
        when(utilisateurRepository.save(any(Utilisateur.class))).thenAnswer(i -> i.getArgument(0));

        UtilisateurDTO created = utilisateurService.createUtilisateur(dto);

        assertEquals("Nom", created.nom());
        assertEquals("Prenom", created.prenom());
        assertEquals("encodedPwd", created.password());
        assertEquals(role.getId(), created.idRole());
        assertEquals(adresse.getId(), created.idAdresse());
        verify(utilisateurRepository).save(any(Utilisateur.class));
    }
    //Test du comportement du PUT sur un utilisateur existant
    @Test
    void testUpdateUtilisateur_Existing_shouldUpdate() {

        Utilisateur existingUser = new Utilisateur();
        existingUser.setId(1L);

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Adresse adresse = new Adresse();
        adresse.setId(1L);
        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));

        when(passwordEncoder.encode("pwd")).thenReturn("encoded");

        when(utilisateurRepository.save(any())).thenReturn(existingUser);

        UtilisateurDTO dto = new UtilisateurDTO(
                "NomModifie", "PrenomModifie", "email@test.com", "pwd",
                "0123456789", LocalDate.of(1990, 1, 1),
                1L, 1L
        );

        UtilisateurDTO updated = utilisateurService.modifyUtilisateur(1L, dto);
        verify(utilisateurRepository).save(existingUser);

        assertEquals("NomModifie", updated.nom());
        assertEquals("PrenomModifie", updated.prenom());
        assertEquals(role.getId(), updated.idRole());
        assertEquals(adresse.getId(), updated.idAdresse());
    }

    //Test du comportement du PUT sur un utilisateur inexistant
    @Test
    void testUpdateUtilisateur_NotFound_shouldThrow() {
        when(utilisateurRepository.findById(999L)).thenReturn(Optional.empty());

        UtilisateurDTO dto = new UtilisateurDTO(
                "Nom", "Prenom", "email@test.com", "pwd",
                "0123456789", LocalDate.of(1990, 1, 1),
                null, null
        );

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> utilisateurService.modifyUtilisateur(999L, dto));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    //Test du comportement du DELETE sur un utilisateur inexistant
    @Test
    void testDeleteUtilisateur_NotFound_shouldThrow() {
        when(utilisateurRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> utilisateurService.deleteUtilisateur(999L));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    //Test du comportement du DELETE sur un utilisateur existant
    @Test
    void testDeleteUtilisateur_success() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(user));

        utilisateurService.deleteUtilisateur(1L);

        verify(utilisateurRepository).delete(user);
    }
}
