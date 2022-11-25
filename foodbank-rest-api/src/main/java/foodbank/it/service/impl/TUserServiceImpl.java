package foodbank.it.service.impl;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.IBanqueRepository;
import foodbank.it.persistence.repository.ITUserRepository;
import foodbank.it.service.ITUserService;
import foodbank.it.service.SearchTUserCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TUserServiceImpl implements ITUserService {

	private ITUserRepository TUserRepository;
	private IBanqueRepository BanqueRepository;
	private final EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public TUserServiceImpl(ITUserRepository TUserRepository,IBanqueRepository BanqueRepository, EntityManager entityManager) {
		this.TUserRepository = TUserRepository;
		this.BanqueRepository = BanqueRepository;
		this.entityManager = entityManager;

	}

	@Override
	public Optional<TUser> findByIdUser(String idUser) {
		return TUserRepository.findByIdUser(idUser);
	}

	@Override
	public TUser save(TUser tuser, boolean booCreateMode) throws Exception {
		// backed Out BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (booCreateMode == true) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<TUser> existingTUser = totalCriteriaQuery.from(TUser.class);
			List<Predicate> predicates = new ArrayList<>();
			Predicate idUserPredicate = criteriaBuilder.equal(existingTUser.get("idUser"), tuser.getIdUser());
			predicates.add(idUserPredicate);
			totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
			totalCriteriaQuery.select(criteriaBuilder.count(existingTUser));
			TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
			Long countResult = countQuery.getSingleResult();

			if (countResult > 0) {
				String errorMsg = String.format("a user exists with userId: %s", tuser.getIdUser());
				throw new Exception(errorMsg);
			}
			// Backed Out tuser.setPassword(encoder.encode(tuser.getPassword()));
		}
		Optional<Banque> userBanque= BanqueRepository.findByBankShortName(tuser.getIdCompany());
		userBanque.ifPresent(myBanque ->tuser.setLienBanque((short) myBanque.getBankId()));
		return TUserRepository.save(tuser);
	}

	@Override
	@Transactional
	public void delete(String idUser) {
		TUserRepository.deleteByIdUser(idUser);
	}

	@Override
	public Page<TUser> findAll(SearchTUserCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<TUser> tuserQuery = criteriaBuilder.createQuery(TUser.class);
		Root<TUser> tuser = tuserQuery.from(TUser.class);
		List<Predicate> predicates = new ArrayList<>();

		String idUser = searchCriteria.getIdUser();
		String userName = searchCriteria.getUserName();
		Boolean actif = searchCriteria.getActif();
		Boolean droit1 = searchCriteria.getDroit1();
		Boolean gestMemb = searchCriteria.getGestMemb();
		Boolean gestBen = searchCriteria.getGestBen();
		Boolean gestFead = searchCriteria.getGestFead();
		Boolean gestDon = searchCriteria.getGestDon();
		String idLanguage = searchCriteria.getIdLanguage();
		String email = searchCriteria.getEmail();
		String rights = searchCriteria.getRights();
		Integer lienBanque = searchCriteria.getLienBanque();
		Integer idOrg = searchCriteria.getIdOrg();
		Integer lienDepot = searchCriteria.getLienDepot();
		String idCompany = searchCriteria.getIdCompany();
		Boolean hasLogins = searchCriteria.getHasLogins();
		String hasAnomalies = searchCriteria.getHasAnomalies();
		Boolean classicBanks = searchCriteria.getClassicBanks();

		if (idUser != null) {

			Predicate idUserPredicate = criteriaBuilder.like(tuser.get("idUser"), "%" + idUser.toLowerCase() + "%");
			predicates.add(idUserPredicate);
		}
		if (userName != null) {

			Predicate userNamePredicate = criteriaBuilder.like(tuser.<String>get("userName"),
					"%" + userName.toLowerCase() + "%");
			predicates.add(userNamePredicate);
		}

		if (email != null) {

			Predicate emailPredicate = criteriaBuilder.like(tuser.<String>get("email"),
					"%" + email.toLowerCase() + "%");
			predicates.add(emailPredicate);
		}
		if (idLanguage != null) {
			Predicate languePredicate = criteriaBuilder.equal(tuser.<Short>get("idLanguage"), idLanguage);
			predicates.add(languePredicate);
		}
		if (rights != null) {

			Predicate rightsPredicate = criteriaBuilder.equal(tuser.get("rights"), rights);
			predicates.add(rightsPredicate);
		}
		if (idCompany != null) {
			if (idCompany.equals("???"))
			{
				Predicate idCompanyNullPredicate = criteriaBuilder.isNull(tuser.get("idCompany"));
				Predicate idCompanyEmptyPredicate = criteriaBuilder.equal(tuser.get("idCompany"),"");
				Predicate idCompanyNullOrEmptyPredicate = criteriaBuilder.or(idCompanyNullPredicate,idCompanyEmptyPredicate);
				predicates.add(idCompanyNullOrEmptyPredicate);
			}
			else {
				Predicate idCompanyPredicate = criteriaBuilder.equal(tuser.get("idCompany"), idCompany);
				predicates.add(idCompanyPredicate);
			}
		}
		if (lienBanque != null) {
			Predicate lienBanquePredicate = criteriaBuilder.equal(tuser.get("lienBanque"), lienBanque);
			predicates.add(lienBanquePredicate);
		}
		if (idOrg != null) {
			log.debug("Checking Users with idOrg: %d", idOrg);
			if (idOrg == 0) {
				// selecting bank members only
				Predicate idOrgZero = criteriaBuilder.equal(tuser.get("idOrg"), 0);
				Predicate idOrgNull = criteriaBuilder.isNull(tuser.get("idOrg"));
				Predicate idOrgPredicate = criteriaBuilder.or(idOrgZero, idOrgNull);
				predicates.add(idOrgPredicate);
			} else {
				if (idOrg == 999) {
					// exclude members of bank who have idOrg 0 or null
					Predicate idOrgNotZero = criteriaBuilder.notEqual(tuser.get("idOrg"), 0);
					Predicate idOrgNotNull = criteriaBuilder.isNotNull(tuser.get("idOrg"));
					predicates.add(idOrgNotZero);
					predicates.add(idOrgNotNull);
				} else {
					Predicate idOrgPredicate = criteriaBuilder.equal(tuser.get("idOrg"), idOrg);
					predicates.add(idOrgPredicate);
				}
			}
		}

		if (lienDepot != null) {
			Predicate lienDepotPredicate = criteriaBuilder.equal(tuser.get("lienDepot"), lienDepot);
			predicates.add(lienDepotPredicate);
		}

		if (actif != null) {
			Integer intActive = 0;
			if (actif == true) {
				intActive = 1;
			}
			Predicate isActifPredicate = criteriaBuilder.equal(tuser.get("actif"), intActive);
			predicates.add(isActifPredicate);
		}
		if (droit1 != null) {
			Integer intDroit1 = 0;
			if (droit1 == true) {
				intDroit1 = 1;
			}
			Predicate isDroit1Predicate = criteriaBuilder.equal(tuser.get("droit1"), intDroit1);
			predicates.add(isDroit1Predicate);
		}
		if (gestMemb != null) {
			Integer intGestMemb = 0;
			if (gestMemb == true) {
				intGestMemb = 1;
			}
			Predicate isGestMembPredicate = criteriaBuilder.equal(tuser.get("gestMemb"), intGestMemb);
			predicates.add(isGestMembPredicate);
		}
		if (gestBen != null) {
			Integer intGestBen = 0;
			if (gestBen == true) {
				intGestBen = 1;
			}
			Predicate isGestBenPredicate = criteriaBuilder.equal(tuser.get("gestBen"), intGestBen);
			predicates.add(isGestBenPredicate);
		}
		if (gestFead != null) {
			Integer intGestFead = 0;
			if (gestFead == true) {
				intGestFead = 1;
			}
			Predicate isGestFeadPredicate = criteriaBuilder.equal(tuser.get("gestFead"), intGestFead);
			predicates.add(isGestFeadPredicate);
		}
		if (gestDon != null) {
			Integer intGestDon = 0;
			if (gestDon == true) {
				intGestDon = 1;
			}
			Predicate isGestDonPredicate = criteriaBuilder.equal(tuser.get("gestDon"), intGestDon);
			predicates.add(isGestDonPredicate);
		}
		if (hasLogins != null) {
			Predicate hasLoginsPredicate = criteriaBuilder.equal(tuser.get("nbLogins"), 0);
			if (hasLogins == true) {
				hasLoginsPredicate = criteriaBuilder.gt(tuser.get("nbLogins"), 0);
			}
			predicates.add(hasLoginsPredicate);
		}
		if (classicBanks != null) {
			Predicate lienBanqueClassicPredicate = criteriaBuilder.lessThan(tuser.get("lienBanque"), 11);
			predicates.add(lienBanqueClassicPredicate);
		}
		if (hasAnomalies != null) {
			if (hasAnomalies.equals("2")) {
				Predicate memberFoundPredicate = criteriaBuilder.isNotNull(tuser.get("membreNom"));
				predicates.add(memberFoundPredicate);
				Expression<String> e = criteriaBuilder.concat(tuser.get("membreNom"), " ");
				e = criteriaBuilder.concat(e, tuser.get("membrePrenom"));
				Predicate notEqualUserNamePredicate = criteriaBuilder.notEqual(tuser.get("userName"), e);
				predicates.add(notEqualUserNamePredicate);
			} else if (hasAnomalies.equals("3")) {
				Predicate memberFoundPredicate = criteriaBuilder.isNotNull(tuser.get("membreNom"));
				predicates.add(memberFoundPredicate);
				Predicate notEqualEmailPredicate = criteriaBuilder.notEqual(tuser.get("email"), tuser.get("membreEmail"));
				predicates.add(notEqualEmailPredicate);
			}
			else if (hasAnomalies.equals("4")) {
				Predicate memberFoundPredicate = criteriaBuilder.isNotNull(tuser.get("membreNom"));
				predicates.add(memberFoundPredicate);
				Predicate notEqualCompanyPredicate = criteriaBuilder.notEqual(tuser.get("idCompany"), tuser.get("membreBankShortname"));
				predicates.add(notEqualCompanyPredicate);
			}
			else { 
				Predicate memberNotFoundPredicate = criteriaBuilder.isNull(tuser.get("membreNom"));
				predicates.add(memberNotFoundPredicate);
			}

		}
		tuserQuery.where(predicates.stream().toArray(Predicate[]::new));
		tuserQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), tuser, criteriaBuilder));

		TypedQuery<TUser> query = entityManager.createQuery(tuserQuery);
		long countResult = query.getResultList().size(); // todo: delete this expensive statement and replace it by a
															// count query
		// as commented out below if I can make it work with a join
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		/*
		 * CriteriaQuery<Long> totalCriteriaQuery =
		 * criteriaBuilder.createQuery(Long.class); tuser =
		 * totalCriteriaQuery.from(TUser.class); membre = tuser.join("membreObject");
		 * totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		 * totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(TUser
		 * .class))); TypedQuery<Long> countQuery =
		 * entityManager.createQuery(totalCriteriaQuery); long countResult =
		 * countQuery.getSingleResult();
		 */

		List<TUser> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);

	}

	@Override
	public Iterable<TUser> findAll() {
		return TUserRepository.findAll();
	}

	@Override
	public Iterable<TUser> findByLienBanque(Short lienBanque) {
		return TUserRepository.findByLienBanque(lienBanque);
	}
	@Override
	public Iterable<TUser> findByLienBat(int lienBat) {
		return TUserRepository.findByLienBat(lienBat);
	}

	@Override
	public Iterable<TUser> findByIdOrg(Integer idOrgInteger) {
		return TUserRepository.findByIdOrg(idOrgInteger);
	}

}
