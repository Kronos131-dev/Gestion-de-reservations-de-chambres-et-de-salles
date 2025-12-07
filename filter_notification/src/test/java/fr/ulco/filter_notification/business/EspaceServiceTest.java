package fr.ulco.filter_notification.business;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.repositories.EspaceRepository;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EspaceServiceTest {

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test   // GET: /api/espaces
    void testGetAll() {
        Espace e = new Espace();
        e.setIdEspace(1L);

        when(espaceRepository.findAll()).thenReturn(List.of(e));

        List<Espace> result = espaceService.findAll();

        assertEquals(1, result.size());

        verify(espaceRepository).findAll();
    }

    @Test   // GET: /api/espaces/filter avec body contenant minNbPlaces = 4
    void testGetFiltered() {
        Espace e1 = new Espace();
        e1.setIdEspace(1L);
        e1.setNbPlaces(5L);

        Espace e2 = new Espace();
        e2.setIdEspace(2L);
        e2.setNbPlaces(3L);

        EspaceFilterDTO filter = new EspaceFilterDTO(
                4L,
                null,
                null,
                null,
                null);

        when(espaceRepository.findAll(any(Specification.class))).thenReturn(List.of(e1));

        List<Espace> result = espaceService.findFiltered(filter);

        assertEquals(1, result.size());
        assertTrue(result.contains(e1));
        assertFalse(result.contains(e2));

        verify(espaceRepository).findAll(any(Specification.class));
    }

    @InjectMocks
    private EspaceService espaceService;

    @Mock
    private EspaceRepository espaceRepository;
}
