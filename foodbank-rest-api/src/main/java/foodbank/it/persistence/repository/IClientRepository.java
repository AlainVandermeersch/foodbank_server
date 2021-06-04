package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Client;

public interface IClientRepository extends PagingAndSortingRepository<Client, Integer>{
    Optional<Client> findByIdClient(int idClient);
    void deleteByIdClient(int idClient);    
}
