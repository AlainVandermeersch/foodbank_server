package foodbank.it.service.impl;

import foodbank.it.persistence.model.Don;
import foodbank.it.persistence.repository.IDonRepository;
import foodbank.it.service.IDonService;
import foodbank.it.service.SearchDonCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DonServiceImpl implements IDonService{

	private IDonRepository DonRepository;
	private final EntityManager entityManager;
	
	public DonServiceImpl(IDonRepository DonRepository,EntityManager entityManager) {
        this.DonRepository = DonRepository;
        this.entityManager = entityManager;
    }
	

    @Override
    public Don save(Don don) {  

        return DonRepository.save(don);
    }

    @Override
    @Transactional
    public void delete(int idDon) throws Exception {   
    
			DonRepository.deleteByIdDon(idDon);		
	        
    }
   
    @Override 
    public Page<Don> findAll(SearchDonCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Don> donQuery = criteriaBuilder.createQuery(Don.class);
		Root<Don> don = donQuery.from(Don.class);
		List<Predicate> predicates = new ArrayList<>();

		String donateurNom = searchCriteria.getDonateurNom();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer donYear = searchCriteria.getDonYear();
		

		if (donateurNom != null ) {			

			Predicate donateurNomPredicate = criteriaBuilder.like(don.get("donateurNom"), "%" + donateurNom.toLowerCase() + "%");
			predicates.add(donateurNomPredicate);
		}

		
		if (donYear != null) {
			Predicate donYearPredicate = criteriaBuilder.equal( criteriaBuilder.function("YEAR", Integer.class, don.get("date")),
					donYear );
			predicates.add(donYearPredicate);
		}
		
		
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(don.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		

		donQuery.where(predicates.stream().toArray(Predicate[]::new));
		donQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), don, criteriaBuilder));

		TypedQuery<Don> query = entityManager.createQuery(donQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Don.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
		List<Don> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public Optional<Don> findByIdDon(int idDon) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	



