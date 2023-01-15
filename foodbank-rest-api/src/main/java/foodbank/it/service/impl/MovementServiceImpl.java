package foodbank.it.service.impl;

import foodbank.it.persistence.model.MovementDaily;
import foodbank.it.persistence.model.MovementDailyCount;
import foodbank.it.persistence.model.MovementMonthlyCount;
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
    public List<MovementMonthlyCount> findAllMonthly(SearchMovementCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementMonthlyCount> MovementMonthlyQuery = criteriaBuilder.createQuery(MovementMonthlyCount.class);
        Root<MovementMonthly> MovementMonthly = MovementMonthlyQuery.from(MovementMonthly.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementMonthly.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        MovementMonthlyQuery.where(predicates.stream().toArray(Predicate[]::new));
      
        MovementMonthlyQuery.multiselect(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"),
                MovementMonthly.get("category"),criteriaBuilder.sum(MovementMonthly.get("quantity")));
        MovementMonthlyQuery.groupBy(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("category"));
        MovementMonthlyQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")), criteriaBuilder.asc(MovementMonthly.get("category")));
        List<MovementMonthlyCount> results = entityManager.createQuery(MovementMonthlyQuery).getResultList();
        return results;
    }
    @Override
    public List<MovementDailyCount> findAllDaily(SearchMovementCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementDailyCount> MovementDailyQuery = criteriaBuilder.createQuery(MovementDailyCount.class);
        Root<MovementDaily> MovementDaily = MovementDailyQuery.from(MovementDaily.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementDaily.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        MovementDailyQuery.where(predicates.stream().toArray(Predicate[]::new));

        MovementDailyQuery.multiselect(MovementDaily.get("day"), MovementDaily.get("bankShortName"),
                MovementDaily.get("category"),criteriaBuilder.sum(MovementDaily.get("quantity")));
        MovementDailyQuery.groupBy(MovementDaily.get("day"), MovementDaily.get("bankShortName"), MovementDaily.get("category"));
        MovementDailyQuery.orderBy(criteriaBuilder.asc(MovementDaily.get("day")), criteriaBuilder.asc(MovementDaily.get("bankShortName")), criteriaBuilder.asc(MovementDaily.get("category")));
        List<MovementDailyCount> results = entityManager.createQuery(MovementDailyQuery).getResultList();
        return results;
    }


}
