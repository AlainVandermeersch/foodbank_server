package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Depot;

public interface IDepotService {
	Optional<Depot> findByIdDepot(String idDepot);
	
	Iterable<Depot> findByBanqueObjectBankShortName( String bankShortName);

    Depot save(Depot Depot);

    Iterable<Depot> findAll();

    void delete(String idDepot);


}
