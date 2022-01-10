package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Region;

public interface IRegionService {
	Optional<Region> findByRegId(int regId);
	
	Iterable<Region> findByBankLink( Short bankLink);
	Iterable<Region> findByBankShortName( String  bankShortName);

    Region save(Region Region);

    Iterable<Region> findAll();

    void delete(int regId);


}