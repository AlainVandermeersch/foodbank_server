package foodbank.it.service;

import foodbank.it.persistence.model.Donateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IDonateurService {
	
Optional<Donateur> findByDonateurId(int donateurId);
	
	Donateur save(Donateur donateur) ;

    Page<Donateur> findAll(SearchDonateurCriteria searchCriteria, Pageable pageable);	
   

    void delete(int donateurId) throws Exception;

}
