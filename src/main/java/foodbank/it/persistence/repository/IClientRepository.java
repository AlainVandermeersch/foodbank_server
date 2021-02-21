package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Client;

public interface IClientRepository extends PagingAndSortingRepository<Client, Integer>{
    Optional<Client> findByIdClient(int idClient);
    void deleteByIdClient(int idClient);
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
}
