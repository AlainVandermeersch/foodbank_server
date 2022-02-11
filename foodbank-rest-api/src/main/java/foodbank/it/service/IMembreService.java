package foodbank.it.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.Membre;

public interface IMembreService {
	
	Optional<Membre> findByBatId(int batId);
	
	Membre save(Membre membre,boolean booCreateMode) throws Exception;
	Iterable<Membre> findAll();
	
    Page<Membre> findAll(SearchMembreCriteria searchCriteria, Pageable pageable);	
    Iterable<Membre> findByLienBanque(Short lienBanque);
    Iterable<Membre> findByActif(Short actif);
    Iterable<Membre> findByLienBanqueAndActif(Short lienBanque, Short actif);

    void delete(int batId) throws Exception;

	
}
