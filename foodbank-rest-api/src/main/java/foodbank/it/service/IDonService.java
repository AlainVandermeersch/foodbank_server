package foodbank.it.service;

import foodbank.it.persistence.model.Don;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IDonService {
	
Optional<Don> findByIdDon(int idDon);
	
	Don save(Don don) ;

    Page<Don> findAll(SearchDonCriteria searchCriteria, Pageable pageable);	
   

    void delete(int idDon) throws Exception;

}
