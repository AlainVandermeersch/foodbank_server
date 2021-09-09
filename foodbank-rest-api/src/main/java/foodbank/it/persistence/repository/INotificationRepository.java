package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Notification;

public interface INotificationRepository extends CrudRepository<Notification, Integer>{ 
	Optional<Notification> findByNotificationId(int notificationId);
    
    void deleteByNotificationId(int notificationId);

	
}

