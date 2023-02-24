package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Cpas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ICpasRepository extends PagingAndSortingRepository<Cpas, Integer>{
    Optional<Cpas> findByCpasId(int cpasId);
    Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest);
    Page<Cpas> findByCpasZipStartsWith(String search, Pageable pageRequest);
    Page<Cpas> findBylBanqueAndCpasNameContaining(short lBanque, String searchValue, Pageable pageRequest);

    Page<Cpas> findBylBanqueAndCpasZipStartsWith(short lBanque, String searchValue, Pageable pageRequest);
    Page<Cpas> findBylBanque(short lBanque, Pageable pageRequest);
    void deleteByCpasId(int cpasId);


}
