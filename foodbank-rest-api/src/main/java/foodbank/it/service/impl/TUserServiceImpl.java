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
		String membreNom = searchCriteria.getMembreNom();
		Boolean actif = searchCriteria.getActif();
		Boolean droit1 = searchCriteria.getDroit1();
		Boolean gestMemb = searchCriteria.getGestMemb();
		Boolean gestBen = searchCriteria.getGestBen();
		Boolean gestFead = searchCriteria.getGestFead();
		Boolean gestDon = searchCriteria.getGestDon();
		Integer membreLangue = searchCriteria.getMembreLangue();
		String membreEmail = searchCriteria.getMembreEmail();
		String rights = searchCriteria.getRights();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer idOrg = searchCriteria.getIdOrg();
		Integer lienDepot = searchCriteria.getLienDepot();

		if (idUser != null) {			

			Predicate idUserPredicate = criteriaBuilder.like(tuser.get("idUser"), "%" + idUser.toLowerCase() + "%");
			predicates.add(idUserPredicate);
		}
		if (membreNom != null) {			

			Predicate membreNomPredicate = criteriaBuilder.like(tuser.<String>get("membreNom"), "%" + membreNom.toLowerCase() + "%");
			predicates.add(membreNomPredicate);
		}
				
		if (membreEmail != null) {			

			Predicate emailPredicate = criteriaBuilder.like(tuser.<String>get("membreEmail"), "%" + membreEmail.toLowerCase() + "%");
			predicates.add(emailPredicate);
		}
		if (membreLangue != null) {
			Predicate languePredicate = criteriaBuilder.equal(tuser.<Short>get("membreLangue"), membreLangue);
			predicates.add(languePredicate);
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
			if (idOrg == 0) {
				Predicate idOrgZero = criteriaBuilder.equal(tuser.get("idOrg"), 0);
				Predicate idOrgNull = criteriaBuilder.isNull(tuser.get("idOrg"));
				Predicate idOrgPredicate = criteriaBuilder.or(idOrgZero,idOrgNull);
				predicates.add(idOrgPredicate);
			}
			else {
				
					Predicate idOrgPredicate = criteriaBuilder.equal(tuser.get("idOrg"), idOrg);
					predicates.add(idOrgPredicate);				
			}
		}
		else {
			if (lienDepot == null) {
				System.out.printf("\nExcluding Bank Members");
				// exclude users of bank who have idOrg 0 or null
				Predicate idOrgNotZero = criteriaBuilder.notEqual(tuser.get("idOrg"), 0);
				Predicate idOrgNotNull = criteriaBuilder.isNotNull(tuser.get("idOrg"));
				predicates.add(idOrgNotZero);
				predicates.add(idOrgNotNull);
			}
			else {
				Predicate lienDepotPredicate = criteriaBuilder.equal(tuser.get("lienDepot"),lienDepot);
				predicates.add(lienDepotPredicate);
			}
		}
		if (actif != null) {
			Integer intActive = 0;
			if (actif == true) {
				intActive = 1;
			}
			Predicate isActifPredicate = criteriaBuilder.equal(tuser.get("actif"), intActive);
			predicates.add(isActifPredicate);
		} 
		if (droit1 != null) {
			Integer intDroit1 = 0;
			if (droit1 == true) {
				intDroit1 = 1;
			}
			Predicate isDroit1Predicate = criteriaBuilder.equal(tuser.get("droit1"), intDroit1);
			predicates.add(isDroit1Predicate);
		} 
		if (gestMemb != null) {
			Integer intGestMemb = 0;
			if (gestMemb == true) {
				intGestMemb = 1;
			}
			Predicate isGestMembPredicate = criteriaBuilder.equal(tuser.get("gestMemb"), intGestMemb);
			predicates.add(isGestMembPredicate);
		} 
		if (gestBen != null) {
			Integer intGestBen = 0;
			if (gestBen == true) {
				intGestBen = 1;
			}
			Predicate isGestBenPredicate = criteriaBuilder.equal(tuser.get("gestBen"), intGestBen);
			predicates.add(isGestBenPredicate);
		} 
		if (gestFead != null) {
			Integer intGestFead = 0;
			if (gestFead == true) {
				intGestFead = 1;
			}
			Predicate isGestFeadPredicate = criteriaBuilder.equal(tuser.get("gestFead"), intGestFead);
			predicates.add(isGestFeadPredicate);
		}
		if (gestDon != null) {
			Integer intGestDon = 0;
			if (gestDon == true) {
				intGestDon = 1;
			}
			Predicate isGestDonPredicate = criteriaBuilder.equal(tuser.get("gestDon"), intGestDon);
			predicates.add(isGestDonPredicate);
		} 
		tuserQuery.where(predicates.stream().toArray(Predicate[]::new));
		tuserQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), tuser, criteriaBuilder));

		TypedQuery<TUser> query = entityManager.createQuery(tuserQuery);
        long countResult =  query.getResultList().size(); // todo: delete this expensive statement and replace it by a count query
        // as commented out below if I can make it work with a join
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

	/* CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
	   tuser = totalCriteriaQuery.from(TUser.class);		
	   membre = tuser.join("membreObject");
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(TUser.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult(); */

		List<TUser> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
		
	}
}
