package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="type_espace")
public class TypeEspace {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_type")
    private Long id;

    @Column(nullable=false)
    private String description;

    @Column(name="nom_espace", nullable=false)
    private String nomEspace;
}
