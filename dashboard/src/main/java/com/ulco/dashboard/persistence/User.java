package com.ulco.dashboard.persistence;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "utilisateur", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long id_utilisateur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date date_naissance;

    @ManyToOne
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    // getters
    public Long getIdUtilisateur() { return id_utilisateur; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTel() { return tel; }
    public Date getDate_naissance() { return date_naissance; }
    public Adresse getAdresse() { return adresse; }
    public Role getRole() { return role; }
}
