package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.Client;

public interface IClientRepository extends CrudRepository<Client, Integer>{
    Optional<Client> findByIdClient(int idClient);
    void deleteByIdClient(int idClient);
    Iterable<Client> findByBanqueObjectBankShortName( String bankShortName);
    Iterable<Client> findByLienDis(int lienDis);
}
