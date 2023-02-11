package foodbank.it.service.impl;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.IMembreRepository;
import foodbank.it.persistence.repository.IOrganisationRepository;
import foodbank.it.persistence.repository.ITUserRepository;
import foodbank.it.service.IMembreService;
import foodbank.it.service.SearchMembreCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MembreServiceImpl implements IMembreService{

	private IMembreRepository MembreRepository;
	private ITUserRepository TUserRepository;
	private IOrganisationRepository OrganisationRepository;
	private final EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public MembreServiceImpl(IMembreRepository MembreRepository, ITUserRepository TUserRepository, IOrganisationRepository OrganisationRepository,
							 EntityManager entityManager) {
        this.MembreRepository = MembreRepository;
        this.TUserRepository = TUserRepository;
		this.OrganisationRepository = OrganisationRepository;
        this.entityManager = entityManager;
    }
	@Override
    public Optional<Membre> findByBatId(int batId) {
        return MembreRepository.findByBatId(batId);
    }

    @Override
    @Transactional
    public Membre save(Membre membre, boolean booCreateMode) throws Exception {  
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	if (booCreateMode) {
        	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        	Root<Membre> existingMembre = totalCriteriaQuery.from(Membre.class);
    		List<Predicate> predicates = new ArrayList<>();
    		Predicate nomPredicate = criteriaBuilder.equal(existingMembre.get("nom"), membre.getNom());
    		predicates.add(nomPredicate);
    		Predicate prenomPredicate = criteriaBuilder.equal(existingMembre.get("prenom"), membre.getPrenom());
    		predicates.add(prenomPredicate);
    		Integer lienDis = membre.getLienDis();
    		if (lienDis != null && lienDis != 0 ) {
    			Predicate lienDisPredicate = criteriaBuilder.equal(existingMembre.get("lienDis"), lienDis);
    			predicates.add(lienDisPredicate);    			
    			// Checking If Member exists with nom: %s prenom %s in Organisation %d", membre.getNom(), membre.getPrenom(), lienDis);
    		}
    		else {
    			Predicate lienBanquePredicate = criteriaBuilder.equal(existingMembre.get("lienBanque"), membre.getLienBanque());
    			predicates.add(lienBanquePredicate);
    			Predicate lienDisPredicate = criteriaBuilder.or((criteriaBuilder.equal(existingMembre.get("lienDis"),0 )),criteriaBuilder.isNull(existingMembre.get("lienDis")));
    			predicates.add(lienDisPredicate); 
    			// Checking If Member exists with nom: %s prenom %s in Bank %d", membre.getNom(), membre.getPrenom(),membre.getLienBanque());
    		}
    		
    		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
    		totalCriteriaQuery.select(criteriaBuilder.count(existingMembre));
    		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
    		Long countResult = countQuery.getSingleResult();
    		

    		if (countResult > 0) {
    			String errorMsg = String.format("a member exists already with name %s %s",membre.getNom(), membre.getPrenom());		
    			throw new Exception(errorMsg);
    		}

    	}
    	else {
			// if lienbanque is null, pick lienbanque from organisation if there
			if (membre.getLienBanque() == null) {
				Integer idDis = membre.getLienDis();
				Optional<Organisation> o = this.OrganisationRepository.findByIdDis(idDis);
				o.ifPresent(myOrg -> {
					membre.setLienBanque(myOrg.getLienBanque());
					log.debug("Replacing in membre %s %s null value lienBanque with org lienbanque: %d", membre.getNom(),membre.getPrenom(),myOrg.getLienBanque());
				});
			}
    		// updating members we must update the duplicate User Name, E-Mail and Language fields in the Users pointing to the Member
    		CriteriaQuery<TUser> tuserQuery = criteriaBuilder.createQuery(TUser.class);
    		Root<TUser> tuser = tuserQuery.from(TUser.class);		
    		List<Predicate> predicates = new ArrayList<>();
    		Predicate lienBatPredicate = criteriaBuilder.equal(tuser.get("lienBat"), membre.getBatId());
			predicates.add(lienBatPredicate);
			tuserQuery.where(predicates.stream().toArray(Predicate[]::new));
			TypedQuery<TUser> query = entityManager.createQuery(tuserQuery);
			List<TUser> userList = query.getResultList();
			userList.forEach((tUser) -> {
				tUser.setEmail(membre.getBatmail());
				tUser.setUserName(membre.getNom() + ' ' + membre.getPrenom());
				switch (membre.getLangue()) {
                case 1:
                    tUser.setIdLanguage("fr");
                    break;
                case 2:
                	tUser.setIdLanguage("nl");
                    break;
                case 3:
                	tUser.setIdLanguage("en");
                    break;
                case 4:
                	tUser.setIdLanguage("de");
                    break;
                default:
                	tUser.setIdLanguage("??");
            }
				TUserRepository.save(tUser);
	        });
			

    		
    	}
    	
    	
        return MembreRepository.save(membre);
    }

    @Override
    @Transactional
    public void delete(int batId) throws Exception {
    	
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<TUser> tuser = totalCriteriaQuery.from(TUser.class);
		List<Predicate> predicates = new ArrayList<>();
		Predicate lienBatPredicate = criteriaBuilder.equal(tuser.get("lienBat"), batId);
		predicates.add(lienBatPredicate);
	
		log.debug("Checking User References to Member with id: %d", batId);
		
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(tuser));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		Long countResult = countQuery.getSingleResult();
		

		if (countResult > 0) {
			String errorMsg = String.format("There are %d Users with Member id %d",countResult, batId);		
			throw new Exception(errorMsg);
		}
		else {
			MembreRepository.deleteByBatId(batId);		
		}
        
    }
	List<Predicate> createPredicatesForQuery(CriteriaBuilder criteriaBuilder,CriteriaQuery<Membre> membreQuery, Root<Membre> membre, SearchMembreCriteria searchCriteria) {

		List<Predicate> predicates = new ArrayList<>();
		String nom = searchCriteria.getNom();
		Boolean actif = searchCriteria.getActif();
		String address = searchCriteria.getAddress();
		String zip = searchCriteria.getZip();
		String city = searchCriteria.getCity();
		String batmail = searchCriteria.getBatmail();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDis = searchCriteria.getLienDis();
		Integer lienDepot = searchCriteria.getLienDepot();
		String bankShortName = searchCriteria.getBankShortName();
		String telgsm = searchCriteria.getTelgsm();
		Integer fonction = searchCriteria.getFonction();
		String hasAnomalies = searchCriteria.getHasAnomalies();
		Boolean classicBanks = searchCriteria.getClassicBanks();

		if (nom != null ) {

			Predicate nomPredicate = criteriaBuilder.like(membre.get("nom"), "%" + nom.toLowerCase() + "%");
			Predicate prenomPredicate = criteriaBuilder.like(membre.get("prenom"), "%" + nom.toLowerCase() + "%");
			Predicate fullNamePredicate = criteriaBuilder.or(nomPredicate,prenomPredicate);
			predicates.add(fullNamePredicate);
		}
		if (telgsm != null ) {

			Predicate telPredicate = criteriaBuilder.like(criteriaBuilder.function("REPLACE"
					, String.class,membre.get("tel"), criteriaBuilder.literal(" ")
					, criteriaBuilder.literal("")),"%" + telgsm + "%");
			Predicate gsmPredicate = criteriaBuilder.like(criteriaBuilder.function("REPLACE"
					, String.class,membre.get("gsm"), criteriaBuilder.literal(" ")
					, criteriaBuilder.literal("")),"%" + telgsm + "%");
			Predicate telgsmPredicate = criteriaBuilder.or(telPredicate,gsmPredicate);
			predicates.add(telgsmPredicate);
		}

		if (address != null ) {

			Predicate addressPredicate = criteriaBuilder.like(membre.get("address"), "%" + address.toLowerCase() + "%");
			predicates.add(addressPredicate);
		}
		if (zip != null ) {

			Predicate zipPredicate = criteriaBuilder.like(membre.get("zip"), "%" + zip.toLowerCase() + "%");
			predicates.add(zipPredicate);
		}
		if (city != null ) {

			Predicate cityPredicate = criteriaBuilder.like(membre.get("city"), "%" + city.toLowerCase() + "%");
			predicates.add(cityPredicate);
		}
		if (batmail != null ) {

			Predicate batmailPredicate = criteriaBuilder.like(membre.get("batmail"), "%" + batmail.toLowerCase() + "%");
			predicates.add(batmailPredicate);
		}
		if (lienBanque != null) {
			if (lienBanque == 999)
			{
				Predicate lienBanqueNullPredicate = criteriaBuilder.isNull(membre.get("lienBanque"));
				predicates.add(lienBanqueNullPredicate);
			}
			else {
				Predicate lienBanquePredicate = criteriaBuilder.equal(membre.get("lienBanque"), lienBanque);
				predicates.add(lienBanquePredicate);
			}
		}
		if (bankShortName != null) {
			Predicate lienBankShortNamePredicate = criteriaBuilder.equal(membre.get("bankShortName"), bankShortName);
			predicates.add(lienBankShortNamePredicate);
		}

		if (fonction != null) {
			Predicate fonctionPredicate = criteriaBuilder.equal(membre.get("fonction"), fonction);
			predicates.add(fonctionPredicate);
		}

		if (lienDis != null) {
			log.debug("Checking Members with liendis: %d", lienDis);
			if (lienDis == 0) {
				// selecting bank members only
				Predicate lienDisZero = criteriaBuilder.equal(membre.get("lienDis"), 0);
				Predicate lienDisNull = criteriaBuilder.isNull(membre.get("lienDis"));
				Predicate lienDisPredicate = criteriaBuilder.or(lienDisZero,lienDisNull);
				predicates.add(lienDisPredicate);
			}
			else {
				if (lienDis == 999){
					// exclude members of bank who have liendis 0 or null
					Predicate lienDisNotZero = criteriaBuilder.notEqual(membre.get("lienDis"), 0);
					Predicate lienDisNotNull = criteriaBuilder.isNotNull(membre.get("lienDis"));
					predicates.add(lienDisNotZero);
					predicates.add(lienDisNotNull);
				}
				else {
					Predicate lienDisPredicate = criteriaBuilder.equal(membre.get("lienDis"), lienDis);
					predicates.add(lienDisPredicate);
				}
			}
		}

		if (lienDepot != null) {
			Predicate lienDepotPredicate = criteriaBuilder.equal(membre.get("lienDepot"),lienDepot);
			predicates.add(lienDepotPredicate);

		}
		if (actif != null) {
			Integer intActive = 0;
			if (actif) {
				intActive = 1;
			}
			Predicate isActifPredicate = criteriaBuilder.equal(membre.get("actif"), intActive);
			predicates.add(isActifPredicate);
		}
		if (classicBanks != null) {
			Predicate lienBanqueClassicPredicate = criteriaBuilder.lessThan(membre.get("lienBanque"), 11);
			predicates.add(lienBanqueClassicPredicate);
		}
		if (hasAnomalies != null) {
			if (hasAnomalies.equals("1")) {
				Predicate emailNullPredicate = criteriaBuilder.isNull(membre.get("batmail"));
				Predicate emailBlankPredicate = criteriaBuilder.equal(membre.get("batmail"), "");
				Predicate emailAbsentPredicate = criteriaBuilder.or(emailNullPredicate,emailBlankPredicate);
				predicates.add(emailAbsentPredicate);
			}
			else if (hasAnomalies.equals("2")) {
				Subquery<Long> subQuery = membreQuery.subquery(Long.class);
				Root<Membre> subqueryMembre = subQuery.from(Membre.class);

				subQuery.select(criteriaBuilder.count(membre))
						.where(criteriaBuilder.isNotNull(membre.get("batmail")),
								criteriaBuilder.notEqual(membre.get("batmail"),""),
								criteriaBuilder.equal(subqueryMembre.get("batmail"), membre.get("batmail"))
						)
						.groupBy(subqueryMembre.get("batmail"));

				predicates.add(criteriaBuilder.greaterThan(subQuery, 1L));
			}
			else if (hasAnomalies.equals("3")) {
				Subquery<Long> subQuery = membreQuery.subquery(Long.class);
				Root<Membre> subqueryMembre = subQuery.from(Membre.class);

				subQuery.select(criteriaBuilder.count(membre))
						.where(criteriaBuilder.equal(subqueryMembre.get("nom"), membre.get("nom")),
								criteriaBuilder.equal(subqueryMembre.get("prenom"), membre.get("prenom")))
						.groupBy(subqueryMembre.get("nom"), subqueryMembre.get("prenom"));

				predicates.add(criteriaBuilder.greaterThan(subQuery, 1L));
			}


		}
		return predicates;
	}
	@Override
    public Page<Membre> findPaged(SearchMembreCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membre> membreQuery = criteriaBuilder.createQuery(Membre.class);
		Root<Membre> membre = membreQuery.from(Membre.class);
		List<Predicate> predicates = this.createPredicatesForQuery(criteriaBuilder,membreQuery,membre, searchCriteria);
		membreQuery.where(predicates.toArray(new Predicate[0]));
		membreQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), membre, criteriaBuilder));

		TypedQuery<Membre> query = entityManager.createQuery(membreQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.toArray(new Predicate[0]));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Membre.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
		List<Membre> resultList = query.getResultList();

		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public Iterable<Membre> findAll(SearchMembreCriteria searchCriteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membre> membreQuery = criteriaBuilder.createQuery(Membre.class);
		Root<Membre> membre = membreQuery.from(Membre.class);
		List<Predicate> predicates = this.createPredicatesForQuery(criteriaBuilder,membreQuery,membre, searchCriteria);
		membreQuery.where(predicates.toArray(new Predicate[0]));
		membreQuery.orderBy(criteriaBuilder.asc(membre.get("nom")),criteriaBuilder.asc(membre.get("prenom")));
		TypedQuery<Membre> query = entityManager.createQuery(membreQuery);
		List<Membre> resultList = query.getResultList();
		return resultList;
	}



}
	
