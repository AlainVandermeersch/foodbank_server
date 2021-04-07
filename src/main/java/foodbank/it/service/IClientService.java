package foodbank.it.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import foodbank.it.persistence.model.Client;

public interface IClientService {
	Optional<Client> findByIdClient(int idClient);	
	Page<Client> findAll(SearchClientCriteria searchCriteria, Pageable pageable);	    
    Client save(Client Client);
    void delete(int idClient);
	

}
