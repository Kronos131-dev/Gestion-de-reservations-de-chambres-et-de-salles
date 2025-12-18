package fr.ulco.filter_notification.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_notification")
    private Long idNotification;

    @Column(name="date_creation", nullable=false)
    private LocalDateTime dateCreation;

    @Column(nullable=false)
    private String contenu;
}