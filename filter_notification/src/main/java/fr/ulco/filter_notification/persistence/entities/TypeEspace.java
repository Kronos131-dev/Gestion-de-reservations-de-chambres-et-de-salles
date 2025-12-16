package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="type_espace")
public class TypeEspace {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_type")
    private Long idType;

    @Column(nullable=false)
    private String description;

    @Column(name="nom_espace", nullable=false)
    private String nomEspace;
}
