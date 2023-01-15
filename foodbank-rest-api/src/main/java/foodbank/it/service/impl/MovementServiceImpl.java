package foodbank.it.service.impl;

import foodbank.it.persistence.model.MovementSummaryCount;
import foodbank.it.persistence.model.MovementMonthly;
import foodbank.it.service.IMovementService;

import foodbank.it.service.SearchMovementCriteria;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class MovementServiceImpl implements IMovementService {

    private final EntityManager entityManager;

    public MovementServiceImpl( EntityManager entityManager) {
                this.entityManager = entityManager;
    }
    @Override
    public List<MovementSummaryCount> findAll(SearchMovementCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementSummaryCount> MovementMonthlyQuery = criteriaBuilder.createQuery(MovementSummaryCount.class);
        Root<MovementMonthly> MovementMonthly = MovementMonthlyQuery.from(MovementMonthly.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementMonthly.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        MovementMonthlyQuery.where(predicates.stream().toArray(Predicate[]::new));
      
        MovementMonthlyQuery.multiselect(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"),
                MovementMonthly.get("category"),criteriaBuilder.sum(MovementMonthly.get("quantity")));
        MovementMonthlyQuery.groupBy(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("category"));
        MovementMonthlyQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")), criteriaBuilder.asc(MovementMonthly.get("category")));
        List<MovementSummaryCount> results = entityManager.createQuery(MovementMonthlyQuery).getResultList();
        return results;
    }



}
