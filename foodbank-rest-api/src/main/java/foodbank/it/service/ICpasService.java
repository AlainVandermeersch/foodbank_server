package foodbank.it.service;
import java.util.Optional;

import foodbank.it.persistence.model.Cpas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICpasService {
    Optional<Cpas> findByCpasId(int cpasId);
    Cpas save(Cpas Cpas);
    Page<Cpas> findAll(Pageable pageable);
    Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest);
    Page<Cpas> findByCpasZipStartsWith(String search, Pageable pageRequest);
    void delete(int cpasId);	
}
