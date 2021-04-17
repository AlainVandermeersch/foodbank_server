package foodbank.it.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.OrgPerso;
import foodbank.it.persistence.repository.IOrgPersoRepository;
import foodbank.it.service.IOrgPersoService;
import foodbank.it.service.SearchOrgPersoCriteria;
@Service

public class OrgPersoServiceImpl implements IOrgPersoService{
	
	private final IOrgPersoRepository OrgPersoRepository;
	private final EntityManager entityManager;
	
	public OrgPersoServiceImpl(IOrgPersoRepository OrgPersoRepository,
			 EntityManager entityManager) {
        this.OrgPersoRepository = OrgPersoRepository;
        this.entityManager = entityManager;
    }

	@Override
	public Optional<OrgPerso> findByOrgPersId(int orgPersId) {
		return this.OrgPersoRepository.findByOrgPersId(orgPersId);
	}

	@Override
	public OrgPerso save(OrgPerso orgPerso) {
		return this.OrgPersoRepository.save(orgPerso);
	}

	@Override
	public Iterable<OrgPerso> findAll(SearchOrgPersoCriteria searchCriteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrgPerso> orgPersoQuery = criteriaBuilder.createQuery(OrgPerso.class);
		Root<OrgPerso> orgPerso = orgPersoQuery.from(OrgPerso.class);

		List<Predicate> predicates = new ArrayList<>();
		Integer lienAsso = searchCriteria.getLienAsso();
		Integer intIsDeleted = searchCriteria.isDeleted();
		Predicate lienAssoPredicate = criteriaBuilder.equal(orgPerso.get("lienAsso"), lienAsso);
			predicates.add(lienAssoPredicate);
	
		
		if (intIsDeleted != null) {
			Predicate lienDeletedPredicate = criteriaBuilder.equal(orgPerso.get("deleted"),intIsDeleted);
			predicates.add(lienDeletedPredicate);
		}

		orgPersoQuery.where(predicates.stream().toArray(Predicate[]::new));		

		TypedQuery<OrgPerso> query = entityManager.createQuery(orgPersoQuery);		

		List<OrgPerso> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@Transactional
	public void deleteByOrgPersId(int orgPersId) {
		this.OrgPersoRepository.deleteByOrgPersId(orgPersId);
		
	}

	@Override
	@Transactional
	public void deleteByLienAsso(int lienAsso) {
		this.OrgPersoRepository.deleteByLienAsso(lienAsso);
		
	}

}
