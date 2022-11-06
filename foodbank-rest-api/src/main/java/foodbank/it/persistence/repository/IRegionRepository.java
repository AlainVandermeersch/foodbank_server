package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRegionRepository extends CrudRepository<Region, Integer>{
    Optional<Region> findByRegId(int regId);
    void deleteByRegId(int regId);
   	Iterable<Region> findByBankLink(Short bankLink);
	Iterable<Region> findByBankShortName(String bankShortName);
}