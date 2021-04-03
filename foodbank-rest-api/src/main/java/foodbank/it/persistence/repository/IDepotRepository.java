package foodbank.it.persistence.repository;

import java.util.Optional;

import foodbank.it.persistence.model.Depot;
import org.springframework.data.repository.CrudRepository;

public interface IDepotRepository extends CrudRepository<Depot, Integer>{
    Optional<Depot> findByIdDepot(String idDepot);
    void deleteByIdDepot(String idDepot);
    Iterable<Depot> findByNomContaining( String nom);
}
