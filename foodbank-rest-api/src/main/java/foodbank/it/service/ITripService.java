package foodbank.it.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.Trip;

public interface ITripService {
		Optional<Trip> findByTripId(int tripId);	
		Page<Trip> findAll(SearchTripCriteria searchCriteria, Pageable pageable);	    
		Trip save(Trip Trip) ;
	    void delete(int tripId);	

	}
