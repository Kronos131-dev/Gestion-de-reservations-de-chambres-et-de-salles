package fr.ulco.filter_notification.persistence.repositories;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, Long> {

    @Query("""
            SELECT e FROM Espace e
            WHERE (?1 IS NULL OR ?1 <= e.nbPlaces)
            AND (?2 IS NULL OR ?2 >= e.nbPlaces)
            AND (?3 IS NULL OR ?3 <= e.prixBase)
            AND (?4 IS NULL OR ?4 >= e.prixBase)
            AND (?5 IS NULL OR ?5 = e.typeEspace.idType)
    """)
    List<Espace> findFiltered(
            Long minNbPlaces,
            Long maxNbPlaces,
            Float minPrixBase,
            Float maxPrixBase,
            Long idTypeEspace
    );
}
