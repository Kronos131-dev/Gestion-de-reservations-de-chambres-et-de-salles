package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name="utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_utilisateur")
    private Long idUtilisateur;

    @Column(nullable=false)
    private String nom;

    @Column(nullable=false)
    private String prenom;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String tel;

    @Column(name="date_naissance")
    private LocalDate dateNaissance;
}
