package foodbank.it.service.impl;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import foodbank.it.persistence.model.Depot;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.repository.IDepotRepository;
import foodbank.it.persistence.repository.IOrganisationRepository;
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


import foodbank.it.service.IDepotService;
import foodbank.it.service.SearchDepotCriteria;

@Service
public class DepotServiceImpl implements IDepotService{

	private IDepotRepository DepotRepository;
	private IOrganisationRepository OrganisationRepository;
	private final EntityManager entityManager;
	
	public DepotServiceImpl(
			IDepotRepository DepotRepository,
			IOrganisationRepository OrganisationRepository,
			EntityManager entityManager
			) {
        this.DepotRepository = DepotRepository;
        this.OrganisationRepository = OrganisationRepository;
        this.entityManager = entityManager;
    }
	@Override
    public Optional<Depot> findByIdDepot(String idDepot) {
        return DepotRepository.findByIdDepot(idDepot);
    }

    @Override
    @Transactional
    public Depot save(Depot Depot) { 
    	// save depot but also update matching organisation fields for this depot
    	 Integer lienDepotInteger = Optional.ofNullable(Depot.getIdDepot()).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null); 
    	 if (lienDepotInteger != null) {
    		 Optional<Organisation> depotOrg = this.OrganisationRepository.findById(lienDepotInteger);
    		 depotOrg.ifPresentOrElse(myOrg->  {
    			 myOrg.setSociete(Depot.getNom());
    			 myOrg.setAdresse(Depot.getAdresse()) ;
    			 myOrg.setAdresse2(Depot.getAdresse2());
    			 myOrg.setCp(Depot.getCp());
    			 myOrg.setLocalite(Depot.getVille());
    			 myOrg.setTel(Depot.getTelephone());
    			 myOrg.setEmail(Depot.getEmail());
    			 myOrg.setDepPrinc(Depot.getDepPrinc());
    			 myOrg.setActif(Depot.getActif());
    			 // Note we do not synchronize lien_banque from ID_company, dep_FEAD, memo and contact fields
    			 System.out.printf("\nSynchronizing entries between depot and organisation entries for depot %s %s\n",Depot.getIdDepot(), Depot.getNom());
    			 OrganisationRepository.save(myOrg);
    			
    		 },
    		 ()-> { 
    			 	System.out.printf("\nCould not synchronize depot: organisation not found for depot %s %s\n",Depot.getIdDepot(),Depot.getNom());
    			}
    		);
    	 }
        return DepotRepository.save(Depot);
    }
    
    @Override 
    public Page<Depot> findAll(SearchDepotCriteria searchCriteria, Pageable pageable) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Depot> depotQuery = criteriaBuilder.createQuery(Depot.class);
	Root<Depot> depot = depotQuery.from(Depot.class);
	List<Predicate> predicates = new ArrayList<>();

	String nom = searchCriteria.getNom();
	Boolean actif = searchCriteria.getActif();
	String idCompany = searchCriteria.getIdCompany();
	

	if (nom != null ) {			

		Predicate nomPredicate = criteriaBuilder.like(depot.get("nom"), "%" + nom.toLowerCase() + "%");
		predicates.add(nomPredicate);
	}
	
	if (actif != null) {
		Integer intActive = 0;
		if (actif == true) {
			intActive = 1;
		}
		Predicate isActifPredicate = criteriaBuilder.equal(depot.get("actif"), intActive);
		predicates.add(isActifPredicate);
	} 
	
	if (idCompany != null) {
		Predicate idCompanyPredicate = criteriaBuilder.equal(depot.get("idCompany"), idCompany);
		predicates.add(idCompanyPredicate);
	}
	

	

	depotQuery.where(predicates.stream().toArray(Predicate[]::new));
	depotQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), depot, criteriaBuilder));

	TypedQuery<Depot> query = entityManager.createQuery(depotQuery);
	query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
	query.setMaxResults(pageable.getPageSize());

	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
	totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
	totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Depot.class)));
    TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
    long countResult = countQuery.getSingleResult();
	List<Depot> resultList = query.getResultList();
	return new PageImpl<>(resultList, pageable, countResult);
}
    

    @Override
    @Transactional
    public void delete(String idDepot) {
        DepotRepository.deleteByIdDepot(idDepot);
        
    }

}
