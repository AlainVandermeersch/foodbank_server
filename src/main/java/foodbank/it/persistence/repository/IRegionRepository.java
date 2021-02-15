package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Region;

public interface IRegionRepository extends CrudRepository<Region, Integer>{
    Optional<Region> findByRegId(int regId);
    void deleteByRegId(int regId);
    Iterable<Region> findByBanqueObjectBankShortName( String bankShortName);
}