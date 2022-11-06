package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Donateur;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IDonateurRepository  extends PagingAndSortingRepository<Donateur, Integer>{
	Optional<Donateur> findByDonateurId(int donateurId);
    void deleteByDonateurId(int donateurId);   
}
