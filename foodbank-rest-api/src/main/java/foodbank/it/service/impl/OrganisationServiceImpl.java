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
		Integer idDis = searchCriteria.getIdDis();
		String societe = searchCriteria.getSociete();
		String adresse = searchCriteria.getAdresse();
		String nomDepot = searchCriteria.getNomDepot();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDepot = searchCriteria.getlienDepot();
		Boolean isDepot = searchCriteria.getIsDepot();
		Boolean isBirb = searchCriteria.getIsBirb();
		Boolean isAgreed = searchCriteria.getIsAgreed();
		Boolean actif = searchCriteria.getActif();
		String refint = searchCriteria.getRefInt();
		
		if (societe != null ) {			

			Predicate prenomPredicate = criteriaBuilder.like(organisation.get("societe"), "%" + societe.toLowerCase() + "%");
			predicates.add(prenomPredicate);
		}
		if (adresse != null ) {			

			Predicate adressFieldPredicate = criteriaBuilder.like(organisation.get("adresse"), "%" + adresse.toLowerCase() + "%");
			Predicate cityPredicate = criteriaBuilder.like(organisation.get("localite"), "%" + adresse.toLowerCase() + "%");
			Predicate zipCodePredicate = criteriaBuilder.like(organisation.get("cp"), "%" + adresse.toLowerCase() + "%");
			Predicate adressePredicate = criteriaBuilder.or(adressFieldPredicate,cityPredicate,zipCodePredicate);
			predicates.add(adressePredicate);
		}
		if (nomDepot != null ) {			

			Predicate nomDepotPredicate = criteriaBuilder.like(organisation.get("nomDepot"), "%" + nomDepot.toLowerCase() + "%");
			predicates.add(nomDepotPredicate);
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
		
	    if (isAgreed != null) {
			// Note daten field means is Agreed
			Integer intAgreed = 0;
			if (isAgreed == true) {
				intAgreed = 1;
			}
			Predicate isAgreedPredicate = criteriaBuilder.equal(organisation.get("daten"), intAgreed);
			predicates.add(isAgreedPredicate);
		} 
		if (actif != null) {
			Integer intActive = 0;
			if (actif == true) {
				intActive = 1;
			}
			Predicate isActifPredicate = criteriaBuilder.equal(organisation.get("actif"), intActive);
			predicates.add(isActifPredicate);
		} 
		
		if (refint != null) {
			Predicate refintPredicate = criteriaBuilder.like(organisation.get("refInt"), "%" + refint + "%");
			predicates.add(refintPredicate);
		}
		if (lienBanque != null) {
		Predicate lienBanquePredicate = criteriaBuilder.equal(organisation.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		
		if (lienDepot != null) {
			Predicate lienlienDepotPredicate = criteriaBuilder.equal(organisation.get("lienDepot"), lienDepot);
			predicates.add(lienlienDepotPredicate);
		}
		if (idDis != null) {
			Predicate idDisPredicate = criteriaBuilder.equal(organisation.get("idDis"), idDis);
				predicates.add(idDisPredicate);
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
		Integer lienDepot = searchCriteria.getLienDepot();
		Boolean isDepot = searchCriteria.getIsDepot();
		
		if (societe != null ) {	
			Predicate prenomPredicate = criteriaBuilder.like(organisation.get("societe"), "%" + societe.toLowerCase() + "%");
			predicates.add(prenomPredicate);
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(organisation.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		if (lienDepot != null) {
			Predicate lienDepotPredicate = criteriaBuilder.equal(organisation.get("lienDepot"), lienDepot);
			predicates.add(lienDepotPredicate);
		}
		if (isDepot != null) {
			Integer intDepot = 0;
			if (isDepot== true) {
				intDepot = 1;
			}
			Predicate isDepotPredicate = criteriaBuilder.equal(organisation.get("depyN"), intDepot);
			predicates.add(isDepotPredicate);
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
    			Join<Membre,Organisation> organisation = membre.join("lienDis");
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
