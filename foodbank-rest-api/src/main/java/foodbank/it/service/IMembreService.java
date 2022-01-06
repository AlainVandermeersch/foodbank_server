package foodbank.it.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.Membre;

public interface IMembreService {
	
	Optional<Membre> findByBatId(int batId);
	
	Membre save(Membre membre,boolean booCreateMode) throws Exception;

    Page<Membre> findAll(SearchMembreCriteria searchCriteria, Pageable pageable);	
    

    void delete(int batId) throws Exception;

	
}
