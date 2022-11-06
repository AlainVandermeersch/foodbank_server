package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.ClientDependent;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClientDependentRepository extends CrudRepository<ClientDependent, Integer>{
	    Optional<ClientDependent> findByIdDep(int idDep);
	    void deleteByIdDep(int idDep);
	    void deleteByLienMast(int lienMast);
}
