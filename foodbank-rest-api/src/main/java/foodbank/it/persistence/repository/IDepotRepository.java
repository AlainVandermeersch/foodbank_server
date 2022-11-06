package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Depot;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IDepotRepository  extends PagingAndSortingRepository<Depot, String>{
	Optional<Depot> findByIdDepot(String idDepot);
    void deleteByIdDepot(String idDepot);   
}
