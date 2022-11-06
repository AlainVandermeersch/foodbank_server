package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IClientRepository extends PagingAndSortingRepository<Client, Integer>{
    Optional<Client> findByIdClient(int idClient);
    void deleteByIdClient(int idClient);    
}
