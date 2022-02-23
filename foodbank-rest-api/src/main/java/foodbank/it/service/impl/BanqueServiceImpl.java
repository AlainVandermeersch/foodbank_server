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
import foodbank.it.web.dto.BanqueOrgReportDto;
import foodbank.it.web.dto.BanqueOrgCountDto;

@Service
public class BanqueServiceImpl implements IBanqueService {

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
			String errorMsg = String.format("There are %d Organisations with Bank id %d", countResult, bankId);
			throw new Exception(errorMsg);
		} else {
			BanqueRepository.deleteByBankId(bankId);
		}

	}

	@Override
	public Optional<Banque> findByBankShortName(String bankShortName) {
		// TODO Auto-generated method stub
		return BanqueRepository.findByBankShortName(bankShortName);
	}

	@Override
	public List<BanqueOrgCountDto> reportOrgCount(Boolean hasBirbCode) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BanqueOrgCountDto> organisationQuery = criteriaBuilder.createQuery(BanqueOrgCountDto.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate isActifPredicate = criteriaBuilder.equal(organisation.get("actif"), 1);
		predicates.add(isActifPredicate);
		Predicate isValidBankPredicate = criteriaBuilder.isNotNull(organisation.get("bankShortName"));
		predicates.add(isValidBankPredicate);
		if (hasBirbCode != null) {
			Predicate hasBirbCodePredicate = criteriaBuilder.greaterThan(organisation.get("birbCode"), 0);
			predicates.add(hasBirbCodePredicate);
		}
		organisationQuery.where(predicates.stream().toArray(Predicate[]::new));
		organisationQuery.groupBy(organisation.get("lienBanque"));
		organisationQuery.multiselect(organisation.get("bankShortName"), criteriaBuilder.count(organisation));
		organisationQuery.orderBy(criteriaBuilder.asc(organisation.get("bankShortName")));
		List<BanqueOrgCountDto> results = entityManager.createQuery(organisationQuery).getResultList();

		return results;
	}

	@Override
	public List<BanqueOrgReportDto> reportOrgs() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BanqueOrgReportDto> organisationQuery = criteriaBuilder.createQuery(BanqueOrgReportDto.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate isActifPredicate = criteriaBuilder.equal(organisation.get("actif"), 1);
		predicates.add(isActifPredicate);
		Predicate isValidBankPredicate = criteriaBuilder.isNotNull(organisation.get("bankShortName"));
		predicates.add(isValidBankPredicate);		
		organisationQuery.where(predicates.stream().toArray(Predicate[]::new));
		organisationQuery.groupBy(organisation.get("lienBanque"));
		organisationQuery.multiselect(organisation.get("bankShortName"), criteriaBuilder.count(organisation),
				criteriaBuilder.sum(organisation.get("nFam")),
				criteriaBuilder.sum(organisation.get("nPers")),
				criteriaBuilder.sum(organisation.get("nNour")),
				criteriaBuilder.sum(organisation.get("nBebe")),
				criteriaBuilder.sum(organisation.get("nEnf")),
				criteriaBuilder.sum(organisation.get("nAdo")),
				criteriaBuilder.sum(organisation.get("n1824")),
				criteriaBuilder.sum(organisation.get("nSen"))
				);
		organisationQuery.orderBy(criteriaBuilder.asc(organisation.get("bankShortName")));
		List<BanqueOrgReportDto> results = entityManager.createQuery(organisationQuery).getResultList();

		return results;
	}

}
