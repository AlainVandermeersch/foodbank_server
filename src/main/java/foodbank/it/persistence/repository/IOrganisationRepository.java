package foodbank.it.persistence.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Organisation;

public interface IOrganisationRepository extends CrudRepository<Organisation, Integer>{
    Optional<Organisation> findByIdDis(int idDis);
    void deleteByIdDis(int idDis);
    Iterable<Organisation> findByLienBanque(short lienBanque);
}
