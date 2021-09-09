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

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.Organisation;
import foodbank.it.persistence.repository.IBanqueRepository;
import org.springframework.stereotype.Service;

import foodbank.it.service.IBanqueService;
@Service
public class BanqueServiceImpl implements IBanqueService{
    
    private IBanqueRepository BanqueRepository;
    private final EntityManager entityManager;

    public BanqueServiceImpl(IBanqueRepository BanqueRepository, EntityManager entityManager) {
        this.BanqueRepository = BanqueRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Banque> findByBankId(int bankId) {
        return BanqueRepository.findByBankId(bankId);
    }

    @Override
    public Banque save(Banque Banque) {
       
        return BanqueRepository.save(Banque);
    }

    @Override
    public Iterable<Banque> findAll() {
        return BanqueRepository.findAll();
    }
    @Override
    public Iterable<Banque> findByActif(short actif) {
        return BanqueRepository.findByActif(actif);
    }
    @Override
    @Transactional
    public void delete(int bankId) throws Exception {
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
    	Root<Organisation> organisation = totalCriteriaQuery.from(Organisation.class);
		List<Predicate> predicates = new ArrayList<>();
		Predicate lienBankPredicate = criteriaBuilder.equal(organisation.get("lienBanque"), bankId);
		predicates.add(lienBankPredicate);
	
		System.out.printf("\nChecking Organisation References to Banque with id: %d", bankId);
		
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(organisation));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		Long countResult = countQuery.getSingleResult();
		

		if (countResult > 0) {
			String errorMsg = String.format("There are %d Organisations with Bank id %d",countResult, bankId);		
			throw new Exception(errorMsg);
		}
		else {
			BanqueRepository.deleteByBankId(bankId);
		}
        
    }

	@Override
	public Optional<Banque> findByBankShortName(String bankShortName) {
		// TODO Auto-generated method stub
		return BanqueRepository.findByBankShortName(bankShortName);
	}
    

}
