package com.example.manager.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_adresse")
    private Long id;

    @Column(nullable = false)
    private String num;

    @Column(nullable = false)
    private String rue;

    @Column(nullable = false)
    private String ville;

    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String pays;

    @OneToMany(mappedBy = "adresse")
    private List<Utilisateur> utilisateurs = new ArrayList<>();

}
