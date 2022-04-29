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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.AuditChange;
import foodbank.it.persistence.repository.IAuditChangeRepository;
import foodbank.it.service.IAuditChangeService;
import foodbank.it.service.SearchAuditChangeCriteria;


@Service
public class AuditChangeServiceImpl implements IAuditChangeService {

    private IAuditChangeRepository AuditChangeRepository;
    private final EntityManager entityManager;

    public AuditChangeServiceImpl(IAuditChangeRepository AuditChangeRepository, EntityManager entityManager) {
        this.AuditChangeRepository = AuditChangeRepository;
        this.entityManager = entityManager;
    }

    @Override
    public AuditChange save(AuditChange auditChangenew) {

        return AuditChangeRepository.save(auditChangenew);
    }

    @Override
    public Page<AuditChange> findAll(SearchAuditChangeCriteria searchCriteria, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditChange> auditChangeQuery = criteriaBuilder.createQuery(AuditChange.class);
        Root<AuditChange> auditChange = auditChangeQuery.from(AuditChange.class);
        List<Predicate> predicates = new ArrayList<>();

        Integer idDis = searchCriteria.getIdDis();
        String societe = searchCriteria.getSociete();
        String user = searchCriteria.getUser();
        String userName = searchCriteria.getUserName();
        String bankShortName = searchCriteria.getBankShortName();
        String entity = searchCriteria.getEntity();
        String entity_key = searchCriteria.getEntity_key();
        String action = searchCriteria.getAction();
        String fromDateString = searchCriteria.getFromDate();
        String toDateString = searchCriteria.getToDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (fromDateString != null) {
            LocalDateTime fromDate = LocalDate.parse(fromDateString, formatter).atStartOfDay();
            Predicate fromDatePredicate = criteriaBuilder.greaterThanOrEqualTo(auditChange.get("dateIn"), fromDate);
            predicates.add(fromDatePredicate);
        }
        if (toDateString != null) {
            LocalDateTime toDate = LocalDate.parse(toDateString, formatter).atStartOfDay();
            Predicate toDatePredicate = criteriaBuilder.lessThanOrEqualTo(auditChange.get("dateIn"), toDate);
            predicates.add(toDatePredicate);
        }

        if (societe != null) {

            Predicate societePredicate = criteriaBuilder.like(auditChange.get("societe"), "%" + societe.toLowerCase() + "%");
            predicates.add(societePredicate);
        }
        if (user != null) {

            Predicate userPredicate = criteriaBuilder.like(auditChange.get("user"), "%" + user.toLowerCase() + "%");
            predicates.add(userPredicate);
        }
        if (idDis != null) {
            Predicate idDisPredicate = criteriaBuilder.equal(auditChange.get("idDis"), idDis);
            predicates.add(idDisPredicate);
        }

        if (bankShortName != null) {
            Predicate bankShortNamePredicate = criteriaBuilder.equal(auditChange.get("bankShortName"), bankShortName);
            predicates.add(bankShortNamePredicate);
        }
        if (userName != null) {

            Predicate userNamePredicate = criteriaBuilder.like(auditChange.get("userName"),
                    "%" + userName.toLowerCase() + "%");
            predicates.add(userNamePredicate);
        }

        if (entity != null) {

            Predicate entityPredicate = criteriaBuilder.like(auditChange.get("entity"), "%" + entity.toLowerCase() + "%");
            predicates.add(entityPredicate);
        }
        if (entity_key != null) {

            Predicate entity_keyPredicate = criteriaBuilder.like(auditChange.get("entity_key"), "%" + entity_key.toLowerCase() + "%");
            predicates.add(entity_keyPredicate);
        }
        if (action != null) {

            Predicate actionPredicate = criteriaBuilder.like(auditChange.get("action"), "%" + action.toLowerCase() + "%");
            predicates.add(actionPredicate);
        }


        auditChangeQuery.where(predicates.stream().toArray(Predicate[]::new));
        auditChangeQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), auditChange, criteriaBuilder));

        TypedQuery<AuditChange> query = entityManager.createQuery(auditChangeQuery);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(AuditChange.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
        List<AuditChange> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, countResult);
    }

    @Override
    public Optional<AuditChange> findByAuditId(int auditChangeId) {
        return AuditChangeRepository.findById(auditChangeId);
    }

    @Override
    @Transactional
    public void delete(int auditId) throws Exception {
        AuditChangeRepository.deleteById(auditId);

    }



}

