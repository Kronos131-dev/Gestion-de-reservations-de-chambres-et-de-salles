package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="espace")
public class Espace {

    public Long getIdEspace() {
        return idEspace;
    }

    public void setIdEspace(Long idEspace) {
        this.idEspace = idEspace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Long nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Float getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(Float prixBase) {
        this.prixBase = prixBase;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TypeEspace getTypeEspace() {
        return typeEspace;
    }

    public void setTypeEspace(TypeEspace typeEspace) {
        this.typeEspace = typeEspace;
    }

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

    private enum Status { DISPONIBLE, OCCUPE, EN_MAINTENANCE }

    @ManyToOne
    @JoinColumn(name="id_type", nullable=false)
    private TypeEspace typeEspace;
}
