package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.CodePostal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICodePostalRepository extends CrudRepository<CodePostal, Integer> {
    Optional<CodePostal> findByZipCode(int zipCode);
    Iterable<CodePostal> findBylCpas(int lCpas);
    void deleteByZipCode(int zipCode);
}
