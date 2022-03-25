package foodbank.it.service.impl;

import foodbank.it.persistence.model.TypeEmploi;
import foodbank.it.persistence.repository.ITypeEmploiRepository;
import foodbank.it.service.ITypeEmploiService;
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
public class TypeEmploiServiceImpl implements ITypeEmploiService {
    private ITypeEmploiRepository TypeEmploiRepository;
    private final EntityManager entityManager;

    public TypeEmploiServiceImpl(ITypeEmploiRepository typeEmploiRepository,
                                 EntityManager entityManager
    ) {
        this.TypeEmploiRepository = typeEmploiRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<TypeEmploi> findByJobNr(int jobNr) {
        return this.TypeEmploiRepository.findByJobNr(jobNr);
    }

    @Override
    public TypeEmploi save(TypeEmploi typeEmploi) {
        return this.TypeEmploiRepository.save(typeEmploi);
    }

    @Override
    public Iterable<TypeEmploi> findAll(Integer lienBanque,Boolean actif) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TypeEmploi> typeEmploiQuery = criteriaBuilder.createQuery(TypeEmploi.class);
        Root<TypeEmploi> typeEmploi = typeEmploiQuery.from(TypeEmploi.class);
        List<Predicate> predicates = new ArrayList<>();
        if (lienBanque != null) {
            Predicate lienBanquePredicate = criteriaBuilder.equal(typeEmploi.get("lienBanque"), lienBanque);
            predicates.add(lienBanquePredicate);
        }
        if (actif != null) {
            Integer intActive = 0;
            if (actif) {
                intActive = 1;
            }
            Predicate isActifPredicate = criteriaBuilder.equal(typeEmploi.get("actif"), intActive);
            predicates.add(isActifPredicate);
        }
        typeEmploiQuery.where(predicates.stream().toArray(Predicate[]::new));
        typeEmploiQuery.orderBy(criteriaBuilder.asc(typeEmploi.get("jobNr")));
        TypedQuery<TypeEmploi> query = entityManager.createQuery(typeEmploiQuery);
        List<TypeEmploi> resultList = query.getResultList();
        return resultList;


    }

    @Override
    @Transactional
    public void delete(int jobNr) throws Exception {
        this.TypeEmploiRepository.delete(jobNr);
    }
}
