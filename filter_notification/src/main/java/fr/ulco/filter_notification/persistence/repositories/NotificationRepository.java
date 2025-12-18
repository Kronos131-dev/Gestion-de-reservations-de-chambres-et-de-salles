package fr.ulco.filter_notification.persistence.repositories;

import fr.ulco.filter_notification.persistence.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
