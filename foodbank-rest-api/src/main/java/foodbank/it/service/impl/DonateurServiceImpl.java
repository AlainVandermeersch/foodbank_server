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

import foodbank.it.persistence.model.Donateur;
import foodbank.it.persistence.repository.IDonateurRepository;
import foodbank.it.service.IDonateurService;
import foodbank.it.service.SearchDonateurCriteria;


@Service
public class DonateurServiceImpl implements IDonateurService{

	private IDonateurRepository DonateurRepository;
	private final EntityManager entityManager;
	
	public DonateurServiceImpl(IDonateurRepository DonateurRepository,EntityManager entityManager) {
        this.DonateurRepository = DonateurRepository;
        this.entityManager = entityManager;
    }
	

    @Override
    public Donateur save(Donateur donateur) {  
    	
    	
        return DonateurRepository.save(donateur);
    }

    @Override
    @Transactional
    public void delete(int donateurId) throws Exception {   
    
			DonateurRepository.deleteByDonateurId(donateurId);		
	        
    }
   
    @Override 
    public Page<Donateur> findAll(SearchDonateurCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Donateur> donateurQuery = criteriaBuilder.createQuery(Donateur.class);
		Root<Donateur> donateur = donateurQuery.from(Donateur.class);
		List<Predicate> predicates = new ArrayList<>();

		String nom = searchCriteria.getNom();
		String prenom = searchCriteria.getPrenom();
		String adresse = searchCriteria.getAdresse();
		String cp = searchCriteria.getCp();
		String city = searchCriteria.getCity();
		Integer lienBanque = searchCriteria.getLienBanque();
		

		if (nom != null ) {			

			Predicate nomPredicate = criteriaBuilder.like(donateur.get("nom"), "%" + nom.toLowerCase() + "%");
			predicates.add(nomPredicate);
		}
		if (prenom != null ) {			

			Predicate prenomPredicate = criteriaBuilder.like(donateur.get("prenom"), "%" + prenom.toLowerCase() + "%");
			predicates.add(prenomPredicate);
		}
		if (adresse != null ) {			

			Predicate adressePredicate = criteriaBuilder.like(donateur.get("adresse"), "%" + adresse.toLowerCase() + "%");
			predicates.add(adressePredicate);
		}
		if (cp != null ) {			

			Predicate cpPredicate = criteriaBuilder.like(donateur.get("cp"), "%" + cp.toLowerCase() + "%");
			predicates.add(cpPredicate);
		}
		if (city != null ) {			

			Predicate cityPredicate = criteriaBuilder.like(donateur.get("city"), "%" + city.toLowerCase() + "%");
			predicates.add(cityPredicate);
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(donateur.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		

		donateurQuery.where(predicates.stream().toArray(Predicate[]::new));
		donateurQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), donateur, criteriaBuilder));

		TypedQuery<Donateur> query = entityManager.createQuery(donateurQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Donateur.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
		List<Donateur> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public Optional<Donateur> findByDonateurId(int donateurId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	

