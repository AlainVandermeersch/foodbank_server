package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.IMembreRepository;
import foodbank.it.service.IMembreService;
import foodbank.it.service.SearchMembreCriteria;
import foodbank.it.service.SearchMembreMailCriteria;

@Service
public class MembreServiceImpl implements IMembreService{

	private IMembreRepository MembreRepository;
	private final EntityManager entityManager;
	
	public MembreServiceImpl(IMembreRepository MembreRepository,EntityManager entityManager) {
        this.MembreRepository = MembreRepository;
        this.entityManager = entityManager;
    }
	@Override
    public Optional<Membre> findByBatId(int batId) {
        return MembreRepository.findByBatId(batId);
    }

    @Override
    public Membre save(Membre membre, boolean booCreateMode) throws Exception {  
    	if (booCreateMode == true) {
    		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
    			System.out.printf("Checking If Member exists with nom: %s prenom %s in Organisation %d\n", membre.getNom(), membre.getPrenom(), lienDis);
    		}
    		else {
    			Predicate lienBanquePredicate = criteriaBuilder.equal(existingMembre.get("lienBanque"), membre.getLienBanque());
    			predicates.add(lienBanquePredicate);
    			Predicate lienDisPredicate = criteriaBuilder.or((criteriaBuilder.equal(existingMembre.get("lienDis"),0 )),criteriaBuilder.isNull(existingMembre.get("lienDis")));
    			predicates.add(lienDisPredicate); 
    			System.out.printf("Checking If Member exists with nom: %s prenom %s in Bank %d\n", membre.getNom(), membre.getPrenom(),membre.getLienBanque());
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
	
		System.out.printf("\nChecking User References to Member with id: %d", batId);
		
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
   
    @Override 
    public Page<Membre> findAll(SearchMembreCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membre> membreQuery = criteriaBuilder.createQuery(Membre.class);
		Root<Membre> membre = membreQuery.from(Membre.class);
		List<Predicate> predicates = new ArrayList<>();

		String nom = searchCriteria.getNom();
		String prenom = searchCriteria.getPrenom();
		String address = searchCriteria.getAddress();
		String zip = searchCriteria.getZip();
		String city = searchCriteria.getCity();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDis = searchCriteria.getLienDis();
		Integer lienDepot = searchCriteria.getLienDepot();

		if (nom != null ) {			

			Predicate nomPredicate = criteriaBuilder.like(membre.get("nom"), "%" + nom.toLowerCase() + "%");
			predicates.add(nomPredicate);
		}
		if (prenom != null ) {			

			Predicate prenomPredicate = criteriaBuilder.like(membre.get("prenom"), "%" + prenom.toLowerCase() + "%");
			predicates.add(prenomPredicate);
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
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(membre.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		if (lienDis != null) {
			System.out.printf("\nChecking Members with liendis: %d", lienDis);
			if (lienDis == 0) {
				Predicate lienDisZero = criteriaBuilder.equal(membre.get("lienDis"), 0);
				Predicate lienDisNull = criteriaBuilder.isNull(membre.get("lienDis"));
				Predicate lienDisPredicate = criteriaBuilder.or(lienDisZero,lienDisNull);
				predicates.add(lienDisPredicate);
			}
			else {
				Predicate lienDisPredicate = criteriaBuilder.equal(membre.get("lienDis"), lienDis);
				predicates.add(lienDisPredicate);
			}
		}
		else {
			if (lienDepot == null) {
			System.out.printf("\nExcluding Bank Members");
			// exclude members of bank who have liendis 0 or null
			Predicate lienDisNotZero = criteriaBuilder.notEqual(membre.get("lienDis"), 0);
			Predicate lienDisNotNull = criteriaBuilder.isNotNull(membre.get("lienDis"));
			predicates.add(lienDisNotZero);
			predicates.add(lienDisNotNull);
			}
			else {
				Predicate lienDepotPredicate = criteriaBuilder.equal(membre.get("lienDepot"),lienDepot);
				predicates.add(lienDepotPredicate);
			}
		}
		Predicate lienActifPredicate = criteriaBuilder.equal(membre.get("actif"),1);
		predicates.add(lienActifPredicate);

		membreQuery.where(predicates.stream().toArray(Predicate[]::new));
		membreQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), membre, criteriaBuilder));

		TypedQuery<Membre> query = entityManager.createQuery(membreQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Membre.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
		List<Membre> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}
	@Override
	public List<Membre> findAll(SearchMembreMailCriteria searchCriteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membre> membreQuery = criteriaBuilder.createQuery(Membre.class);
		Root<Membre> membre = membreQuery.from(Membre.class);

		List<Predicate> predicates = new ArrayList<>();

		
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDis = searchCriteria.getLienDis();
		
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(membre.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		if (lienDis != null) {
			if (lienDis == 0) {
				Predicate lienDisZero = criteriaBuilder.equal(membre.get("lienDis"), 0);
				Predicate lienDisNull = criteriaBuilder.isNull(membre.get("lienDis"));
				Predicate lienDisPredicate = criteriaBuilder.or(lienDisZero,lienDisNull);
				predicates.add(lienDisPredicate);
			}
			else {
				Predicate lienDisPredicate = criteriaBuilder.equal(membre.get("lienDis"), lienDis);
				predicates.add(lienDisPredicate);
			}
		}
		else {
			System.out.printf("\nExcluding Bank Members");
			// exclude members of bank who have liendis 0 or null
			Predicate lienDisNotZero = criteriaBuilder.notEqual(membre.get("lienDis"), 0);
			Predicate lienDisNotNull = criteriaBuilder.isNotNull(membre.get("lienDis"));
			predicates.add(lienDisNotZero);
			predicates.add(lienDisNotNull);
		}
		Predicate lienActifPredicate = criteriaBuilder.equal(membre.get("actif"),1);
		predicates.add(lienActifPredicate);

		membreQuery.where(predicates.stream().toArray(Predicate[]::new));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(criteriaBuilder.asc(membre.get("nom")));
		orderList.add(criteriaBuilder.asc(membre.get("prenom")));
		membreQuery.orderBy(orderList);

		TypedQuery<Membre> query = entityManager.createQuery(membreQuery);
		List<Membre> resultList = query.getResultList();
		return resultList;
	}
}
	
