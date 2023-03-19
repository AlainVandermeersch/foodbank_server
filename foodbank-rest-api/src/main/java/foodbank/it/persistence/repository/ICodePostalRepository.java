package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.CodePostal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface ICodePostalRepository extends PagingAndSortingRepository<CodePostal, Integer> {
    Optional<CodePostal> findByZipCode(int zipCode);
    Iterable<CodePostal> findByLcpas(int lcpas);
    void deleteByZipCode(int zipCode);
    Page<CodePostal> findAll( Pageable pageRequest);
}
