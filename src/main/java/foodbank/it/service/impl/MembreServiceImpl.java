package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.query.QueryUtils;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.repository.IMembreRepository;
import foodbank.it.service.IMembreService;
import foodbank.it.service.SearchMembreCriteria;

@Service
public class MembreServiceImpl implements IMembreService{

	private IMembreRepository MembreRepository;
	private final EntityManager entityManager;
	
	public MembreServiceImpl(IMembreRepository MembreRepository,EntityManager entityManager) {
        this.MembreRepository = MembreRepository;
        this.entityManager = entityManager;
    }
	@Override
    public Optional<Membre> findByBatId(int batId) {
        return MembreRepository.findByBatId(batId);
    }

    @Override
    public Membre save(Membre Membre) {        
        return MembreRepository.save(Membre);
    }

    @Override
    @Transactional
    public void delete(int batId) {
        MembreRepository.deleteByBatId(batId);
        
    }
   
    @Override 
    public Page<Membre> findAll(SearchMembreCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membre> membreQuery = criteriaBuilder.createQuery(Membre.class);
		Root<Membre> membre = membreQuery.from(Membre.class);

		List<Predicate> predicates = new ArrayList<>();

		String searchField = searchCriteria.getSearchField();
		String searchValue = searchCriteria.getSearchValue();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDis = searchCriteria.getLienDis();

		if (searchField != null && searchValue != null && !searchField.isEmpty() && !searchValue.isEmpty()) {
			Path<String> searchFieldPath = membre.get(searchField);
			Expression<String> lowerSearchField = criteriaBuilder.lower(searchFieldPath);

			Predicate searchFieldPredicate = criteriaBuilder.like(lowerSearchField, "%" + searchValue.toLowerCase() + "%");
			predicates.add(searchFieldPredicate);
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(membre.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		if (lienDis != null) {
			Predicate lienDisPredicate = criteriaBuilder.equal(membre.get("lienDis"), lienDis);
			predicates.add(lienDisPredicate);
		}

		membreQuery.where(predicates.stream().toArray(Predicate[]::new));
		membreQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), membre, criteriaBuilder));

		TypedQuery<Membre> query = entityManager.createQuery(membreQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Membre.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Membre> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
}
	
