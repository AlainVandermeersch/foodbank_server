package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Organisation;

public interface IOrganisationService {
	Optional<Organisation> findByIdDis(int idDis);
	
	Organisation save(Organisation Organisation);

    void delete(int idDis);

	Iterable<Organisation> findByLienBanque(Short lienBanque);

}
