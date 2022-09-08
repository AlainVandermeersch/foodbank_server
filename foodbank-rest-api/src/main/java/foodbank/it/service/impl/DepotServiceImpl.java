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
	private String anomalies;
	
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
    public Depot save(Depot Depot, Boolean sync) throws Exception {
    	// save depot by copying copy matching organisation fields for this depot
		if (sync == true) {
			Integer lienDepotInteger = Optional.ofNullable(Depot.getIdDepot()).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
			if (lienDepotInteger != null) {
				Optional<Organisation> depotOrg = this.OrganisationRepository.findById(lienDepotInteger);
				depotOrg.ifPresentOrElse(myOrg -> {
							Depot.setNom(myOrg.getSociete());
							Depot.setAdresse(myOrg.getAdresse());
							Depot.setAdresse2(myOrg.getAdresse2());
							Depot.setCp(myOrg.getCp());
							Depot.setVille(myOrg.getLocalite());
							Depot.setTelephone(myOrg.getTel());
							Depot.setEmail(myOrg.getEmail());
							Depot.setDepPrinc(myOrg.getDepPrinc());
							Depot.setActif(myOrg.getActif());


						},
						() -> {
							String errorMsg = String.format("Could not synchronize depot: organisation not found for depot %s %s", Depot.getIdDepot(), Depot.getNom());
							try {
								throw new Exception(errorMsg);
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}
				);
			} else {
				String errorMsg = String.format("Could not save Depot  with empty or non numeric id '%s' and name '%s'", Depot.getIdDepot(), Depot.getNom());
				try {
					throw new Exception(errorMsg);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
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

	@Override
	public String getAnomalies(Depot Depot) {
		this.anomalies ="";
		Integer lienDepotInteger = Optional.ofNullable(Depot.getIdDepot()).filter(str -> !str.isEmpty()).map(Integer::parseInt).orElse(null);
		if (lienDepotInteger != null) {
			Optional<Organisation> depotOrg = this.OrganisationRepository.findById(lienDepotInteger);
			depotOrg.ifPresentOrElse(myOrg -> {
						if (!myOrg.getSociete().equals(Depot.getNom())) {
							this.anomalies += String.format("nom: %s;", myOrg.getSociete());
						}
						if (!myOrg.getAdresse().equals(Depot.getAdresse())) {
							this.anomalies += String.format("adresse: %s;", myOrg.getAdresse());
						}
						if (!myOrg.getAdresse2().equals(Depot.getAdresse2())) {
							this.anomalies += String.format("adresse2: %s;", myOrg.getAdresse2());
						}
						if (!myOrg.getCp().equals(Depot.getCp())) {
							this.anomalies += String.format("cp: %s;", myOrg.getCp());
						}
						if (!myOrg.getLocalite().equals(Depot.getVille())) {
							this.anomalies += String.format("ville: %s;", myOrg.getLocalite());
						}
						if (!myOrg.getTel().equals(Depot.getTelephone())) {
							this.anomalies += String.format("tel: %s;", myOrg.getTel());
						}
						if (!myOrg.getEmail().equals(Depot.getEmail())) {
							this.anomalies += String.format("email: %s;", myOrg.getEmail());
						}
						if (!myOrg.getDepPrinc().equals(Depot.getDepPrinc())) {
							this.anomalies += String.format("depPrinc: %s;", myOrg.getDepPrinc());
						}
						if (!myOrg.getActif().equals(Depot.getActif())) {
							this.anomalies += String.format("actif: %d;", myOrg.getActif());
						}
					},
					() -> {
						this.anomalies += String.format("idDepot: %s;",Depot.getIdDepot());

					}
			);
		}
		else {
			this.anomalies += String.format("idDepot: %s;",Depot.getIdDepot());
		}
		return this.anomalies;
	}
	

}
