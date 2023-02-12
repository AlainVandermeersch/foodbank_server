package foodbank.it.service.impl;

import foodbank.it.persistence.model.Client;
import foodbank.it.persistence.repository.IClientDependentRepository;
import foodbank.it.persistence.repository.IClientRepository;
import foodbank.it.service.IClientService;
import foodbank.it.service.SearchClientCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
	private List<Predicate> createPredicatesForQuery(CriteriaBuilder criteriaBuilder, Root<Client> client, SearchClientCriteria searchCriteria) {

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
		String daten = searchCriteria.getDaten();


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
		if (daten != null ) {

			Predicate datenPredicate = criteriaBuilder.like(client.get("daten"), "%" + daten + "%");
			predicates.add(datenPredicate);
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
			if (isSuspect) {
				isSuspectPredicate = criteriaBuilder.notEqual(client.get("coeff"), 1);
			}
			predicates.add(isSuspectPredicate);
		}

		return predicates;
	}
	@Override public List<Client> findAll(SearchClientCriteria searchCriteria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Client> clientQuery = criteriaBuilder.createQuery(Client.class);
		Root<Client> client = clientQuery.from(Client.class);

		List<Predicate> predicates = createPredicatesForQuery(criteriaBuilder, client, searchCriteria);
		clientQuery.where(predicates.toArray(Predicate[]::new));
		String duplicate = searchCriteria.getDuplicate();
		if (duplicate == null ) {
			clientQuery.orderBy(criteriaBuilder.asc(client.get("nom")),criteriaBuilder.asc(client.get("prenom")));
			TypedQuery<Client> query = entityManager.createQuery(clientQuery);
			List<Client> resultList = query.getResultList();

			return resultList;
		}
		if (duplicate.equals("name") ) {

			clientQuery.orderBy(criteriaBuilder.asc(criteriaBuilder.upper(client.get("nom"))),
					criteriaBuilder.asc(criteriaBuilder.upper(client.get("prenom"))));
		}
		else {
			// must be search on duplicate birth dates
			Expression<String> stringToDateConverter = criteriaBuilder.function("STR_TO_DATE", String.class, client.get("daten"), criteriaBuilder.literal("%d/%m/%Y"));
			clientQuery.orderBy(criteriaBuilder.asc(stringToDateConverter));
		}
		TypedQuery<Client> query = entityManager.createQuery(clientQuery);
		List<Client> clients = query.getResultList();
		List<Client> duplicateClients = findDuplicateClients(clients, duplicate);
		return duplicateClients;
	}
	@Override public Page<Client> findPaged(SearchClientCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Client> clientQuery = criteriaBuilder.createQuery(Client.class);
		Root<Client> client = clientQuery.from(Client.class);

		List<Predicate> predicates = createPredicatesForQuery( criteriaBuilder, client, searchCriteria);

		clientQuery.where(predicates.toArray(Predicate[]::new));
		Expression<String> stringToDateConverter = criteriaBuilder.function("STR_TO_DATE", String.class, client.get("daten"), criteriaBuilder.literal("%d/%m/%Y"));
		String duplicate = searchCriteria.getDuplicate();
		if (duplicate == null ) {
			Sort sort = pageable.getSort();
			boolean isSortOnDaten= false;
			Sort.Direction sortDatenDirection = null;
			for (Sort.Order order : sort)
			{
				if (order.getProperty().equals("daten")) {
					isSortOnDaten= true;
					sortDatenDirection = order.getDirection();
					break;
				}
				System.out.println("Direction: " + order.getDirection());
			}
			if (isSortOnDaten) {

				if (sortDatenDirection.isAscending()) {
					clientQuery.orderBy(criteriaBuilder.asc(stringToDateConverter));
				}
				else {
					clientQuery.orderBy(criteriaBuilder.desc(stringToDateConverter));
				}
			}
			else {
				clientQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), client, criteriaBuilder));
			}
			TypedQuery<Client> query = entityManager.createQuery(clientQuery);
			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
			query.setMaxResults(pageable.getPageSize());

			CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
			totalCriteriaQuery.where(predicates.toArray(Predicate[]::new));
			totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Client.class)));
			TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
			long countResult = countQuery.getSingleResult();

			List<Client> clients = query.getResultList();


			return new PageImpl<>(clients, pageable, countResult);
		}
		else {
			if (duplicate.equals("name") ) {

				clientQuery.orderBy(criteriaBuilder.asc(criteriaBuilder.upper(client.get("nom"))),
						criteriaBuilder.asc(criteriaBuilder.upper(client.get("prenom"))));
			}
			else {
				// must be search on duplicate birth dates
				clientQuery.orderBy(criteriaBuilder.asc(stringToDateConverter));
			}
			TypedQuery<Client> query = entityManager.createQuery(clientQuery);
			List<Client> clients = query.getResultList();
			List<Client> duplicateClients = findDuplicateClients(clients, duplicate);

			int total = duplicateClients.size();
			int start = (int) pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), total);

			List<Client> output = new ArrayList<>();

			if (start <= end) {
				output = duplicateClients.subList(start, end);
			}

			return new PageImpl<>(
					output,
					pageable,
					total
			);


		}
	}
    private List<Client> findDuplicateClients(List<Client> clients, String duplicate) {
		List<Client> duplicateClients = new ArrayList<>();
		Client previousClient = null;

		for (Client nextClient : clients) {
			if (duplicate.equals("name") ) {
				if (previousClient == null || !previousClient.getNom().equalsIgnoreCase(nextClient.getNom())) {
					previousClient = nextClient;
					continue;
				}
			}
			else {
				// must be search on duplicate birth dates
				if (previousClient == null || !previousClient.getDaten().equalsIgnoreCase(nextClient.getDaten())) {
					previousClient = nextClient;
					continue;
				}
			}
			if (!duplicateClients.contains(previousClient)) {
				duplicateClients.add(previousClient);
			}
			duplicateClients.add(nextClient);
			previousClient = nextClient;

		}
		return duplicateClients;
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
