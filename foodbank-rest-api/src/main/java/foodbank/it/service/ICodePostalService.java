package foodbank.it.service;

import foodbank.it.persistence.model.CodePostal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICodePostalService {
    Optional<CodePostal> findByZipCode(int zipCode);
    Iterable<CodePostal> findByLcpas(int lcpas);
    void deleteByZipCode(int zipCode);

    CodePostal save(CodePostal newCodePostal);

    void delete(Integer zipcode);

    Page<CodePostal> findAll(SearchCodePostalCriteria searchCodePostalCriteria, Pageable pageRequest);
}
