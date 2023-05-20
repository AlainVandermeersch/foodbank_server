package foodbank.it.service.impl;

import foodbank.it.persistence.model.*;
import foodbank.it.persistence.repository.IDepotRepository;
import foodbank.it.service.IMovementService;

import foodbank.it.service.SearchMovementCriteria;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class MovementServiceImpl implements IMovementService {

    private final EntityManager entityManager;

    public MovementServiceImpl( EntityManager entityManager) {
                this.entityManager = entityManager;
    }



    @Override
    public List<MovementDaily> findDailyMovements(SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();
        Integer idDepot = searchCriteria.getIdDepot();
        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();
        Integer lastDays = searchCriteria.getLastDays();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementDaily> MovementDailyQuery = criteriaBuilder.createQuery(MovementDaily.class);
        Root<MovementDaily> MovementDaily = MovementDailyQuery.from(MovementDaily.class);
        List<Predicate> predicates = new ArrayList<>();
        if (lastDays != null) {
            LocalDate today = LocalDate.now();
            LocalDate lastDaysDate = today.minusDays(lastDays);
            Predicate lastDaysPredicate = criteriaBuilder.greaterThanOrEqualTo(MovementDaily.get("day"), lastDaysDate);
            predicates.add(lastDaysPredicate);
        }

        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementDaily.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        if (idCompany != null) {
            Predicate idCompanyPredicate = criteriaBuilder.equal(MovementDaily.get("bankShortName"), idCompany);
            predicates.add(idCompanyPredicate);
        }
        if (idDepot != null) {
            Predicate idDepotPredicate = criteriaBuilder.equal(MovementDaily.get("lienDepot"), idDepot);
            predicates.add(idDepotPredicate);
        }

        if (lowRange != null) {
            LocalDate lowRangeDate = LocalDate.parse(lowRange);
            Predicate lowRangePredicate = criteriaBuilder.greaterThanOrEqualTo(MovementDaily.get("day"), lowRangeDate);
            predicates.add(lowRangePredicate);
        }
        if (highRange != null) {
            LocalDate highRangeDate = LocalDate.parse(highRange);
            Predicate highRangePredicate = criteriaBuilder.lessThanOrEqualTo(MovementDaily.get("day"), highRangeDate);
            predicates.add(highRangePredicate);
        }
        MovementDailyQuery.where(predicates.stream().toArray(Predicate[]::new));

        MovementDailyQuery.orderBy(criteriaBuilder.asc(MovementDaily.get("day")), criteriaBuilder.asc(MovementDaily.get("bankShortName")), criteriaBuilder.desc(MovementDaily.get("quantity")));
        List<MovementDaily> results = entityManager.createQuery(MovementDailyQuery).getResultList();
        return results;
    }

    @Override
    public List<MovementMonthly> findMonthlyMovements(SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();
        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();
        Integer idDepot = searchCriteria.getIdDepot();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementMonthly> MovementMonthlyQuery = criteriaBuilder.createQuery(MovementMonthly.class);
        Root<MovementMonthly> MovementMonthly = MovementMonthlyQuery.from(MovementMonthly.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementMonthly.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        if (idCompany != null) {
            Predicate idCompanyPredicate = criteriaBuilder.equal(MovementMonthly.get("bankShortName"), idCompany);
            predicates.add(idCompanyPredicate);
        }
        if (idDepot != null) {
            Predicate idDepotPredicate = criteriaBuilder.equal(MovementMonthly.get("lienDepot"), idDepot);
            predicates.add(idDepotPredicate);
        }

        if (lowRange != null) {
            Predicate lowRangePredicate = criteriaBuilder.greaterThanOrEqualTo(MovementMonthly.get("month"), lowRange);
            predicates.add(lowRangePredicate);
        }
        if (highRange != null) {
            Predicate highRangePredicate = criteriaBuilder.lessThanOrEqualTo(MovementMonthly.get("month"), highRange);
            predicates.add(highRangePredicate);
        }
        MovementMonthlyQuery.where(predicates.stream().toArray(Predicate[]::new));

        MovementMonthlyQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")), criteriaBuilder.desc(MovementMonthly.get("quantity")));
        List<MovementMonthly> results = entityManager.createQuery(MovementMonthlyQuery).getResultList();
        return results;
    }


}
