package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.query.QueryUtils;

import foodbank.it.persistence.model.Trip;
import foodbank.it.persistence.repository.ITripRepository;

import java.util.Optional;

import javax.persistence.EntityManager;
import foodbank.it.service.ITripService;
import foodbank.it.service.SearchTripCriteria;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Service
public class TripServiceImpl implements ITripService {
	
	private ITripRepository TripRepository;
    private final EntityManager entityManager;

    public TripServiceImpl(ITripRepository TripRepository,EntityManager entityManager) {
        this.TripRepository = TripRepository;
        this.entityManager = entityManager;
        
    }

	@Override
	public Optional<Trip> findByTripId(int tripId) {
		
		return TripRepository.findByTripId(tripId);
	}

	@Override
	public Page<Trip> findAll(SearchTripCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Trip> tripQuery = criteriaBuilder.createQuery(Trip.class);
		Root<Trip> trip = tripQuery.from(Trip.class);

		List<Predicate> predicates = new ArrayList<>();

		String nom = searchCriteria.getMembreNom();
	
		Integer batnr = searchCriteria.getBatId();
		
		if (nom != null ) {			

			Predicate nomPredicate = criteriaBuilder.like(trip.get("membreNom"), "%" + nom.toLowerCase() + "%");
			predicates.add(nomPredicate);
		}
		
		if (batnr != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(trip.get("batnr"), batnr);
			predicates.add(lienBanquePredicate);
		}
		

		Predicate lienActifPredicate = criteriaBuilder.equal(trip.get("actif"),1);
		predicates.add(lienActifPredicate);

		tripQuery.where(predicates.stream().toArray(Predicate[]::new));
		tripQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), trip, criteriaBuilder));

		TypedQuery<Trip> query = entityManager.createQuery(tripQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Trip.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Trip> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}

	@Override
	public Trip save(Trip trip)  {
		
		return TripRepository.save(trip);
	}

	@Override
	@Transactional
	public void delete(int tripId) {
		TripRepository.deleteByTripId(tripId);
		
	}

}
