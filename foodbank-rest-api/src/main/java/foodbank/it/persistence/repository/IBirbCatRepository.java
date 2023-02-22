package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.BirbCat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IBirbCatRepository extends CrudRepository<BirbCat, Integer> {
    Optional<BirbCat> findByBirbId(int birbId);
    void deleteByBirbId(int birbId);
}
