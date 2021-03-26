package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Depot;

public interface IDepotRepository extends CrudRepository<Depot, Integer>{
    Optional<Depot> findByIdDepot(String idDepot);
    void deleteByIdDepot(String idDepot);
    Iterable<Depot> findByNomContaining( String nom);
}
