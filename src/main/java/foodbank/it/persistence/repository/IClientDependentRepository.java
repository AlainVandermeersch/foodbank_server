package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.ClientDependent;

public interface IClientDependentRepository extends CrudRepository<ClientDependent, Integer>{
	    Optional<ClientDependent> findByIdDep(int idDep);
	    Iterable<ClientDependent> findByActif(short actif);
	    Iterable<ClientDependent> findByLienMast(int lienMast);
	    void deleteByIdDep(int idDep);
}
