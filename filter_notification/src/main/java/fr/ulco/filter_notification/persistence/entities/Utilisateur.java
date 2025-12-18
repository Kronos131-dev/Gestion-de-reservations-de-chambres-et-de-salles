package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name="recoit",
            joinColumns = @JoinColumn(name="id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name="id_notification")
    )
    private List<Notification> notifications;
}
