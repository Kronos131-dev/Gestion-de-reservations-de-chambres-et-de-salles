package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="type_espace")
public class TypeEspace {

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
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
    private Long idType;

    @Column(nullable=false)
    private String description;

    @Column(name="nom_espace", nullable=false)
    private String nomEspace;
}
