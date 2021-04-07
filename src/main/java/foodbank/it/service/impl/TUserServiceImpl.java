package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import javax.transaction.Transactional;
import foodbank.it.persistence.model.Banque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.ITUserRepository;
import foodbank.it.service.ITUserService;
import foodbank.it.service.SearchTUserCriteria;

@Service
public class TUserServiceImpl implements ITUserService {

    private ITUserRepository TUserRepository;
    private final EntityManager entityManager;

    public TUserServiceImpl(ITUserRepository TUserRepository,EntityManager entityManager) {
        this.TUserRepository = TUserRepository;
        this.entityManager = entityManager;
        
    }

    
    @Override
    public Optional<TUser> findByIdUser(String idUser) {
        return TUserRepository.findByIdUser(idUser);
    }

    @Override
    public TUser save(TUser TUser) {
      /*  if (StringUtils.isEmpty(TUser.getIdUser()())) {
            TUser.setDateCreated(LocalDate.now());
        } */
        return TUserRepository.save(TUser);
    }

    @Override
    @Transactional
    public void delete(String idUser) {
        TUserRepository.deleteByIdUser(idUser);
    }


    @Override 
    public Page<TUser> findAll(SearchTUserCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TUser> tuserQuery = criteriaBuilder.createQuery(TUser.class);
		Root<TUser> tuser = tuserQuery.from(TUser.class);

		List<Predicate> predicates = new ArrayList<>();

		String searchField = searchCriteria.getSearchField();
		String searchValue = searchCriteria.getSearchValue();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer idOrg = searchCriteria.getIdOrg();

		if (searchField != null && searchValue != null && !searchField.isEmpty() && !searchValue.isEmpty()) {
			Path<String> searchFieldPath = tuser.get(searchField);
			Expression<String> lowerSearchField = criteriaBuilder.lower(searchFieldPath);

			Predicate searchFieldPredicate = criteriaBuilder.like(lowerSearchField, "%" + searchValue.toLowerCase() + "%");
			predicates.add(searchFieldPredicate);
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(tuser.get("lienBanque"), idOrg);
			predicates.add(lienBanquePredicate);
		}
		

		if (idOrg != null) {
			Predicate idOrgPredicate = criteriaBuilder.equal(tuser.get("idOrg"), idOrg);
			predicates.add(idOrgPredicate);
		}

		tuserQuery.where(predicates.stream().toArray(Predicate[]::new));
		tuserQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), tuser, criteriaBuilder));

		TypedQuery<TUser> query = entityManager.createQuery(tuserQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(TUser.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<TUser> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
}
