package com.hotel.gestionClients.business.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UtilisateurDTO implements Serializable {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    private AdresseDTO adresse;
    private RoleDTO role;

    public UtilisateurDTO() {
        this.adresse = new AdresseDTO();
        this.role = new RoleDTO();
    }

    public UtilisateurDTO(Long id, String nom, String prenom, String email, String password,
                          String tel, Date dateNaissance, AdresseDTO adresse, RoleDTO role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.role = role;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public AdresseDTO getAdresse() { return adresse; }
    public void setAdresse(AdresseDTO adresse) { this.adresse = adresse; }

    public RoleDTO getRole() { return role; }
    public void setRole(RoleDTO role) { this.role = role; }
}
