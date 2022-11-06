package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.OrgPerso;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IOrgPersoRepository extends CrudRepository<OrgPerso, Integer>{
	Optional<OrgPerso> findByOrgPersId(int orgPersId);
    void deleteByOrgPersId(int orgPersId);
    void deleteByLienAsso(int lienAsso);

}
