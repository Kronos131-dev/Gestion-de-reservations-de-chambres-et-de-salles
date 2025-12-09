package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="espace")
public class Espace {

    public enum Status { DISPONIBLE, OCCUPE, EN_MAINTENANCE }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_espace")
    private Long idEspace;

    @Column(nullable=false)
    private String description;

    @Column(name="nb_place", nullable=false)
    private Long nbPlaces;

    @Column(name="prix_base", nullable=false)
    private Float prixBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status;

    @ManyToOne
    @JoinColumn(name="id_type", nullable=false)
    private TypeEspace typeEspace;
}


