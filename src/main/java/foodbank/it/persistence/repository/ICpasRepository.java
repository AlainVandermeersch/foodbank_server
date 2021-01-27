package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Cpas;

public interface ICpasRepository extends CrudRepository<Cpas, Integer>{
    Optional<Cpas> findByCpasId(int cpasId);
    Iterable<Cpas> findByCpasZip(String cpasZip);
    void deleteByCpasId(int cpasId);

}
