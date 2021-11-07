package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Donateur;

public interface IDonateurRepository  extends PagingAndSortingRepository<Donateur, Integer>{
	Optional<Donateur> findByDonateurId(int donateurId);
    void deleteByDonateurId(int donateurId);   
}
