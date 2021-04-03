package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.ClientDependent;
import foodbank.it.persistence.repository.IClientDependentRepository;
import foodbank.it.service.IClientDependentService;
import foodbank.it.service.SearchClientDependentCriteria;
@Service
public class ClientDependentServiceImpl implements IClientDependentService{
	
	private final IClientDependentRepository ClientDependentRepository;
	private final EntityManager entityManager;
	
	public ClientDependentServiceImpl(IClientDependentRepository ClientDependentRepository,
			 EntityManager entityManager) {
        this.ClientDependentRepository = ClientDependentRepository;
        this.entityManager = entityManager;
    }

	@Override
	public Optional<ClientDependent> findByIdDep(int idDep) {
		return this.ClientDependentRepository.findByIdDep(idDep);
	}

	@Override
	public ClientDependent save(ClientDependent clientDependent) {
		return this.ClientDependentRepository.save(clientDependent);
	}

	@Override
	public Page<ClientDependent> findAll(SearchClientDependentCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClientDependent> clientDependentQuery = criteriaBuilder.createQuery(ClientDependent.class);
		Root<ClientDependent> clientDependent = clientDependentQuery.from(ClientDependent.class);

		List<Predicate> predicates = new ArrayList<>();
		String searchField = searchCriteria.getSearchField();
		String searchValue = searchCriteria.getSearchValue();

		Integer lienDis = searchCriteria.getLienDis();
		Integer lienMast = searchCriteria.getLienMast();
		Integer intIsActif = searchCriteria.isActif();

		if (searchField != null && searchValue != null && !searchField.isEmpty() && !searchValue.isEmpty()) {
			Path<String> searchFieldPath = clientDependent.get(searchField);
			Expression<String> lowerSearchField = criteriaBuilder.lower(searchFieldPath);

			Predicate searchFieldPredicate = criteriaBuilder.like(lowerSearchField, "%" + searchValue.toLowerCase() + "%");
			predicates.add(searchFieldPredicate);
		}

		if (lienDis != null) {
			Predicate lienDisPredicate = criteriaBuilder.equal(clientDependent.get("lienDis"), lienDis);
			predicates.add(lienDisPredicate);
		}

		if (lienMast != null) {
			Predicate lienMastPredicate = criteriaBuilder.equal(clientDependent.get("lienMast"), lienMast);
			predicates.add(lienMastPredicate);
		}
		
		if (intIsActif != null) {
			boolean booIsActif = false;
			if (intIsActif== 1) booIsActif = true;
			Predicate lienActifPredicate = criteriaBuilder.equal(clientDependent.get("actif"), booIsActif);
			predicates.add(lienActifPredicate);
		}

		clientDependentQuery.where(predicates.stream().toArray(Predicate[]::new));
		clientDependentQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), clientDependent, criteriaBuilder));

		TypedQuery<ClientDependent> query = entityManager.createQuery(clientDependentQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(ClientDependent.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<ClientDependent> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}

	@Override
	@Transactional
	public void delete(int idDep) {
		this.ClientDependentRepository.deleteByIdDep(idDep);
		
	}


}
