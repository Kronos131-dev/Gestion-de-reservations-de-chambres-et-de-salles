package fr.ulco.filter_notification.persistence.repositories;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, Long>, JpaSpecificationExecutor<Espace> {
}
