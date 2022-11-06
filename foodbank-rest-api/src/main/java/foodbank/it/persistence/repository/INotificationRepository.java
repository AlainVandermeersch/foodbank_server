package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Notification;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface INotificationRepository extends PagingAndSortingRepository<Notification, Integer>{ 
	Optional<Notification> findByNotificationId(int notificationId);
    
    void deleteByNotificationId(int notificationId);

	
}

