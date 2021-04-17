package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.OrgPerso;

public interface IOrgPersoService {
	Optional<OrgPerso> findByOrgPersId(int orgPersId);
	OrgPerso save(OrgPerso orgPerso);
    void deleteByOrgPersId(int orgPersId);
    void deleteByLienAsso(int lienAsso);
    Iterable<OrgPerso> findAll(SearchOrgPersoCriteria searchCriteria); 
}
