package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Organisation;

public interface IOrganisationService {
	Optional<Organisation> findByIdDis(int idDis);
	
	Iterable<Organisation> findByLienBanque(short lienBanque);

    Organisation save(Organisation Organisation);

    Iterable<Organisation> findAll();

    void delete(int idDis);


}
