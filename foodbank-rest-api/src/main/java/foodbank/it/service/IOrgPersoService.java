package foodbank.it.service;

import foodbank.it.persistence.model.OrgPerso;

import java.util.Optional;

public interface IOrgPersoService {
	Optional<OrgPerso> findByOrgPersId(int orgPersId);
	OrgPerso save(OrgPerso orgPerso);
    void deleteByOrgPersId(int orgPersId);
    void deleteByLienAsso(int lienAsso);
    Iterable<OrgPerso> findAll(SearchOrgPersoCriteria searchCriteria); 
}
