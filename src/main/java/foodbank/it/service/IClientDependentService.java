package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.ClientDependent;
import foodbank.it.service.SearchClientDependentCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientDependentService {
	
	Optional<ClientDependent> findByIdDep(int idDep);    
    ClientDependent save(ClientDependent clientDependent);
    Page<ClientDependent> findAll(SearchClientDependentCriteria searchCriteria, Pageable pageable);
    void delete(int idDep);
}
