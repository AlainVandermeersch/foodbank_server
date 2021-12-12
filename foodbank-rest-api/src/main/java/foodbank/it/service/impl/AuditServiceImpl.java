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

import foodbank.it.persistence.model.Audit;

import foodbank.it.persistence.repository.IAuditRepository;
import foodbank.it.service.IAuditService;
import foodbank.it.service.SearchAuditCriteria;


@Service
public class AuditServiceImpl implements IAuditService{

	private IAuditRepository AuditRepository;
	private final EntityManager entityManager;
	
	public AuditServiceImpl(IAuditRepository AuditRepository,EntityManager entityManager) {
       this.AuditRepository = AuditRepository;
       this.entityManager = entityManager;
   }
	

   @Override
   public Audit save(Audit audit) {  
   	
   	
       return AuditRepository.save(audit);
   }

     
   @Override 
   public Page<Audit> findAll(SearchAuditCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Audit> auditQuery = criteriaBuilder.createQuery(Audit.class);
		Root<Audit> audit = auditQuery.from(Audit.class);
		List<Predicate> predicates = new ArrayList<>();

		Integer lienBanque = searchCriteria.getLienBanque();
		Integer idDis = searchCriteria.getIdDis();
		String societe = searchCriteria.getSociete();
		String user = searchCriteria.getUser();

		if (societe != null ) {			

			Predicate societePredicate = criteriaBuilder.like(audit.get("societe"), "%" + societe.toLowerCase() + "%");
			predicates.add(societePredicate);
		}
		if (user != null ) {			

			Predicate userPredicate = criteriaBuilder.like(audit.get("user"), "%" + user.toLowerCase() + "%");
			predicates.add(userPredicate);
		}
				

		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(audit.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		if (idDis != null) {
			Predicate idDisPredicate = criteriaBuilder.equal(audit.get("idDis"), idDis);
			predicates.add(idDisPredicate);
		}
		

		

		auditQuery.where(predicates.stream().toArray(Predicate[]::new));
		auditQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), audit, criteriaBuilder));

		TypedQuery<Audit> query = entityManager.createQuery(auditQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Audit.class)));
       TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
       long countResult = countQuery.getSingleResult();
		List<Audit> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public Optional<Audit> findByAuditId(int auditId) {
		return AuditRepository.findByAuditId(auditId);
	}


	@Override
	public void delete(int auditId) throws Exception {
		AuditRepository.deleteByAuditId(auditId);
		
	}
	

}
