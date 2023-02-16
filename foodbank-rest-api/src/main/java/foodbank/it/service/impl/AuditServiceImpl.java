package foodbank.it.service.impl;

import foodbank.it.persistence.model.Audit;
import foodbank.it.persistence.model.AuditUser;
import foodbank.it.persistence.repository.IAuditRepository;
import foodbank.it.persistence.repository.ITUserRepository;
import foodbank.it.service.IAuditService;
import foodbank.it.service.SearchAuditCriteria;
import foodbank.it.web.dto.AuditReportDto;
import foodbank.it.web.dto.AuditUserDto;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AuditServiceImpl implements IAuditService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private IAuditRepository AuditRepository;
	private ITUserRepository TUserRepository;
	private final EntityManager entityManager;

	public AuditServiceImpl(IAuditRepository AuditRepository,
							ITUserRepository TUserRepository,
							EntityManager entityManager) {
		this.AuditRepository = AuditRepository;
		this.TUserRepository = TUserRepository;
		this.entityManager = entityManager;
	}

	@Override
	public Audit save(Audit auditnew) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Audit> auditQuery = criteriaBuilder.createQuery(Audit.class);
		Root<Audit> audit = auditQuery.from(Audit.class);
		List<Predicate> predicates = new ArrayList<>();
		String userId = auditnew.getUser();
		Predicate isUser = criteriaBuilder.equal(audit.get("user"), userId);
		predicates.add(isUser);
		Predicate isJavaAppPredicate = criteriaBuilder.equal(audit.get("application"), "FBIT");
		predicates.add(isJavaAppPredicate);
		Expression<String> parentExpression = criteriaBuilder.function("DATE_FORMAT", String.class, audit.get("dateIn"),
				criteriaBuilder.literal("%Y-%m-%d"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = LocalDate.now().format(formatter);
		
		Predicate hasLoggedinTodayPredicate = criteriaBuilder.equal(parentExpression,today);
		predicates.add(hasLoggedinTodayPredicate);
		auditQuery.where(predicates.stream().toArray(Predicate[]::new));
		log.debug("Querying if  User %s logged on today %s",userId,today);
		TypedQuery<Audit> query = entityManager.createQuery(auditQuery);
		
		List<Audit> resultList = query.getResultList();
				
		if (resultList.size() >0 ) {
			log.debug("User %s logged on already today %d times - not recording",userId, resultList.size());
			return auditnew;
		}
		log.debug("Recording  User %s logged on today %s\n",userId,today);
		return AuditRepository.save(auditnew);
	}
	private List<Predicate> createPredicatesForQuery(CriteriaBuilder criteriaBuilder,  Root<Audit> audit, SearchAuditCriteria searchCriteria) {
		List<Predicate> predicates = new ArrayList<>();
		Integer idDis = searchCriteria.getIdDis();
		String societe = searchCriteria.getSociete();
		String user = searchCriteria.getUser();
		String userName = searchCriteria.getUserName();
		String bankShortName = searchCriteria.getBankShortName();
		String rights = searchCriteria.getRights();
		String fromDateString = searchCriteria.getFromDate();
		String toDateString = searchCriteria.getToDate();
		Boolean isJavaApp = searchCriteria.getIsJavaApp();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (fromDateString != null) {
			LocalDateTime fromDate = LocalDate.parse(fromDateString, formatter).atStartOfDay();
			Predicate fromDatePredicate = criteriaBuilder.greaterThanOrEqualTo(audit.get("dateIn"), fromDate);
			predicates.add(fromDatePredicate);
		}
		if (toDateString != null) {
			LocalDateTime toDate = LocalDate.parse(toDateString, formatter).atStartOfDay();
			Predicate toDatePredicate = criteriaBuilder.lessThanOrEqualTo(audit.get("dateIn"), toDate);
			predicates.add(toDatePredicate);
		}

		if (societe != null) {

			Predicate societePredicate = criteriaBuilder.like(audit.get("societe"), "%" + societe.toLowerCase() + "%");
			predicates.add(societePredicate);
		}
		if (user != null) {

			Predicate userPredicate = criteriaBuilder.like(audit.get("user"), "%" + user.toLowerCase() + "%");
			predicates.add(userPredicate);
		}
		if (idDis != null) {
			Predicate idDisPredicate = criteriaBuilder.equal(audit.get("idDis"), idDis);
			predicates.add(idDisPredicate);
		}

		if (bankShortName != null) {
			Predicate bankShortNamePredicate = criteriaBuilder.equal(audit.get("bankShortName"), bankShortName);
			predicates.add(bankShortNamePredicate);
		}
		if (userName != null) {

			Predicate userNamePredicate = criteriaBuilder.like(audit.get("userName"),
					"%" + userName.toLowerCase() + "%");
			predicates.add(userNamePredicate);
		}

		if (rights != null) {

			Predicate rightsPredicate = criteriaBuilder.equal(audit.get("rights"), rights);
			predicates.add(rightsPredicate);
		}

		if (isJavaApp != null) {
			Predicate applicationPredicate = criteriaBuilder.isNull(audit.get("application"));
			if (isJavaApp == true) {
				applicationPredicate = criteriaBuilder.isNotNull(audit.get("application"));
			}
			predicates.add(applicationPredicate);
		}
		return predicates;
	}
	@Override
	public Page<Audit> findAll(SearchAuditCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Audit> auditQuery = criteriaBuilder.createQuery(Audit.class);
		Root<Audit> audit = auditQuery.from(Audit.class);
		List<Predicate> predicates = this.createPredicatesForQuery(criteriaBuilder, audit, searchCriteria);

		auditQuery.where(predicates.stream().toArray(Predicate[]::new));
		auditQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), audit, criteriaBuilder));

		TypedQuery<Audit> query = entityManager.createQuery(auditQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
		totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(Audit.class)));
		TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
		long countResult = countQuery.getSingleResult();
		List<Audit> resultList = query.getResultList();
		return new PageImpl<>(resultList, pageable, countResult);
	}

	@Override
	public Optional<Audit> findByAuditId(int auditId) {
		return AuditRepository.findById(auditId);
	}

	@Override
	@Transactional
	public void delete(int auditId) throws Exception {
		AuditRepository.deleteById(auditId);

	}
	@Override
	public List<AuditUserDto> reportUsers(String bankShortName, String fromDateString, String toDateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuditUser> auditQuery = criteriaBuilder.createQuery(AuditUser.class);
		Root<Audit> audit = auditQuery.from(Audit.class);
		List<Predicate> predicates = new ArrayList<>();

		if (bankShortName != null) {
			Predicate bankShortNamePredicate = criteriaBuilder.equal(audit.get("bankShortName"), bankShortName);
			predicates.add(bankShortNamePredicate);
		}
		if (fromDateString != null) {
			LocalDateTime fromDate = LocalDate.parse(fromDateString, formatter).atStartOfDay();
			Predicate fromDatePredicate = criteriaBuilder.greaterThanOrEqualTo(audit.get("dateIn"), fromDate);
			predicates.add(fromDatePredicate);
		}
		if (toDateString != null) {
			LocalDateTime toDate = LocalDate.parse(toDateString, formatter).atStartOfDay();
			Predicate toDatePredicate = criteriaBuilder.lessThanOrEqualTo(audit.get("dateIn"), toDate);
			predicates.add(toDatePredicate);
		}

		auditQuery.where(predicates.stream().toArray(Predicate[]::new));
		auditQuery.multiselect(audit.get("user"),audit.get("application"),
				criteriaBuilder.count(audit));
		auditQuery.groupBy(audit.get("user"), audit.get("application"));
		TypedQuery<AuditUser> query = entityManager.createQuery(auditQuery);
		List<AuditUser> userReports = query.getResultList();
		List<AuditUserDto> auditUserDtos = new ArrayList<AuditUserDto>();
		for (AuditUser auditUser : userReports) {
			AuditUserDto auditUserDto = auditUserDtos.stream()
					.filter(a -> a.getIdUser().equals(auditUser.getIdUser())).findFirst().orElse(null);
			if (auditUserDto == null) {
				auditUserDto = new AuditUserDto();
				auditUserDto.setLoginCountPHP(0L);
				auditUserDto.setLoginCountFBIT(0L);

			}
			auditUserDto.setIdUser(auditUser.getIdUser());
			if (auditUser.getApplication().equals("PHP")) {
				auditUserDto.setLoginCountPHP(auditUser.getLoginCount());
			}else {
				auditUserDto.setLoginCountFBIT(auditUser.getLoginCount());
			}
			AuditUserDto finalAuditUserDto = auditUserDto;
			TUserRepository.findByIdUser(auditUser.getIdUser()).ifPresent(tUser -> {
				finalAuditUserDto.setUserName(tUser.getUserName());
				finalAuditUserDto.setEmail(tUser.getEmail());
				finalAuditUserDto.setIdCompany(tUser.getIdCompany());
				finalAuditUserDto.setIdOrg(tUser.getIdOrg());
				finalAuditUserDto.setSociete(tUser.getSociete());
			});

			auditUserDtos.add(finalAuditUserDto);
		}
		return auditUserDtos;
	}

	@Override
	public List<AuditUserDto> findUsersPaged(SearchAuditCriteria criteria, Pageable pageRequest) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuditUser> auditUserQuery = criteriaBuilder.createQuery(AuditUser.class);
		Root<Audit> audit = auditUserQuery.from(Audit.class);
		List<Predicate> predicates = this.createPredicatesForQuery(criteriaBuilder,  audit, criteria);

		auditUserQuery.where(predicates.stream().toArray(Predicate[]::new));
		auditUserQuery.multiselect(audit.get("user"),audit.get("application"),
				audit.get("userName"),audit.get("bankShortName"),audit.get("idDis"),
				audit.get("societe"),audit.get("email"),audit.get("rights"),
				criteriaBuilder.count(audit));
		auditUserQuery.groupBy(audit.get("user"), audit.get("application"));
		String sortColumn = pageRequest.getSort().iterator().next().getProperty();
		if (!sortColumn.startsWith("loginCount")) {
			auditUserQuery.orderBy(QueryUtils.toOrders(pageRequest.getSort(), audit, criteriaBuilder));
		}
		TypedQuery<AuditUser> query = entityManager.createQuery(auditUserQuery);
		List<AuditUser> userReports = query.getResultList();
		List<AuditUserDto> auditUserDtos = new ArrayList<AuditUserDto>();
		for (AuditUser auditUser : userReports) {
			AuditUserDto auditUserDto = auditUserDtos.stream()
					.filter(a -> a.getIdUser().equals(auditUser.getIdUser())).findFirst().orElse(null);
			if (auditUserDto == null) {
				auditUserDto = new AuditUserDto();
				auditUserDto.setIdUser(auditUser.getIdUser());
				auditUserDto.setUserName(auditUser.getUserName());
				auditUserDto.setIdCompany(auditUser.getIdCompany());
				auditUserDto.setIdOrg(auditUser.getIdOrg());
				auditUserDto.setSociete(auditUser.getSociete());
				auditUserDto.setEmail(auditUser.getEmail());
				auditUserDto.setRights(auditUser.getRights());
				auditUserDto.setLoginCountPHP(0L);
				auditUserDto.setLoginCountFBIT(0L);
				auditUserDto.setTotalRecords(0L);
				auditUserDtos.add(auditUserDto);
			}

			if ((auditUser.getApplication() != null) && auditUser.getApplication().equals("FBIT")) {
				auditUserDto.setLoginCountFBIT(auditUser.getLoginCount());
			} else {
				auditUserDto.setLoginCountPHP(auditUser.getLoginCount());
			}
		}
		if (sortColumn.equals("loginCountPHP")) {
			auditUserDtos.sort(Comparator.comparing(AuditUserDto::getLoginCountPHP).reversed());
		} else if (sortColumn.equals("loginCountFBIT")) {
			auditUserDtos.sort(Comparator.comparing(AuditUserDto::getLoginCountFBIT).reversed());
		}
		List<AuditUserDto> pagedAuditUserDtos = new ArrayList<AuditUserDto>();
		int start = pageRequest.getPageNumber() * pageRequest.getPageSize();
		int end = Math.min((start + pageRequest.getPageSize()), auditUserDtos.size());
		for (int i = start; i < end; i++) {
			AuditUserDto auditUserDto = auditUserDtos.get(i);
			auditUserDto.setTotalRecords((long) auditUserDtos.size());
			pagedAuditUserDtos.add(auditUserDto);
		}

		return pagedAuditUserDtos;

	}

	@Override
	public List<AuditReportDto> report(String bankShortName, String fromDateString, String toDateString, String reportType) {
		// to confuse the enemy bankShortName which is the field of banque is
		// substituted here to bankShortName which is the audit class field
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuditReportDto> auditQuery = criteriaBuilder.createQuery(AuditReportDto.class);
		Root<Audit> audit = auditQuery.from(Audit.class);
		List<Predicate> predicates = new ArrayList<>();

		if (bankShortName != null && (reportType.equals( "general") ||reportType.equals( "history" ))) {
			Predicate bankShortNamePredicate = criteriaBuilder.equal(audit.get("bankShortName"), bankShortName);
			predicates.add(bankShortNamePredicate);
		}
		if (fromDateString != null) {
			LocalDateTime fromDate = LocalDate.parse(fromDateString, formatter).atStartOfDay();
			Predicate fromDatePredicate = criteriaBuilder.greaterThanOrEqualTo(audit.get("dateIn"), fromDate);
			predicates.add(fromDatePredicate);
		}
		if (toDateString != null) {
			LocalDateTime toDate = LocalDate.parse(toDateString, formatter).atStartOfDay();
			Predicate toDatePredicate = criteriaBuilder.lessThanOrEqualTo(audit.get("dateIn"), toDate);
			predicates.add(toDatePredicate);
		}
		switch(reportType) {
		
		case "history": 
			auditQuery.where(predicates.stream().toArray(Predicate[]::new));
			Expression<String> expression = criteriaBuilder.function("DATE_FORMAT", String.class, audit.get("dateIn"),
					criteriaBuilder.literal("%Y-%m-%d"));
			auditQuery.groupBy(expression, audit.get("application"));
			auditQuery.multiselect(expression.alias("key"), audit.get("application"), criteriaBuilder.count(audit));
			auditQuery.orderBy(criteriaBuilder.desc(expression), criteriaBuilder.asc(audit.get("application")));
			break;
		case "usage":
				Predicate idDisNotZeroPredicate = criteriaBuilder.notEqual(audit.get("idDis"), 0);
				predicates.add(idDisNotZeroPredicate);
				Predicate idDisNotNullPredicate = criteriaBuilder.isNotNull(audit.get("idDis"));
				predicates.add(idDisNotNullPredicate);
				auditQuery.where(predicates.stream().toArray(Predicate[]::new));
				auditQuery.groupBy(audit.get("bankShortName"),audit.get("idDis"), audit.get("societe"));
				auditQuery.multiselect(audit.get("bankShortName"), audit.get("societe"), criteriaBuilder.count(audit));
				auditQuery.orderBy(criteriaBuilder.asc(audit.get("bankShortName")),
						criteriaBuilder.asc(audit.get("societe")));
				break;
		case "rights":
			auditQuery.where(predicates.stream().toArray(Predicate[]::new));
			auditQuery.groupBy(audit.get("bankShortName"), audit.get("rights"));
			auditQuery.multiselect(audit.get("bankShortName"), audit.get("rights"), criteriaBuilder.count(audit));
			auditQuery.orderBy(criteriaBuilder.asc(audit.get("bankShortName")), criteriaBuilder.asc(audit.get("rights")));
			
			break;
		default: // must be general	
				auditQuery.where(predicates.stream().toArray(Predicate[]::new));
				if (bankShortName != null) {
					auditQuery.groupBy(audit.get("societe"), audit.get("application"));
					auditQuery.multiselect(audit.get("societe"), audit.get("application"),
							criteriaBuilder.count(audit));
					auditQuery.orderBy(criteriaBuilder.asc(audit.get("societe")),
							criteriaBuilder.asc(audit.get("application")));
				} else {
					auditQuery.groupBy(audit.get("bankShortName"), audit.get("application"));
					auditQuery.multiselect(audit.get("bankShortName"), audit.get("application"),
							criteriaBuilder.count(audit));
					auditQuery.orderBy(criteriaBuilder.asc(audit.get("bankShortName")),
							criteriaBuilder.asc(audit.get("application")));
				}
			
		}
		List<AuditReportDto> results = entityManager.createQuery(auditQuery).getResultList();

		return results;

	}

}
