package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Don;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDonService {
	
Optional<Don> findByIdDon(int idDon);
	
	Don save(Don don) ;

    Page<Don> findAll(SearchDonCriteria searchCriteria, Pageable pageable);	
   

    void delete(int idDon) throws Exception;

}
