package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.OrgPerso;

public interface IOrgPersoRepository extends CrudRepository<OrgPerso, Integer>{
	Optional<OrgPerso> findByOrgPersId(int orgPersId);
    void deleteByOrgPersId(int orgPersId);
    void deleteByLienAsso(int lienAsso);

}
