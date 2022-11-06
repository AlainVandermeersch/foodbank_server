package foodbank.it.service.impl;

import foodbank.it.persistence.model.Function;
import foodbank.it.persistence.repository.IFunctionRepository;
import foodbank.it.service.IFunctionService;
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
public class FunctionServiceImpl implements IFunctionService {
    private IFunctionRepository FunctionRepository;
    private final EntityManager entityManager;

    public FunctionServiceImpl(IFunctionRepository functionRepository,
                               EntityManager entityManager
    ) {
        this.FunctionRepository = functionRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Function> findByFuncId(int funcId) {
        return this.FunctionRepository.findByFuncId(funcId);
    }

    @Override
    public Function save(Function function,boolean booCreateMode) {
        return this.FunctionRepository.save(function);
    }

    @Override
    public Iterable<Function> findAll(Integer lienBanque,Boolean actif,String language) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Function> functionQuery = criteriaBuilder.createQuery(Function.class);
        Root<Function> function = functionQuery.from(Function.class);
        List<Predicate> predicates = new ArrayList<>();
        if (lienBanque != null) {
            if (lienBanque > 0) {
                Predicate lienBanqueSpecificPredicate = criteriaBuilder.equal(function.get("lienBanque"), lienBanque);
                Predicate lienBanqueZeroPredicate = criteriaBuilder.equal(function.get("lienBanque"), 0);
                Predicate lienBanquePredicate = criteriaBuilder.or(lienBanqueSpecificPredicate, lienBanqueZeroPredicate);
                predicates.add(lienBanquePredicate);
            } else {
                Predicate lienBanqueZeroPredicate = criteriaBuilder.equal(function.get("lienBanque"), 0);
                predicates.add(lienBanqueZeroPredicate);
            }
        }
        if (actif != null) {
            Integer intActive = 0;
            if (actif) {
                intActive = 1;
            }
            Predicate isActifPredicate = criteriaBuilder.equal(function.get("actif"), intActive);
            predicates.add(isActifPredicate);
        }
        functionQuery.where(predicates.stream().toArray(Predicate[]::new));
        if (language.equals("fr")) {
            functionQuery.orderBy(criteriaBuilder.asc(function.get("fonctionName")));
        }
        else {
            functionQuery.orderBy(criteriaBuilder.asc(function.get("fonctionNameNl")));
        }
        TypedQuery<Function> query = entityManager.createQuery(functionQuery);

        List<Function> resultList = query.getResultList();
        return resultList;
    }
    @Override
    @Transactional
    public void deleteByFuncId(int funcId) throws Exception {
        this.FunctionRepository.deleteByFuncId(funcId);
    }
}
