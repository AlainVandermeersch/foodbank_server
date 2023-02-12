package foodbank.it.service;

import foodbank.it.persistence.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IClientService {
	Optional<Client> findByIdClient(int idClient);	
	Page<Client> findPaged(SearchClientCriteria searchCriteria, Pageable pageable);
    List<Client> findAll(SearchClientCriteria searchCriteria);
    Client save(Client Client);
    void delete(int idClient);





}
