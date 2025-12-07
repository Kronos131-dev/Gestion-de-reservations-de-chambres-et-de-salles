package fr.ulco.filter_notification.persistence.specifications;

import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EspaceSpecifications {

    public static Specification<Espace> filter(EspaceFilterDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.minNbPlaces() != null)
                predicates.add(builder.greaterThanOrEqualTo(root.get("nbPlaces"), filter.minNbPlaces()));
            if (filter.maxNbPlaces() != null)
                predicates.add(builder.lessThanOrEqualTo(root.get("nbPlaces"), filter.maxNbPlaces()));
            if (filter.minPrixBase() != null)
                predicates.add(builder.greaterThanOrEqualTo(root.get("prixBase"), filter.minPrixBase()));
            if (filter.maxPrixBase() != null)
                predicates.add(builder.lessThanOrEqualTo(root.get("prixBase"), filter.maxPrixBase()));
            if (filter.typeEspaceIds() != null)
                predicates.add(root.get("typeEspace").get("idType").in(filter.typeEspaceIds()));
            if (filter.estDisponible())
                predicates.add(builder.equal(root.get("status"), Espace.Status.DISPONIBLE));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
