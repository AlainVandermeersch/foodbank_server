package foodbank.it.persistence.repository;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Depot;

public interface IDepotRepository  extends PagingAndSortingRepository<Depot, String>{
	Optional<Depot> findByIdDepot(String idDepot);
    void deleteByIdDepot(String idDepot);   
}
