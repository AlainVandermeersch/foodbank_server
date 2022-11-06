package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Trip;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface ITripRepository extends PagingAndSortingRepository<Trip, Integer>{
    Optional<Trip> findByTripId(int tripId);
    void deleteByTripId(int tripId);

}
