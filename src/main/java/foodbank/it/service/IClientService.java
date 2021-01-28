package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Client;

public interface IClientService {
	Optional<Client> findByIdClient(int idClient);
	
	Iterable<Client> findByBanqueObjectBankShortName( String bankShortName);

    Client save(Client Client);

    Iterable<Client> findAll();

    void delete(int idClient);

	Iterable<Client> findByLienDis(int parseInt);

}
