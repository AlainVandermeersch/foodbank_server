package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.ClientDependent;

public interface IClientDependentRepository extends CrudRepository<ClientDependent, Integer>{
	    Optional<ClientDependent> findByIdDep(int idDep);
	    void deleteByIdDep(int idDep);
}
