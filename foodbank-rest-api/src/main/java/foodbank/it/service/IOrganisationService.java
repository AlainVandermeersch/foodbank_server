package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Organisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IOrganisationService {
	Optional<Organisation> findByIdDis(int idDis);
	
	Organisation save(Organisation Organisation);

    void delete(int idDis)  throws Exception;
	
	Page<Organisation> findAll(SearchOrganisationCriteria searchCriteria, Pageable pageable);

	Page<Organisation> findSummaries(SearchOrganisationSummariesCriteria searchCriteria, Pageable pageable);

}
