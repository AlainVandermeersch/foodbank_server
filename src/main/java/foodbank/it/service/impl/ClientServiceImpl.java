package foodbank.it.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import foodbank.it.persistence.model.Banque;
import foodbank.it.service.SearchClientCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Client;
import foodbank.it.persistence.repository.IClientRepository;
import foodbank.it.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService{

	private final IClientRepository ClientRepository;
	private final EntityManager entityManager;
	
	public ClientServiceImpl(IClientRepository ClientRepository, EntityManager entityManager) {
        this.ClientRepository = ClientRepository;
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

		String searchField = searchCriteria.getSearchField();
		String searchValue = searchCriteria.getSearchValue();
		String bankShortName = searchCriteria.getBankShortName();
		Integer lienDis = searchCriteria.getLienDis();

		if (searchField != null && searchValue != null && !searchField.isEmpty() && !searchValue.isEmpty()) {
			Path<String> searchFieldPath = client.get(searchField);
			Expression<String> lowerSearchField = criteriaBuilder.lower(searchFieldPath);

			Predicate searchFieldPredicate = criteriaBuilder.like(lowerSearchField, "%" + searchValue.toLowerCase() + "%");
			predicates.add(searchFieldPredicate);
		}

		if (bankShortName != null && !bankShortName.isEmpty()) {
			Path<Banque> banqueObject = client.get("banqueObject");
			Path<String> bankShortNamePath = banqueObject.get("bankShortName");
			Expression<String> lowerBankShortName = criteriaBuilder.lower(bankShortNamePath);

			Predicate bankShortNamePredicate = criteriaBuilder.like(lowerBankShortName, "%" + bankShortName.toLowerCase() + "%");
			predicates.add(bankShortNamePredicate);
		}

		if (lienDis != null) {
			Predicate lienDisPredicate = criteriaBuilder.equal(client.get("lienDis"), lienDis);
			predicates.add(lienDisPredicate);
		}

		clientQuery.where(predicates.stream().toArray(Predicate[]::new));
		clientQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), client, criteriaBuilder));

		TypedQuery<Client> query = entityManager.createQuery(clientQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Client.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();

		List<Client> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}

	@Override
    public Client save(Client Client) {        
        return ClientRepository.save(Client);
    }

    

    @Override
    @Transactional
    public void delete(int idClient) {
        ClientRepository.deleteByIdClient(idClient);
        
    }

	@Override
	public Page<Client> findAll(Pageable pageable) {
		return ClientRepository.findAll(pageable);
	}
	@Override
	public Page<Client> findByBanqueObjectBankShortName(String bankShortName, Pageable pageable) {
		// TODO Auto-generated method stub
		return ClientRepository.findByBanqueObjectBankShortName(bankShortName, pageable);
	}
	@Override
	public Page<Client> findByLienDis(Integer lienDis, Pageable pageable) {
		return ClientRepository.findByLienDis(lienDis, pageable);
	}
	@Override
	public Page<Client> findByNomContaining(String search, Pageable pageRequest) {
		return  ClientRepository.findByNomContaining(search, pageRequest);
	}
	@Override
	public Page<Client> findByLienDisAndNomContaining(Integer lienDis, String search, Pageable pageRequest) {
		return  ClientRepository.findByLienDisAndNomContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Client> findByBanqueObjectBankShortNameAndNomContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return  ClientRepository.findByBanqueObjectBankShortNameAndNomContaining(bankShortName, search, pageRequest);
	}
	@Override
	public Page<Client> findByPrenomContaining(String search, Pageable pageRequest) {
		return  ClientRepository.findByPrenomContaining(search, pageRequest);
	}
	@Override
	public Page<Client> findByLienDisAndPrenomContaining(Integer lienDis, String search, Pageable pageRequest) {
		return  ClientRepository.findByLienDisAndPrenomContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Client> findByBanqueObjectBankShortNameAndPrenomContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return  ClientRepository.findByBanqueObjectBankShortNameAndPrenomContaining(bankShortName, search, pageRequest);
	}
	@Override
	public Page<Client> findByAdresseContaining(String search, Pageable pageRequest) {
		return  ClientRepository.findByAdresseContaining(search, pageRequest);
	}
	@Override
	public Page<Client> findByLienDisAndAdresseContaining(Integer lienDis, String search, Pageable pageRequest) {
		return  ClientRepository.findByLienDisAndAdresseContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Client> findByBanqueObjectBankShortNameAndAdresseContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return  ClientRepository.findByBanqueObjectBankShortNameAndAdresseContaining(bankShortName, search, pageRequest);
	}
	@Override
	public Page<Client> findByCpStartsWith(String search, Pageable pageRequest) {
		return  ClientRepository.findByCpStartsWith(search, pageRequest);
	}
	@Override
	public Page<Client> findByLienDisAndCpStartsWith(Integer lienDis, String search, Pageable pageRequest) {
		return  ClientRepository.findByLienDisAndCpStartsWith(lienDis, search, pageRequest);
	}
	@Override
	public Page<Client> findByBanqueObjectBankShortNameAndCpStartsWith(String bankShortName, String search,
			Pageable pageRequest) {
		return  ClientRepository.findByBanqueObjectBankShortNameAndCpStartsWith(bankShortName, search, pageRequest);
	}
	@Override
	public Page<Client> findByLocaliteContaining(String search, Pageable pageRequest) {
		return  ClientRepository.findByLocaliteContaining(search, pageRequest);
	}
	@Override
	public Page<Client> findByLienDisAndLocaliteContaining(Integer lienDis, String search, Pageable pageRequest) {
	return  ClientRepository.findByLienDisAndLocaliteContaining(lienDis, search, pageRequest);
	}
	@Override
	public Page<Client> findByBanqueObjectBankShortNameAndLocaliteContaining(String bankShortName, String search,
			Pageable pageRequest) {
		return  ClientRepository.findByBanqueObjectBankShortNameAndLocaliteContaining(bankShortName, search, pageRequest);
	}
	
	
}
