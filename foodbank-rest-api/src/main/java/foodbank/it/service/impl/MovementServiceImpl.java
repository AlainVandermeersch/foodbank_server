package foodbank.it.service.impl;

import foodbank.it.persistence.model.MovementBankCount;
import foodbank.it.persistence.model.MovementSummary;
import foodbank.it.service.IMovementService;
import foodbank.it.persistence.repository.IMovementSummaryRepository;

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
    private IMovementSummaryRepository movementSummaryRepository;
    private final EntityManager entityManager;

    public MovementServiceImpl(IMovementSummaryRepository movementSummaryRepository, EntityManager entityManager) {
        this.movementSummaryRepository = movementSummaryRepository;
        this.entityManager = entityManager;
    }
    @Override
    public List<MovementBankCount> findAll(SearchMovementCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementBankCount> movementSummaryQuery = criteriaBuilder.createQuery(MovementBankCount.class);
        Root<MovementSummary> movementSummary = movementSummaryQuery.from(MovementSummary.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(movementSummary.get("bankShortName"));
        predicates.add(isValidBankPredicate);


        movementSummaryQuery.where(predicates.stream().toArray(Predicate[]::new));
        movementSummaryQuery.groupBy(movementSummary.get("month"));
        movementSummaryQuery.groupBy(movementSummary.get("bankShortName"));
        movementSummaryQuery.groupBy(movementSummary.get("category"));
        movementSummaryQuery.multiselect(movementSummary.get("month"), movementSummary.get("bankShortName"),
                movementSummary.get("category"),criteriaBuilder.sum(movementSummary.get("quantity")));
        movementSummaryQuery.groupBy(movementSummary.get("month"), movementSummary.get("bankShortName"), movementSummary.get("category"));
        movementSummaryQuery.orderBy(criteriaBuilder.asc(movementSummary.get("month")), criteriaBuilder.asc(movementSummary.get("bankShortName")), criteriaBuilder.asc(movementSummary.get("category")));
        List<MovementBankCount> results = entityManager.createQuery(movementSummaryQuery).getResultList();
        return results;
    }



}
