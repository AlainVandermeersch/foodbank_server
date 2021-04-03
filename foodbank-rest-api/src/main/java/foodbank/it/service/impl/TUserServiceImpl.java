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

import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.ITUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
// backed out import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public TUser save(TUser tuser,  boolean booCreateMode) throws Exception {
    	// backed Out   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();    	
    	if (booCreateMode == true) {    		
    		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        	Root<TUser> existingTUser = totalCriteriaQuery.from(TUser.class);
    		List<Predicate> predicates = new ArrayList<>();
    		Predicate idUserPredicate = criteriaBuilder.equal(existingTUser.get("idUser"), tuser.getIdUser());
    		predicates.add(idUserPredicate);
    	
    		// System.out.printf("\nChecking If User exists with userId: %s", tuser.getIdUser());
    		
    		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
    		totalCriteriaQuery.select(criteriaBuilder.count(existingTUser));
    		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
    		Long countResult = countQuery.getSingleResult();
    		

    		if (countResult > 0) {
    			String errorMsg = String.format("a user exists with userId: %s", tuser.getIdUser());		
    			throw new Exception(errorMsg);
    		}
    		// Backed Out tuser.setPassword(encoder.encode(tuser.getPassword()));
    	}
        return TUserRepository.save(tuser);
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

		String idUser = searchCriteria.getIdUser();
		String userName = searchCriteria.getUserName();
		String idLanguage = searchCriteria.getIdLanguage();
		String email = searchCriteria.getEmail();
		String rights = searchCriteria.getRights();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer idOrg = searchCriteria.getIdOrg();

		if (idUser != null) {			

			Predicate idUserPredicate = criteriaBuilder.like(tuser.get("idUser"), "%" + idUser.toLowerCase() + "%");
			predicates.add(idUserPredicate);
		}
		if (userName != null) {			

			Predicate userNamePredicate = criteriaBuilder.like(tuser.get("userName"), "%" + userName.toLowerCase() + "%");
			predicates.add(userNamePredicate);
		}
		if (idLanguage != null) {			

			Predicate idLanguagePredicate = criteriaBuilder.like(tuser.get("idLanguage"), "%" + idLanguage.toLowerCase() + "%");
			predicates.add(idLanguagePredicate);
		}
		if (email != null) {			

			Predicate emailPredicate = criteriaBuilder.like(tuser.get("email"), "%" + email.toLowerCase() + "%");
			predicates.add(emailPredicate);
		}
		if (rights != null) {			

			Predicate rightsPredicate = criteriaBuilder.equal(tuser.get("rights"), rights);
			predicates.add(rightsPredicate);
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(tuser.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		if (idOrg != null) {
			Predicate idOrgPredicate = criteriaBuilder.equal(tuser.get("idOrg"), idOrg);
			predicates.add(idOrgPredicate);
		}
		Predicate lienActifPredicate = criteriaBuilder.equal(tuser.get("actif"),1);
		predicates.add(lienActifPredicate);

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
