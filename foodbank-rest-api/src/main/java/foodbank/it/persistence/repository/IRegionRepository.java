package foodbank.it.persistence.repository;

import java.util.Optional;

import foodbank.it.persistence.model.Region;
import org.springframework.data.repository.CrudRepository;

public interface IRegionRepository extends CrudRepository<Region, Integer>{
    Optional<Region> findByRegId(int regId);
    void deleteByRegId(int regId);
   	Iterable<Region> findByBankLink(Short bankLink);
	Iterable<Region> findByBankShortName(String bankShortName);
}