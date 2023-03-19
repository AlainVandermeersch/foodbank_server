package foodbank.it.service.impl;

import foodbank.it.persistence.model.CodePostal;
import foodbank.it.persistence.repository.ICodePostalRepository;
import foodbank.it.service.ICodePostalService;
import foodbank.it.service.SearchCodePostalCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CodePostalServiceImpl implements ICodePostalService {
    private final ICodePostalRepository CodePostalRepository;
    private final EntityManager entityManager;

    public CodePostalServiceImpl(ICodePostalRepository CodePostalRepository,EntityManager entityManager) {
        this.CodePostalRepository = CodePostalRepository;
        this.entityManager = entityManager;
    }
    @Override
    public Optional<CodePostal> findByZipCode(int zipCode) {
        return this.CodePostalRepository.findByZipCode(zipCode);
    }

    @Override
    public Iterable<CodePostal> findByLcpas(int lcpas) {
        return this.CodePostalRepository.findByLcpas(lcpas);
    }

    @Override
    @Transactional
    public void deleteByZipCode(int zipCode) {
        this.CodePostalRepository.deleteByZipCode(zipCode);
    }

    @Override
    public CodePostal save(CodePostal newCodePostal) {
       return this.CodePostalRepository.save(newCodePostal);
    }

    @Override
    @Transactional
    public void delete(Integer zipcode) {
        this.CodePostalRepository.deleteByZipCode(zipcode);
    }

    @Override
    public Page<CodePostal> findAll(SearchCodePostalCriteria searchCodePostalCriteria, Pageable pageRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CodePostal> codePostalQuery = criteriaBuilder.createQuery(CodePostal.class);
        Root<CodePostal> codePostal = codePostalQuery.from(CodePostal.class);
        List<Predicate> predicates = new ArrayList<>();
        Integer zipCode = searchCodePostalCriteria.getZipCode();
        if (zipCode != null) {
            Predicate zipCodePredicate = criteriaBuilder.equal(codePostal.get("zipCode"), zipCode);
            predicates.add(zipCodePredicate);
        }
        Integer zipCodecPAS = searchCodePostalCriteria.getZipCodeCpas();
        if (zipCodecPAS != null) {
            Predicate zipCodecPASPredicate = criteriaBuilder.equal(codePostal.get("zipCodeCpas"), zipCodecPAS);
            predicates.add(zipCodecPASPredicate);
        }
        String city = searchCodePostalCriteria.getCity();
        if (city != null) {
            Predicate cityPredicate = criteriaBuilder.like(codePostal.get("city"), "%" + city + "%");
            predicates.add(cityPredicate);
        }
        String cityCpas = searchCodePostalCriteria.getCityCpas();
        if (cityCpas != null) {
            Predicate cityCpasPredicate = criteriaBuilder.like(codePostal.get("cityCpas"), "%" +cityCpas + "%");
            predicates.add(cityCpasPredicate);
        }
        codePostalQuery.where(predicates.stream().toArray(Predicate[]::new));
        codePostalQuery.orderBy(QueryUtils.toOrders(pageRequest.getSort(), codePostal, criteriaBuilder));

        TypedQuery<CodePostal> query = entityManager.createQuery(codePostalQuery);
        query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        query.setMaxResults(pageRequest.getPageSize());

        CriteriaQuery<Long> totalCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        totalCriteriaQuery.where(predicates.stream().toArray(Predicate[]::new));
        totalCriteriaQuery.select(criteriaBuilder.count(totalCriteriaQuery.from(CodePostal.class)));
        TypedQuery<Long> countQuery = entityManager.createQuery(totalCriteriaQuery);
        long countResult = countQuery.getSingleResult();
        List<CodePostal> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageRequest, countResult);
    }
}
