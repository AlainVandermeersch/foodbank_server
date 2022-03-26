package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.TypeEmploi;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITypeEmploiRepository extends CrudRepository<TypeEmploi, Integer> {
    Optional<TypeEmploi> findByJobNr(int jobNr);
    TypeEmploi save(TypeEmploi TypeEmploi);
    Iterable<TypeEmploi> findAll();
    void deleteByJobNr(int jobNr) throws Exception;
}
