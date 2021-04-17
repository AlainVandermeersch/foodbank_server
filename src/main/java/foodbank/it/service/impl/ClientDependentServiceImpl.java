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

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.ClientDependent;
import foodbank.it.persistence.repository.IClientDependentRepository;
import foodbank.it.service.IClientDependentService;
import foodbank.it.service.SearchClientDependentCriteria;
@Service
public class ClientDependentServiceImpl implements IClientDependentService{
	
	private final IClientDependentRepository ClientDependentRepository;
	private final EntityManager entityManager;
	
	public ClientDependentServiceImpl(IClientDependentRepository ClientDependentRepository,
			 EntityManager entityManager) {
        this.ClientDependentRepository = ClientDependentRepository;
        this.entityManager = entityManager;
    }

	@Override
	public Optional<ClientDependent> findByIdDep(int idDep) {
		return this.ClientDependentRepository.findByIdDep(idDep);
	}

	@Override
	public ClientDependent save(ClientDependent clientDependent) {
		return this.ClientDependentRepository.save(clientDependent);
	}

	@Override
	public Iterable<ClientDependent> findAll(SearchClientDependentCriteria searchCriteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClientDependent> clientDependentQuery = criteriaBuilder.createQuery(ClientDependent.class);
		Root<ClientDependent> clientDependent = clientDependentQuery.from(ClientDependent.class);

		List<Predicate> predicates = new ArrayList<>();
		Integer lienMast = searchCriteria.getLienMast();
		Integer intIsActif = searchCriteria.isActif();
		Predicate lienMastPredicate = criteriaBuilder.equal(clientDependent.get("lienMast"), lienMast);
			predicates.add(lienMastPredicate);
	
		
		if (intIsActif != null) {
			Predicate lienActifPredicate = criteriaBuilder.equal(clientDependent.get("actif"), intIsActif);
			predicates.add(lienActifPredicate);
		}

		clientDependentQuery.where(predicates.stream().toArray(Predicate[]::new));		

		TypedQuery<ClientDependent> query = entityManager.createQuery(clientDependentQuery);		

		List<ClientDependent> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@Transactional
	public void delete(int idDep) {
		this.ClientDependentRepository.deleteByIdDep(idDep);
		
	}

	@Override
	@Transactional
	public void deleteByLienMast(int lienMast) {
		this.ClientDependentRepository.deleteByLienMast(lienMast);
		
	}


}
