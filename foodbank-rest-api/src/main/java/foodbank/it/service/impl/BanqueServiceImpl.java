package foodbank.it.service.impl;

import foodbank.it.persistence.model.*;
import foodbank.it.persistence.repository.IBanqueRepository;
import foodbank.it.service.IBanqueService;
import foodbank.it.web.dto.BanqueFeadReportDto;
import foodbank.it.web.dto.BanqueOrgCountDto;
import foodbank.it.web.dto.BanqueClientReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BanqueServiceImpl implements IBanqueService {

	private IBanqueRepository BanqueRepository;
	private final EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

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
	public Iterable<Banque> findAll(Boolean classicBanks) {
		if (classicBanks != null) {
			return BanqueRepository.findByBankIdLessThan(11);
		}
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

		log.debug("Checking Organisation References to Banque with id: %d", bankId);

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
	

	private List<BanqueCount> countMembers(boolean bankCount) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BanqueCount> membreQuery = criteriaBuilder.createQuery(BanqueCount.class);
		Root<Membre> membre = membreQuery.from(Membre.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate isActifPredicate = criteriaBuilder.equal(membre.get("actif"), 1);
		predicates.add(isActifPredicate);
		Predicate isValidBankPredicate = criteriaBuilder.isNotNull(membre.get("bankShortName"));
		predicates.add(isValidBankPredicate);
		Predicate lienBanqueClassicPredicate = criteriaBuilder.lessThan(membre.get("lienBanque"), 11);
		predicates.add(lienBanqueClassicPredicate);
		Predicate lienBanqueNotNullPredicate = criteriaBuilder.greaterThanOrEqualTo(membre.get("lienBanque"), 1);
		predicates.add(lienBanqueNotNullPredicate);
		if (bankCount == true) {
			// selecting bank members only
			Predicate lienDisZero = criteriaBuilder.equal(membre.get("lienDis"), 0);
			Predicate lienDisNull = criteriaBuilder.isNull(membre.get("lienDis"));
			Predicate lienDisPredicate = criteriaBuilder.or(lienDisZero,lienDisNull);
			predicates.add(lienDisPredicate);
		}
		else {
			Predicate lienDisNotZero = criteriaBuilder.notEqual(membre.get("lienDis"), 0);
			Predicate lienDisNotNull = criteriaBuilder.isNotNull(membre.get("lienDis"));
			predicates.add(lienDisNotZero);
			predicates.add(lienDisNotNull);
		}
		membreQuery.where(predicates.stream().toArray(Predicate[]::new));
		membreQuery.groupBy(membre.get("lienBanque"));
		membreQuery.multiselect(membre.get("bankShortName"), criteriaBuilder.count(membre));
		membreQuery.orderBy(criteriaBuilder.asc(membre.get("bankShortName")));
		List<BanqueCount> results = entityManager.createQuery(membreQuery).getResultList();

		return results;
	}
	private List<UserCount> countUsers(boolean bankCount) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserCount> userQuery = criteriaBuilder.createQuery(UserCount.class);
		Root<TUser> user = userQuery.from(TUser.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate isActifPredicate = criteriaBuilder.equal(user.get("actif"), 1);
		predicates.add(isActifPredicate);
		Predicate isValidBankPredicate = criteriaBuilder.isNotNull(user.get("idCompany"));
		predicates.add(isValidBankPredicate);

		if (bankCount == true) {
			// selecting bank members only
			Predicate idOrgZero = criteriaBuilder.equal(user.get("idOrg"), 0);
			Predicate idOrgNull = criteriaBuilder.isNull(user.get("idOrg"));
			Predicate idOrgPredicate = criteriaBuilder.or(idOrgZero,idOrgNull);
			predicates.add(idOrgPredicate);
		}
		else {
			Predicate idOrgNotZero = criteriaBuilder.notEqual(user.get("idOrg"), 0);
			Predicate idOrgNotNull = criteriaBuilder.isNotNull(user.get("idOrg"));
			predicates.add(idOrgNotZero);
			predicates.add(idOrgNotNull);
		}
		userQuery.where(predicates.stream().toArray(Predicate[]::new));
		userQuery.groupBy(user.get("idCompany"));
		userQuery.multiselect(user.get("idCompany"), criteriaBuilder.count(user));
		userQuery.orderBy(criteriaBuilder.asc(user.get("idCompany")));
		List<UserCount> results = entityManager.createQuery(userQuery).getResultList();

		return results;
	}
	@Override
	public List<BanqueOrgCountDto> reportMembreCount() {
	    List<BanqueCount> bankCounts = this.countMembers(true);
		List<BanqueCount> orgCounts = this.countMembers(false);
		List<BanqueOrgCountDto> bankOrgcounts = new ArrayList<BanqueOrgCountDto>();
		for (BanqueCount bankCount : bankCounts) {
			boolean foundMatch = false;
			for (BanqueCount orgCount : orgCounts) {
				if (bankCount.getBankShortName().equals(orgCount.getBankShortName())) {
					bankOrgcounts.add(new BanqueOrgCountDto(bankCount.getBankShortName(), bankCount.getCount(), orgCount.getCount()));
					foundMatch = true;
					break;
				}
			}
			if (foundMatch == false) {
				bankOrgcounts.add(new BanqueOrgCountDto(bankCount.getBankShortName(), bankCount.getCount(), 0L));
			}
		}

		return bankOrgcounts;

	}
	@Override
	public List<BanqueOrgCountDto> reportTUserCount() {
		List<UserCount> bankCounts = this.countUsers(true);
		List<UserCount> orgCounts = this.countUsers(false);
		List<BanqueOrgCountDto> bankOrgcounts = new ArrayList<BanqueOrgCountDto>();
		for (UserCount bankCount : bankCounts) {
			boolean foundMatch = false;
			for (UserCount orgCount : orgCounts) {
				if (bankCount.getIdCompany().equals(orgCount.getIdCompany())) {
					bankOrgcounts.add(new BanqueOrgCountDto(bankCount.getIdCompany(), bankCount.getCount(), orgCount.getCount()));
					foundMatch = true;
					break;
				}
			}
			if (foundMatch == false) {
				bankOrgcounts.add(new BanqueOrgCountDto(bankCount.getIdCompany(), bankCount.getCount(), 0L));
			}
		}

		return bankOrgcounts;

	}

	@Override
	public List<BanqueFeadReportDto> reportOrgFead() {
		List<BanqueCount> bankOrgCounts = this.reportOrgCount(null);
		List<BanqueCount> bankAgreedCounts = this.reportOrgCount("AGREED");
		List<BanqueCount> bankFeadCounts = this.reportOrgCount("FEAD");
		List<BanqueCount> bankFeadFromUsCounts = this.reportOrgCount("FEADFROMUS");
		List<BanqueFeadReportDto> bankFeadReports = new ArrayList<BanqueFeadReportDto>();
		for (BanqueCount bankOrgCount : bankOrgCounts) {
			BanqueFeadReportDto bankFeadReport = new BanqueFeadReportDto(bankOrgCount.getBankShortName());
            bankFeadReport.setOrgCount(bankOrgCount.getCount());
			for (BanqueCount bankAgreedCount : bankAgreedCounts) {
				if (bankOrgCount.getBankShortName().equals(bankAgreedCount.getBankShortName())) {
					bankFeadReport.setOrgAgreedCount(bankAgreedCount.getCount());
					break;
				}
			}
			for (BanqueCount bankFeadCount : bankFeadCounts) {
				if (bankOrgCount.getBankShortName().equals(bankFeadCount.getBankShortName())) {
					bankFeadReport.setOrgFeadCount(bankFeadCount.getCount());
					break;
				}
			}
			for (BanqueCount bankFeadFromUsCount : bankFeadFromUsCounts) {
				if (bankOrgCount.getBankShortName().equals(bankFeadFromUsCount.getBankShortName())) {
					bankFeadReport.setOrgFeadFromUsCount(bankFeadFromUsCount.getCount());
					break;
				}
			}
			bankFeadReports.add( bankFeadReport);

		}

		return bankFeadReports;
	}


	@Override
	public List<BanqueCount> reportOrgCount(String filter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BanqueCount> organisationQuery = criteriaBuilder.createQuery(BanqueCount.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate isActifPredicate = criteriaBuilder.equal(organisation.get("actif"), 1);
		predicates.add(isActifPredicate);
		Predicate isValidBankPredicate = criteriaBuilder.isNotNull(organisation.get("bankShortName"));
		predicates.add(isValidBankPredicate);
		Predicate lienBanqueClassicPredicate = criteriaBuilder.lessThan(organisation.get("lienBanque"), 11);
		predicates.add(lienBanqueClassicPredicate);
		Predicate lienBanqueNotNullPredicate = criteriaBuilder.greaterThanOrEqualTo(organisation.get("lienBanque"), 1);
		predicates.add(lienBanqueNotNullPredicate);
		// exclude depots
		Predicate isDepotPredicate = criteriaBuilder.equal(organisation.get("depyN"), 0);
		predicates.add(isDepotPredicate);
		if (filter != null) {
			if (filter.equals("FEAD")) {
				Predicate hasBirbCodePredicate = criteriaBuilder.greaterThan(organisation.get("birbCode"), 0);
				predicates.add(hasBirbCodePredicate);
			}
			// Agreed if field daten 0
			if (filter.equals("AGREED")) {
				Predicate isAgreedPredicate = criteriaBuilder.equal(organisation.get("daten"), 0);
				predicates.add(isAgreedPredicate);
			}
			if (filter.equals("FEADFROMUS")) {
				Predicate isfeadNPredicate = criteriaBuilder.equal(organisation.get("feadN"), 1);
				predicates.add(isfeadNPredicate);
			}
		}
		organisationQuery.where(predicates.stream().toArray(Predicate[]::new));
		organisationQuery.groupBy(organisation.get("lienBanque"));
		organisationQuery.multiselect(organisation.get("bankShortName"), criteriaBuilder.count(organisation));
		organisationQuery.orderBy(criteriaBuilder.asc(organisation.get("bankShortName")));
		List<BanqueCount> results = entityManager.createQuery(organisationQuery).getResultList();

		return results;
	}

	@Override
	public List<BanqueClientReportDto> reportOrgClients() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BanqueClientReportDto> organisationQuery = criteriaBuilder.createQuery(BanqueClientReportDto.class);
		Root<Organisation> organisation = organisationQuery.from(Organisation.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate isActifPredicate = criteriaBuilder.equal(organisation.get("actif"), 1);
		predicates.add(isActifPredicate);
		Predicate isValidBankPredicate = criteriaBuilder.isNotNull(organisation.get("bankShortName"));
		predicates.add(isValidBankPredicate);
		Predicate lienBanqueClassicPredicate = criteriaBuilder.lessThan(organisation.get("lienBanque"), 11);
		predicates.add(lienBanqueClassicPredicate);
		Predicate lienBanqueNotNullPredicate = criteriaBuilder.greaterThanOrEqualTo(organisation.get("lienBanque"), 1);
		predicates.add(lienBanqueNotNullPredicate);
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
		List<BanqueClientReportDto> results = entityManager.createQuery(organisationQuery).getResultList();

		return results;
	}

}
