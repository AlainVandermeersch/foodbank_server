package foodbank.it.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import foodbank.it.persistence.model.Client;

public interface IClientService {
	Optional<Client> findByIdClient(int idClient);
	
	Page<Client> findAll(Pageable pageable);
	Page<Client> findByBanqueObjectBankShortName( String bankShortName,Pageable pageable);
    Page<Client> findByLienDis(Integer lienDis,Pageable pageable);
    
    Page<Client> findByNomContaining(String search, Pageable pageRequest);
    Page<Client> findByLienDisAndNomContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Client> findByBanqueObjectBankShortNameAndNomContaining(String bankShortName, String search,
			Pageable pageRequest);
	
	Page<Client> findByPrenomContaining(String search, Pageable pageRequest);
	Page<Client> findByLienDisAndPrenomContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Client> findByBanqueObjectBankShortNameAndPrenomContaining(String bankShortName, String search,
			Pageable pageRequest);
	
	Page<Client> findByAdresseContaining(String search, Pageable pageRequest);
	Page<Client> findByLienDisAndAdresseContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Client> findByBanqueObjectBankShortNameAndAdresseContaining(String bankShortName, String search,
			Pageable pageRequest);
	
	Page<Client> findByCpStartsWith(String search, Pageable pageRequest);
	Page<Client> findByLienDisAndCpStartsWith(Integer lienDis, String search, Pageable pageRequest);
	Page<Client> findByBanqueObjectBankShortNameAndCpStartsWith(String bankShortName, String search,
			Pageable pageRequest);
	
	Page<Client> findByLocaliteContaining(String search, Pageable pageRequest);
	Page<Client> findByLienDisAndLocaliteContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Client> findByBanqueObjectBankShortNameAndLocaliteContaining(String bankShortName, String search,
			Pageable pageRequest);
    
    Client save(Client Client);

    void delete(int idClient);
	

}
