package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.OrgProgram;

public interface IOrgProgramRepository extends CrudRepository<OrgProgram, Integer>{
	
	Optional<OrgProgram> findByLienDis(int lienDis);
    void deleteByLienDis(int lienDis);

}
