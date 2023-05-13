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
    private IDepotRepository DepotRepository;

    public MovementServiceImpl( EntityManager entityManager, IDepotRepository DepotRepository) {
                this.entityManager = entityManager;
                this.DepotRepository = DepotRepository;
    }
    @Override
    public List<MovementMonthlyCountbyBank> findMonthlyByBank(SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();
        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementMonthlyCountbyBank> MovementMonthlyQuery = criteriaBuilder.createQuery(MovementMonthlyCountbyBank.class);
        Root<MovementMonthly> MovementMonthly = MovementMonthlyQuery.from(MovementMonthly.class);


        List<Predicate> predicates = new ArrayList<>();
        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementMonthly.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        if (idCompany != null) {
            Predicate idCompanyPredicate = criteriaBuilder.equal(MovementMonthly.get("bankShortName"), idCompany);
            predicates.add(idCompanyPredicate);
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
      
        MovementMonthlyQuery.multiselect(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"),
                MovementMonthly.get("category"),criteriaBuilder.sum(MovementMonthly.get("quantity")),criteriaBuilder.sum(MovementMonthly.get("nfamilies")),criteriaBuilder.sum(MovementMonthly.get("npersons")));
        MovementMonthlyQuery.groupBy(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("category"));
        MovementMonthlyQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")), criteriaBuilder.asc(MovementMonthly.get("category")));
        List<MovementMonthlyCountbyBank> results = entityManager.createQuery(MovementMonthlyQuery).getResultList();
        return results;
    }
    @Override
    public List<MovementMonthlyCountbyBankDepot> findMonthlyByBankDepot(SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();
        Integer idDepot = searchCriteria.getIdDepot();
        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementMonthlyCountbyBankDepot> MovementMonthlyQuery = criteriaBuilder.createQuery(MovementMonthlyCountbyBankDepot.class);
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
        if (idDepot != null) {
            MovementMonthlyQuery.multiselect(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"),
                    MovementMonthly.get("category"), MovementMonthly.get("idOrg"), MovementMonthly.get("orgname"),
                    criteriaBuilder.sum(MovementMonthly.get("quantity")),
                    criteriaBuilder.sum(MovementMonthly.get("nfamilies")),criteriaBuilder.sum(MovementMonthly.get("npersons")));
            MovementMonthlyQuery.groupBy(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("idOrg"), MovementMonthly.get("category"));
            MovementMonthlyQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")), criteriaBuilder.asc(MovementMonthly.get("category")), criteriaBuilder.desc(MovementMonthly.get("quantity")));
            List<MovementMonthlyCountbyBankDepot> results = entityManager.createQuery(MovementMonthlyQuery).getResultList();
            return results;

        } else {
            MovementMonthlyQuery.multiselect(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("category"),  MovementMonthly.get("lienDepot"),
                    criteriaBuilder.sum(MovementMonthly.get("quantity")),criteriaBuilder.countDistinct(MovementMonthly.get("idOrg")),
                    criteriaBuilder.sum(MovementMonthly.get("nfamilies")),criteriaBuilder.sum(MovementMonthly.get("npersons")));
            MovementMonthlyQuery.groupBy(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("lienDepot"), MovementMonthly.get("category"));
            MovementMonthlyQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")),
                    criteriaBuilder.asc(MovementMonthly.get("lienDepot")), criteriaBuilder.asc(MovementMonthly.get("category")));

            List<MovementMonthlyCountbyBankDepot> results = entityManager.createQuery(MovementMonthlyQuery).getResultList();
            results.stream().filter(result -> result.getIdOrg() != null).forEach(result -> {
                Depot depot = DepotRepository.findByIdDepot(result.getIdOrg().toString()).orElse(null);
                if (depot != null) {
                    result.setOrgname(depot.getNom());
                }
            });

            return results;
        }

    }

    @Override
    public List<MovementSummaryByIdOrg> findSummaryByIdOrg(boolean byMonth, SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();
        Integer idDepot = searchCriteria.getIdDepot();
        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementSummaryByIdOrg> MovementQuery = criteriaBuilder.createQuery(MovementSummaryByIdOrg.class);
        Root<MovementMonthly> MovementMonthly = MovementQuery.from(MovementMonthly.class);
        Root<MovementDaily> MovementDaily = MovementQuery.from(MovementDaily.class);
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
        MovementQuery.where(predicates.stream().toArray(Predicate[]::new));
        if (byMonth) {
            MovementQuery.multiselect(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"),
                    MovementMonthly.get("category"),MovementMonthly.get("lienDepot"),MovementMonthly.get("idOrg"), MovementMonthly.get("orgname"),
                    criteriaBuilder.sum(MovementMonthly.get("quantity")),
                    criteriaBuilder.avg(MovementMonthly.get("nfamilies")),criteriaBuilder.avg(MovementMonthly.get("npersons")));
            MovementQuery.groupBy(MovementMonthly.get("month"), MovementMonthly.get("bankShortName"), MovementMonthly.get("category"), MovementMonthly.get("lienDepot"),MovementMonthly.get("idOrg"));
            MovementQuery.orderBy(criteriaBuilder.asc(MovementMonthly.get("month")), criteriaBuilder.asc(MovementMonthly.get("bankShortName")), criteriaBuilder.asc(MovementMonthly.get("category")), criteriaBuilder.desc(MovementMonthly.get("quantity")));

        } else {
            MovementQuery.multiselect(MovementDaily.get("day"), MovementDaily.get("bankShortName"),
                    MovementDaily.get("category"),MovementDaily.get("lienDepot"),MovementDaily.get("idOrg"), MovementDaily.get("orgname"),
                    criteriaBuilder.sum(MovementDaily.get("quantity")),
                    criteriaBuilder.max(MovementDaily.get("nfamilies")),criteriaBuilder.max(MovementDaily.get("npersons")));
            // max get nfamilies and npersons because each record contains thhe nbfamilies and persons of the org
            MovementQuery.groupBy(MovementDaily.get("day"), MovementDaily.get("bankShortName"), MovementDaily.get("category"), MovementDaily.get("lienDepot"),MovementDaily.get("idOrg"));
            MovementQuery.orderBy(criteriaBuilder.asc(MovementDaily.get("day")), criteriaBuilder.asc(MovementDaily.get("bankShortName")), criteriaBuilder.asc(MovementDaily.get("category")), criteriaBuilder.desc(MovementDaily.get("quantity")));

        }
        List<MovementSummaryByIdOrg> results = entityManager.createQuery(MovementQuery).getResultList();
        return results;
    }

    @Override
    public List<MovementDailyCountByBank> findDailyByBank(SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();

        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();
        Integer lastDays = searchCriteria.getLastDays();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementDailyCountByBank> MovementDailyQuery = criteriaBuilder.createQuery(MovementDailyCountByBank.class);
        Root<MovementDaily> MovementDaily = MovementDailyQuery.from(MovementDaily.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate isValidBankPredicate = criteriaBuilder.isNotNull(MovementDaily.get("bankShortName"));
        predicates.add(isValidBankPredicate);
        if (idCompany != null) {
            Predicate idCompanyPredicate = criteriaBuilder.equal(MovementDaily.get("bankShortName"), idCompany);
            predicates.add(idCompanyPredicate);
        }
        if (lowRange != null) {
            Predicate lowRangePredicate = criteriaBuilder.greaterThanOrEqualTo(MovementDaily.get("day"), lowRange);
            predicates.add(lowRangePredicate);
        }
        if (highRange != null) {
            Predicate highRangePredicate = criteriaBuilder.lessThanOrEqualTo(MovementDaily.get("day"), highRange);
            predicates.add(highRangePredicate);
        }
        if (lastDays != null) {
            LocalDate today = LocalDate.now();
            LocalDate lastDaysDate = today.minusDays(lastDays);
            Predicate lastDaysPredicate = criteriaBuilder.greaterThanOrEqualTo(MovementDaily.get("day"), lastDaysDate);
            predicates.add(lastDaysPredicate);
        }
        MovementDailyQuery.where(predicates.stream().toArray(Predicate[]::new));
        MovementDailyQuery.multiselect(MovementDaily.get("day"), MovementDaily.get("bankShortName"),
                MovementDaily.get("category"),criteriaBuilder.sum(MovementDaily.get("quantity")),criteriaBuilder.sum(MovementDaily.get("nfamilies")),criteriaBuilder.sum(MovementDaily.get("npersons")));
        MovementDailyQuery.groupBy(MovementDaily.get("day"), MovementDaily.get("bankShortName"), MovementDaily.get("category"));
        MovementDailyQuery.orderBy(criteriaBuilder.asc(MovementDaily.get("day")), criteriaBuilder.asc(MovementDaily.get("bankShortName")), criteriaBuilder.asc(MovementDaily.get("category")));

        List<MovementDailyCountByBank> results = entityManager.createQuery(MovementDailyQuery).getResultList();

        return results;
    }

    @Override
    public List<MovementDailyCountByBankDepot> findDailyByBankDepot(SearchMovementCriteria searchCriteria) {
        String idCompany = searchCriteria.getIdCompany();
        Integer idDepot = searchCriteria.getIdDepot();
        String lowRange = searchCriteria.getLowRange();
        String highRange = searchCriteria.getHighRange();
        Integer lastDays = searchCriteria.getLastDays();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovementDailyCountByBankDepot> MovementDailyQuery = criteriaBuilder.createQuery(MovementDailyCountByBankDepot.class);
        Root<MovementDaily> MovementDaily = MovementDailyQuery.from(MovementDaily.class);

        List<Predicate> predicates = new ArrayList<>();
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
            Predicate lowRangePredicate = criteriaBuilder.greaterThanOrEqualTo(MovementDaily.get("day"), lowRange);
            predicates.add(lowRangePredicate);
        }
        if (highRange != null) {
            Predicate highRangePredicate = criteriaBuilder.lessThanOrEqualTo(MovementDaily.get("day"), highRange);
            predicates.add(highRangePredicate);
        }
        if (lastDays != null) {
            LocalDate today = LocalDate.now();
            LocalDate lastDaysDate = today.minusDays(lastDays);
            Predicate lastDaysPredicate = criteriaBuilder.greaterThanOrEqualTo(MovementDaily.get("day"), lastDaysDate);
            predicates.add(lastDaysPredicate);
        }
        MovementDailyQuery.where(predicates.stream().toArray(Predicate[]::new));
        if (idDepot != null) {
            MovementDailyQuery.multiselect(MovementDaily.get("day"), MovementDaily.get("bankShortName"),MovementDaily.get("category"),
                    MovementDaily.get("idOrg"), MovementDaily.get("orgname"),criteriaBuilder.sum(MovementDaily.get("quantity")),
                    criteriaBuilder.sum(MovementDaily.get("nfamilies")),criteriaBuilder.sum(MovementDaily.get("npersons")));
            MovementDailyQuery.groupBy(MovementDaily.get("day"), MovementDaily.get("bankShortName"), MovementDaily.get("lienDepot"), MovementDaily.get("category"));
            MovementDailyQuery.orderBy(criteriaBuilder.asc(MovementDaily.get("day")), criteriaBuilder.asc(MovementDaily.get("bankShortName")),
                    criteriaBuilder.asc(MovementDaily.get("category")),criteriaBuilder.desc(MovementDaily.get("quantity")));
            List<MovementDailyCountByBankDepot> results = entityManager.createQuery(MovementDailyQuery).getResultList();
            return results;
        } else {

            MovementDailyQuery.multiselect(MovementDaily.get("day"), MovementDaily.get("bankShortName"), MovementDaily.get("category"),MovementDaily.get("lienDepot"),
                     criteriaBuilder.sum(MovementDaily.get("quantity")),
                    criteriaBuilder.sum(MovementDaily.get("nfamilies")),criteriaBuilder.sum(MovementDaily.get("npersons")));
            MovementDailyQuery.groupBy(MovementDaily.get("day"), MovementDaily.get("bankShortName"), MovementDaily.get("lienDepot"), MovementDaily.get("category"));
            MovementDailyQuery.orderBy(criteriaBuilder.asc(MovementDaily.get("day")), criteriaBuilder.asc(MovementDaily.get("bankShortName")), criteriaBuilder.asc(MovementDaily.get("lienDepot")), criteriaBuilder.asc(MovementDaily.get("category")));

            List<MovementDailyCountByBankDepot> results = entityManager.createQuery(MovementDailyQuery).getResultList();
            results.stream().filter(result -> result.getIdOrg() != null).forEach(result -> {
                Depot depot = DepotRepository.findByIdDepot(result.getIdOrg().toString()).orElse(null);
                if (depot != null) {
                    result.setOrgname(depot.getNom());
                }
            });

            return results;
        }
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
