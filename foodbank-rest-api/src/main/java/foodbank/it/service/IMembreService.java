package foodbank.it.service;

import foodbank.it.persistence.model.Membre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMembreService {
	
	Optional<Membre> findByBatId(int batId);
	
	Membre save(Membre membre,boolean booCreateMode) throws Exception;
	Iterable<Membre> findAll(SearchMembreCriteria searchCriteria);
	
    Page<Membre> findPaged(SearchMembreCriteria searchCriteria, Pageable pageable);

    
    void delete(int batId) throws Exception;

}
