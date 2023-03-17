package foodbank.it.service;

import foodbank.it.persistence.model.CodePostal;
import foodbank.it.persistence.model.Cpas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICodePostalService {
    Optional<CodePostal> findByZipCode(int zipCode);
    Iterable<CodePostal> findByLCpas(int lCpas);
    void deleteByZipCode(int zipCode);

    CodePostal save(CodePostal newCodePostal);

    void delete(Integer zipcode);

    Page<CodePostal> findAll(Pageable pageRequest);
}
