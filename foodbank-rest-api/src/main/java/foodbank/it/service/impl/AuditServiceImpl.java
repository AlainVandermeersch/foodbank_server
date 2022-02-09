package foodbank.it.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Audit;
import foodbank.it.persistence.repository.IAuditRepository;
import foodbank.it.service.IAuditService;
import foodbank.it.service.SearchAuditCriteria;
import foodbank.it.web.dto.AuditReportDto;

@Service
public class AuditServiceImpl implements IAuditService {

	private IAuditRepository AuditRepository;
	private final EntityManager entityManager;

	public AuditServiceImpl(IAuditRepository AuditRepository, EntityManager entityManager) {
		this.AuditRepository = AuditRepository;
		this.entityManager = entityManager;
	}

	@Override
	public Audit save(Audit audit) {

		return AuditRepository.save(audit);
	}

	@Override
	public Page<Audit> findAll(SearchAuditCriteria searchCriteria, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Audit> auditQuery = criteriaBuilder.createQuery(Audit.class);
		Root<Audit> audit = auditQuery.from(Audit.class);
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
