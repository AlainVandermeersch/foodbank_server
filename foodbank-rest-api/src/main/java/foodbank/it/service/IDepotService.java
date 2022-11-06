package foodbank.it.service;

import foodbank.it.persistence.model.Depot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IDepotService {
	Optional<Depot> findByIdDepot(String idDepot);
	
	Depot save(Depot Depot, Boolean sync) throws Exception;

	Page<Depot> findAll(SearchDepotCriteria searchCriteria, Pageable pageable);

    void delete(String idDepot);
	String getAnomalies(Depot Depot);


}
