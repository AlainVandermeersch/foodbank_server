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

import foodbank.it.persistence.model.Organisation;
import foodbank.it.service.IMailService;
import foodbank.it.service.SearchMailListCriteria;
import foodbank.it.web.dto.MailAddressDto;

@Service
public class MailServiceImpl implements IMailService{

	
	private final EntityManager entityManager;
	
	public MailServiceImpl(EntityManager entityManager) {
         this.entityManager = entityManager;
    }
	@Override
	public List<MailAddressDto> find(SearchMailListCriteria 
			searchCriteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Organisation> organisationQuery = criteriaBuilder.createQuery(Organisation.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();

		
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDis = searchCriteria.getLienDis();
		Integer regId =  searchCriteria.getRegId();
		
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(organisation.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		

		if (lienDis != null) {
			    Predicate lienDisPredicate = criteriaBuilder.equal(organisation.get("idDis"), lienDis);
				predicates.add(lienDisPredicate);
	    }
		
		if (regId != null) {
		    Predicate regIdPredicate = criteriaBuilder.equal(organisation.get("region"), regId	);
			predicates.add(regIdPredicate);
    }
	
		
		Predicate lienActifPredicate = criteriaBuilder.equal(organisation.get("actif"),1);
		predicates.add(lienActifPredicate);

		organisationQuery.where(predicates.stream().toArray(Predicate[]::new));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(criteriaBuilder.asc(organisation.get("idDis")));
		
		organisationQuery.orderBy(orderList);

		TypedQuery<Organisation> query = entityManager.createQuery(organisationQuery);
		List<Organisation> selectedOrganisations = query.getResultList();
		return selectedOrganisations.stream()
				.map(org -> convertOrganisationToMailAddressDto(org))
				.collect(Collectors.toList());
	}
	protected MailAddressDto convertOrganisationToMailAddressDto(Organisation entity) {  
    	   	
    	MailAddressDto dto = new MailAddressDto(entity.getIdDis() + " " + entity.getSociete(),"Organisation","Contact",entity.getEmail());
    	return dto;
	}
}
