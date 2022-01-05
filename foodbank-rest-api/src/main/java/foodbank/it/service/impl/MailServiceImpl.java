package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.repository.IMembreRepository;
import foodbank.it.persistence.repository.ITUserRepository;
import foodbank.it.service.IMailService;
import foodbank.it.service.SearchMailListCriteria;
import foodbank.it.web.dto.MailAddressDto;

@Service
public class MailServiceImpl implements IMailService{

	private IMembreRepository MembreRepository;
	private ITUserRepository TUserRepository;
	private final EntityManager entityManager;
	
	public MailServiceImpl(IMembreRepository MembreRepository,ITUserRepository TUserRepository, EntityManager entityManager) {
        this.MembreRepository = MembreRepository;
        this.TUserRepository = TUserRepository;
        this.entityManager = entityManager;
    }
	@Override
	public List<MailAddressDto> find(SearchMailListCriteria 
			searchCriteria) {
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
		List<Membre> selectedMembres = query.getResultList();
		return selectedMembres.stream()
				.map(Membre -> convertMembreToMailAddressDto(Membre))
				.collect(Collectors.toList());
	}
	protected MailAddressDto convertMembreToMailAddressDto(Membre entity) {  
    	   	
    	MailAddressDto dto = new MailAddressDto(entity.getSociete(),entity.getNom(),entity.getPrenom(),entity.getBatmail());
    	return dto;
	}
}
