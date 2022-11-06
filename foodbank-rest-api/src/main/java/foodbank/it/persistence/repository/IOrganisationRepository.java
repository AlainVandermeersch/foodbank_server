package foodbank.it.persistence.repository;


import foodbank.it.persistence.model.Organisation;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IOrganisationRepository extends PagingAndSortingRepository<Organisation, Integer>{
    Optional<Organisation> findByIdDis(int idDis);
    void deleteByIdDis(int idDis);
    Iterable<Organisation> findByLienBanque(Short lienBanque);
}
