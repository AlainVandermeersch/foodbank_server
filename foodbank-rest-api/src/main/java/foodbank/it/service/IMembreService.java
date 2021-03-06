package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Membre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMembreService {
	
	Optional<Membre> findByBatId(int batId);
	
	Membre save(Membre membre,boolean booCreateMode) throws Exception;

    Page<Membre> findAll(SearchMembreCriteria searchCriteria, Pageable pageable);	 

    void delete(int batId) throws Exception;

	
}
