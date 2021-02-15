package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Cpas;
import foodbank.it.persistence.model.Membre;

public interface ICpasRepository extends PagingAndSortingRepository<Cpas, Integer>{
    Optional<Cpas> findByCpasId(int cpasId);
    Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest);
    Page<Cpas> findByCpasZipContaining(String search, Pageable pageRequest);
    void deleteByCpasId(int cpasId);

}
