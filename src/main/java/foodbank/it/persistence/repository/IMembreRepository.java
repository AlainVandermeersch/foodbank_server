package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Membre;

public interface IMembreRepository  extends PagingAndSortingRepository<Membre, Integer>{
	Optional<Membre> findByBatId(int batId);
    void deleteByBatId(int batId);
    Page<Membre> findByBanqueObjectBankShortName( String bankShortName,Pageable pageable);
    Page<Membre> findByLienDis( Integer lienDis,Pageable pageable);
	Page<Membre> findByNomContaining(String search, Pageable pageRequest);
	Page<Membre> findByLienDisAndNomContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Membre> findByBanqueObjectBankShortNameAndNomContaining(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByPrenomContaining(String search, Pageable pageRequest);
	Page<Membre> findByLienDisAndPrenomContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Membre> findByBanqueObjectBankShortNameAndPrenomContaining(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByAddressContaining(String search, Pageable pageRequest);
	Page<Membre> findByLienDisAndAddressContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Membre> findByBanqueObjectBankShortNameAndAddressContaining(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByZipStartsWith(String search, Pageable pageRequest);
	Page<Membre> findByLienDisAndZipStartsWith(Integer lienDis, String search, Pageable pageRequest);
	Page<Membre> findByBanqueObjectBankShortNameAndZipStartsWith(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByCityContaining(String search, Pageable pageRequest);
	Page<Membre> findByLienDisAndCityContaining(Integer lienDis, String search, Pageable pageRequest);
	Page<Membre> findByBanqueObjectBankShortNameAndCityContaining(String bankShortName, String search,
			Pageable pageRequest);
}
