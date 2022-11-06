package foodbank.it.service;

import foodbank.it.persistence.model.ClientDependent;

import java.util.Optional;

public interface IClientDependentService {
	
	Optional<ClientDependent> findByIdDep(int idDep);    
    ClientDependent save(ClientDependent clientDependent);
    Iterable<ClientDependent> findAll(SearchClientDependentCriteria searchCriteria);
    void delete(int idDep);
    void deleteByLienMast(int lienMast);
}
