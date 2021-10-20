package foodbank.it.persistence.repository;
import java.util.Optional;

import foodbank.it.persistence.model.Trip;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ITripRepository extends PagingAndSortingRepository<Trip, Integer>{
    Optional<Trip> findByTripId(int tripId);
    void deleteByTripId(int tripId);

}
