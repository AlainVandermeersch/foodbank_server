package foodbank.it.service.impl;

import foodbank.it.persistence.model.Client;
import foodbank.it.persistence.repository.IClientDependentRepository;
import foodbank.it.persistence.repository.IClientRepository;
import foodbank.it.service.IClientService;
import foodbank.it.service.SearchClientCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService{

	private final IClientRepository ClientRepository;
	private final IClientDependentRepository ClientDependentRepository;
	private final EntityManager entityManager;
	
	public ClientServiceImpl(IClientRepository ClientRepository,
			IClientDependentRepository ClientDependentRepository,
			EntityManager entityManager) {
        this.ClientRepository = ClientRepository;
        this.ClientDependentRepository = ClientDependentRepository;
		this.entityManager = entityManager;
	}
	@Override
    public Optional<Client> findByIdClient(int idClient) {
        return ClientRepository.findByIdClient(idClient);
    }

	@Override public Page<Client> findAll(SearchClientCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Client> clientQuery = criteriaBuilder.createQuery(Client.class);
		Root<Client> client = clientQuery.from(Client.class);

		List<Predicate> predicates = new ArrayList<>();
		String nom = searchCriteria.getNom();
		String prenom = searchCriteria.getPrenom();
		String adresse = searchCriteria.getAdresse();
		String cp = searchCriteria.getCp();
		String localite = searchCriteria.getLocalite();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer lienDis = searchCriteria.getLienDis();
		Integer actif = searchCriteria.getActif();
		Boolean isSuspect = searchCriteria.getSuspect();

		if (nom != null ) {			

			Predicate nomPredicate = criteriaBuilder.like(client.get("nom"), "%" + nom.toLowerCase() + "%");
			predicates.add(nomPredicate);
		}
		if (prenom != null ) {			

			Predicate prenomPredicate = criteriaBuilder.like(client.get("prenom"), "%" + prenom.toLowerCase() + "%");
			predicates.add(prenomPredicate);
		}
		if (adresse != null ) {			

			Predicate adressePredicate = criteriaBuilder.like(client.get("adresse"), "%" + adresse.toLowerCase() + "%");
			predicates.add(adressePredicate);
		}
		if (cp != null ) {			

			Predicate cpPredicate = criteriaBuilder.like(client.get("cp"), "%" + cp.toLowerCase() + "%");
			predicates.add(cpPredicate);
		}
		if (localite != null ) {			

			Predicate localitePredicate = criteriaBuilder.like(client.get("localite"), "%" + localite.toLowerCase() + "%");
			predicates.add(localitePredicate);
		}
		// Alain in Client table field is lbanque		
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(client.get("lbanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		
		if (lienDis != null) {
			Predicate lienDisPredicate = criteriaBuilder.equal(client.get("lienDis"), lienDis);
			predicates.add(lienDisPredicate);
		}
				
		Predicate lienActifPredicate = criteriaBuilder.equal(client.get("actif"),actif);
		predicates.add(lienActifPredicate);
		
		if (isSuspect != null) {
			Predicate isSuspectPredicate = criteriaBuilder.equal(client.get("coeff"), 1);	
			if (isSuspect== true) {
				isSuspectPredicate = criteriaBuilder.notEqual(client.get("coeff"), 1);	
			}
			predicates.add(isSuspectPredicate);
		}

		clientQuery.where(predicates.stream().toArray(Predicate[]::new));
		clientQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), client, criteriaBuilder));

		TypedQuery<Client> query = entityManager.createQuery(clientQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Client.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Client> clients = query.getResultList();	
		
		
		return new PageImpl<>(clients, pageable, countResult);
	}

	@Override
    public Client save(Client Client) {   
		
        return ClientRepository.save(Client);
    }

    

    @Override
    @Transactional
    public void delete(int idClient) {
    	
    	ClientDependentRepository.deleteByLienMast(idClient);
		
        ClientRepository.deleteByIdClient(idClient);
        
    }

	
	
}
