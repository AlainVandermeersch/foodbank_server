package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Depot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepotService {
	Optional<Depot> findByIdDepot(String idDepot);
	
	Depot save(Depot Depot);

	Page<Depot> findAll(SearchDepotCriteria searchCriteria, Pageable pageable);

    void delete(String idDepot);


}
