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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Don;
import foodbank.it.persistence.model.AuditAssoc;

import foodbank.it.persistence.repository.IAuditAssocRepository;
import foodbank.it.service.IAuditAssocService;
import foodbank.it.service.SearchAuditAssocCriteria;


@Service
public class AuditAssocServiceImpl implements IAuditAssocService{

	private IAuditAssocRepository AuditAssocRepository;
	private final EntityManager entityManager;
	
	public AuditAssocServiceImpl(IAuditAssocRepository AuditAssocRepository,EntityManager entityManager) {
        this.AuditAssocRepository = AuditAssocRepository;
        this.entityManager = entityManager;
    }
	

    @Override
    public AuditAssoc save(AuditAssoc auditAssoc) {  
    	
    	
        return AuditAssocRepository.save(auditAssoc);
    }

    @Override
    @Transactional
    public void delete(int auditId) throws Exception { 
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Don> don = totalCriteriaQuery.from(Don.class);
		List<Predicate> predicates = new ArrayList<>();
		Predicate auditAssocPredicate = criteriaBuilder.equal(don.get("auditId"), auditId);
		predicates.add(auditAssocPredicate);
	
		System.out.printf("\nChecking Don References to AuditAssoc with id: %d", auditId);
		
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(don));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		Long countResult = countQuery.getSingleResult();
		

		if (countResult > 0) {
			String errorMsg = String.format("There are %d Dons with AuditAssoc id %d",countResult, auditId);		
			throw new Exception(errorMsg);
		}
		else {
    
			AuditAssocRepository.deleteById(auditId);	
		}
	        
    }
   
    @Override 
    public Page<AuditAssoc> findAll(SearchAuditAssocCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuditAssoc> auditAssocQuery = criteriaBuilder.createQuery(AuditAssoc.class);
		Root<AuditAssoc> auditAssoc = auditAssocQuery.from(AuditAssoc.class);
		List<Predicate> predicates = new ArrayList<>();

		
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDep = searchCriteria.getLienDep();
		
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(auditAssoc.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		
		if (lienDep != null) {
			Predicate lienDepPredicate = criteriaBuilder.equal(auditAssoc.get("lienDep"), lienDep);
			predicates.add(lienDepPredicate);
		}
		
		auditAssocQuery.where(predicates.stream().toArray(Predicate[]::new));
		auditAssocQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), auditAssoc, criteriaBuilder));

		TypedQuery<AuditAssoc> query = entityManager.createQuery(auditAssocQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(AuditAssoc.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
		List<AuditAssoc> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public Optional<AuditAssoc> findByAuditId(int auditId) {
		return AuditAssocRepository.findById(auditId);
	}
	
}
	




