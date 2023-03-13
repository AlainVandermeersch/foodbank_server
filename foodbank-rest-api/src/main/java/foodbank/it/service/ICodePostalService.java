package foodbank.it.service;

import foodbank.it.persistence.model.CodePostal;

import java.util.Optional;

public interface ICodePostalService {
    Optional<CodePostal> findByZipCode(int zipCode);
    Iterable<CodePostal> findByLCpas(int lCpas);
    void deleteByZipCode(int zipCode);

    CodePostal save(CodePostal newCodePostal);

    void delete(Integer zipcode);
}
