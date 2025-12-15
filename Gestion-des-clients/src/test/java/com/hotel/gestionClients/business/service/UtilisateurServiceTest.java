package com.hotel.gestionClients.business.service;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.business.mapper.UtilisateurMapper;
import com.hotel.gestionClients.persistence.entity.Role;
import com.hotel.gestionClients.persistence.entity.Utilisateur;
import com.hotel.gestionClients.persistence.repository.RoleRepository;
import com.hotel.gestionClients.persistence.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Test
    void createClient_DoitCrypterMotDePasse_EtSauvegarder() {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setEmail("nouveau@test.com");
        dto.setPassword("secret123");

        Utilisateur entity = new Utilisateur();
        entity.setEmail("nouveau@test.com");

        when(utilisateurMapper.toEntity(dto)).thenReturn(entity);

        when(passwordEncoder.encode("secret123")).thenReturn("CRYPTED_SECRET");

        when(roleRepository.findByNom("CLIENT")).thenReturn(new Role());

        when(utilisateurRepository.save(entity)).thenReturn(entity);

        when(utilisateurMapper.toDto(entity)).thenReturn(dto);

        UtilisateurDTO result = utilisateurService.createClient(dto);

        assertEquals("CRYPTED_SECRET", entity.getPassword());

        verify(utilisateurRepository, times(1)).save(entity);
    }

    @Test
    void deleteClient_DoitAppelerDeleteById() {
        Long idToDelete = 1L;

        utilisateurService.deleteClient(idToDelete);

        verify(utilisateurRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void updateClient_DoitMettreAJourInfos() {
        Long id = 1L;
        Utilisateur existingUser = new Utilisateur();

        existingUser.setIdUtilisateur(id);
        existingUser.setNom("AncienNom");

        UtilisateurDTO updateDto = new UtilisateurDTO();
        updateDto.setNom("NouveauNom");
        updateDto.setPrenom("NouveauPrenom");

        when(utilisateurRepository.findById(id)).thenReturn(Optional.of(existingUser));

        when(utilisateurRepository.save(existingUser)).thenReturn(existingUser);

        when(utilisateurMapper.toDto(existingUser)).thenReturn(updateDto);

        utilisateurService.updateClient(id, updateDto);

        assertEquals("NouveauNom", existingUser.getNom());
        assertEquals("NouveauPrenom", existingUser.getPrenom());

        verify(utilisateurRepository, times(1)).save(existingUser);
    }
    @Test
    void updateClient_RetourneNull_SiIdNexistePas() {
        Long idInconnu = 9999L;
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setNom("Test");

        when(utilisateurRepository.findById(idInconnu)).thenReturn(Optional.empty());

        UtilisateurDTO result = utilisateurService.updateClient(idInconnu, dto);

        assertNull(result);

        verify(utilisateurRepository, never()).save(any());
    }
}