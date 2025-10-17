package com.example.manager.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_role")
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<Utilisateur> utilisateurs = new ArrayList<>();
}
