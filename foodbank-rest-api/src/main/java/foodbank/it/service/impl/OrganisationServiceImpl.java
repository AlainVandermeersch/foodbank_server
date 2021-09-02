package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;
import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.repository.IOrganisationRepository;
import foodbank.it.service.IOrganisationService;
import foodbank.it.service.SearchOrganisationCriteria;
import foodbank.it.service.SearchOrganisationSummariesCriteria;

import foodbank.it.web.dto.OrgMemberReportDto;
@Service
public class OrganisationServiceImpl implements IOrganisationService{

	private IOrganisationRepository OrganisationRepository;
	private final EntityManager entityManager;
	
	public OrganisationServiceImpl(IOrganisationRepository OrganisationRepository,
			EntityManager entityManager) {
        this.OrganisationRepository = OrganisationRepository;
        this.entityManager = entityManager;
    }
	@Override
    public Optional<Organisation> findByIdDis(int idDis) {
        return OrganisationRepository.findByIdDis(idDis);
    }

    @Override
    public Organisation save(Organisation Organisation) {        
        return OrganisationRepository.save(Organisation);
    }

    @Override
    @Transactional
    public void delete (int idDis)throws Exception {
    	
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Membre> membre = totalCriteriaQuery.from(Membre.class);
		List<Predicate> predicates = new ArrayList<>();
		Predicate lienDisPredicate = criteriaBuilder.equal(membre.get("lienDis"), idDis);
		predicates.add(lienDisPredicate);
	
		System.out.printf("\nChecking Membre References to Organisation with id: %d", idDis);
		
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(membre));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		Long countResult = countQuery.getSingleResult();
		

		if (countResult > 0) {
			String errorMsg = String.format("There are %d Members in Organisation id %d",countResult, idDis);		
			throw new Exception(errorMsg);
		}
		else {
			OrganisationRepository.deleteByIdDis(idDis);
		}
        
    }
	
	@Override
	public Page<Organisation> findAll(SearchOrganisationCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Organisation> organisationQuery = criteriaBuilder.createQuery(Organisation.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();
		String societe = searchCriteria.getSociete();
		String adresse = searchCriteria.getAdresse();
		String cp = searchCriteria.getCp();
		String localite = searchCriteria.getLocalite();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer idDis = searchCriteria.getIdDis();
		Boolean isDepot = searchCriteria.getIsDepot();
		Boolean isBirb = searchCriteria.getIsBirb();
		Boolean isWeb = searchCriteria.getIsWeb();
		String statut = searchCriteria.getStatut();
		
		if (societe != null ) {			

			Predicate prenomPredicate = criteriaBuilder.like(organisation.get("societe"), "%" + societe.toLowerCase() + "%");
			predicates.add(prenomPredicate);
		}
		if (adresse != null ) {			

			Predicate adressePredicate = criteriaBuilder.like(organisation.get("adresse"), "%" + adresse.toLowerCase() + "%");
			predicates.add(adressePredicate);
		}
		if (cp != null ) {			

			Predicate cpPredicate = criteriaBuilder.like(organisation.get("cp"), "%" + cp.toLowerCase() + "%");
			predicates.add(cpPredicate);
		}
		if (localite != null ) {			

			Predicate localitePredicate = criteriaBuilder.like(organisation.get("localite"), "%" + localite.toLowerCase() + "%");
			predicates.add(localitePredicate);
		}
		
		if (isDepot != null) {
			Integer intDepot = 0;
			if (isDepot== true) {
				intDepot = 1;
			}
			Predicate isDepotPredicate = criteriaBuilder.equal(organisation.get("depyN"), intDepot);
			predicates.add(isDepotPredicate);
		}
		
		if (isBirb != null) {
			Integer intBirb = 0;
			if (isBirb== true) {
				intBirb = 1;
			}
			Predicate isBirbPredicate = criteriaBuilder.equal(organisation.get("birbyN"), intBirb);
			predicates.add(isBirbPredicate);
		} 
		
		if (isWeb != null) {
			// Alain - boolean comparison
			Predicate isBirbPredicate = criteriaBuilder.equal(organisation.get("webauthority"), isWeb);
			predicates.add(isBirbPredicate);
		} 
		
		if (statut != null) {
			Predicate statutPredicate = criteriaBuilder.like(organisation.get("statut"), "%" + statut + "%");
			predicates.add(statutPredicate);
		}
		if (lienBanque != null) {
		Predicate lienBanquePredicate = criteriaBuilder.equal(organisation.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		
		if (idDis != null) {
			Predicate lienIdDisPredicate = criteriaBuilder.equal(organisation.get("idDis"), idDis);
			predicates.add(lienIdDisPredicate);
		}

		organisationQuery.where(predicates.stream().toArray(Predicate[]::new));	
		organisationQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), organisation, criteriaBuilder));

		TypedQuery<Organisation> query = entityManager.createQuery(organisationQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Organisation.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Organisation> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public Page<Organisation> findSummaries(SearchOrganisationSummariesCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Organisation> organisationQuery = criteriaBuilder.createQuery(Organisation.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();
		String societe = searchCriteria.getSociete();
		Integer lienBanque = searchCriteria.getLienBanque();		
		
		if (societe != null ) {			

			Predicate prenomPredicate = criteriaBuilder.like(organisation.get("societe"), "%" + societe.toLowerCase() + "%");
			predicates.add(prenomPredicate);
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(organisation.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		organisationQuery.where(predicates.stream().toArray(Predicate[]::new));	
		organisationQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), organisation, criteriaBuilder));

		TypedQuery<Organisation> query = entityManager.createQuery(organisationQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Organisation.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Organisation> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}

	public List<Organisation> OrgClientReport(Integer lienBanque) {
    
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    			
    			CriteriaQuery<Organisation> cq = cb.createQuery(Organisation.class);
    			Root<Organisation> organisation = cq.from(Organisation.class);		
    			List<Predicate> predicates = new ArrayList<>();
    			
    			
    			
    			if (lienBanque != null) {
    				Predicate lienBanquePredicate = cb.equal(organisation.get("lienBanque"), lienBanque);
    				predicates.add(lienBanquePredicate);
    				
    			}
    			cq.where(predicates.stream().toArray(Predicate[]::new));
    		   
    		    		      //ordering by count in descending order
    		    cq.orderBy(cb.desc(organisation.get("nPers")));
    		    cq.having(cb.gt(cb.count(organisation.get("nPers")), 1));
    		   
    		    TypedQuery<Organisation> query = entityManager.createQuery(cq);
    		    List<Organisation> resultList = query.getResultList();

    			return resultList;
    		}
	public List<OrgMemberReportDto> OrgMemberReport(Integer lienBanque) {
	    
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    			
    			CriteriaQuery<OrgMemberReportDto> cq = cb.createQuery(OrgMemberReportDto.class);
    			Root<Membre> membre = cq.from(Membre.class);		
    			Join<Membre,Organisation> organisation = membre.join("organisationObject");
    			List<Predicate> predicates = new ArrayList<>();
    			
    			Expression<String> groupByExp = organisation.get("societe").as(String.class);
  		      	Expression<Long> countExp = cb.count(groupByExp);
  		      	cq.multiselect(groupByExp, countExp);
  		    
    			
    			if (lienBanque != null) {
    				Predicate lienBanquePredicate = cb.equal(organisation.get("lienBanque"), lienBanque);
    				predicates.add(lienBanquePredicate);
    				
    			}
    			Predicate lienActifPredicate = cb.equal(membre.get("actif"),1);
    			predicates.add(lienActifPredicate);

    			cq.where(predicates.stream().toArray(Predicate[]::new));
    		    cq.groupBy(groupByExp);
    		    		      //ordering by count in descending order
    		    cq.orderBy(cb.desc(countExp));
    		    cq.having(cb.gt(countExp, 1));

    		    TypedQuery<OrgMemberReportDto> query = entityManager.createQuery(cq);
    		    List<OrgMemberReportDto> resultList = query.getResultList();

    			return resultList;
    		}	
	
	
}
