package foodbank.it.service;

import foodbank.it.persistence.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITripService {
		Optional<Trip> findByTripId(int tripId);	
		Page<Trip> findAll(SearchTripCriteria searchCriteria, Pageable pageable);	    
		Trip save(Trip Trip) ;
	    void delete(int tripId);	

	}
