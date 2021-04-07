package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.ClientDependent;

public interface IClientDependentService {
	
	Optional<ClientDependent> findByIdDep(int idDep);    
    ClientDependent save(ClientDependent clientDependent);
    Iterable<ClientDependent> findAll(SearchClientDependentCriteria searchCriteria);
    void delete(int idDep);
}
