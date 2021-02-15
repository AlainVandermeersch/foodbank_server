package foodbank.it.service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.Cpas;

public interface ICpasService {
    Optional<Cpas> findByCpasId(int cpasId);
    Cpas save(Cpas Cpas);
    Page<Cpas> findAll(Pageable pageable);
    Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest);
    Page<Cpas> findByCpasZipContaining(String search, Pageable pageRequest);
    void delete(int cpasId);	
}
