package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Donateur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDonateurService {
	
Optional<Donateur> findByDonateurId(int donateurId);
	
	Donateur save(Donateur donateur) ;

    Page<Donateur> findAll(SearchDonateurCriteria searchCriteria, Pageable pageable);	
   

    void delete(int donateurId) throws Exception;

}
