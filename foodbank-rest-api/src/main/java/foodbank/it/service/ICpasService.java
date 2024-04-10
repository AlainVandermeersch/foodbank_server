package foodbank.it.service;

import foodbank.it.persistence.model.Cpas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICpasService {
    Optional<Cpas> findByCpasId(int cpasId);
    Cpas save(Cpas Cpas);
    Page<Cpas> findAll(Pageable pageable);
    Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest);
    Page<Cpas> findByCpasZipStartsWith(String search, Pageable pageRequest);
    Page<Cpas> findByLbanque(short lbanque, Pageable pageRequest);
    void delete(int cpasId);

    Page<Cpas> findByLbanqueAndCpasNameContaining(short parseShort, String searchValue, Pageable pageRequest);

    Page<Cpas> findByLbanqueAndCpasZipStartsWith(short parseShort, String searchValue, Pageable pageRequest);
}
