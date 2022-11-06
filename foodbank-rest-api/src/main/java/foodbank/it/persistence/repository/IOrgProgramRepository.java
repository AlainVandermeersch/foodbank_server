package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.OrgProgram;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IOrgProgramRepository extends CrudRepository<OrgProgram, Integer>{
	
	Optional<OrgProgram> findByLienDis(int lienDis);
    void deleteByLienDis(int lienDis);

}
