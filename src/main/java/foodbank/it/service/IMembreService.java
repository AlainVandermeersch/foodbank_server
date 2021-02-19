package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.Membre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMembreService {
	
	Optional<Membre> findByBatId(int batId);
	
	Page<Membre> findByBanqueObjectBankShortName( String bankShortName, Pageable pageable);
	Page<Membre> findByLienDis( String lienDis, Pageable pageable);

    Membre save(Membre membre);

    Page<Membre> findAll(Pageable pageable);

    void delete(int batId);

	Page<Membre> findByNomContaining(String search, Pageable pageRequest);

	Page<Membre> findByLienDisAndNomContaining(String lienDis, String search, Pageable pageRequest);

	Page<Membre> findByBanqueObjectBankShortNameAndNomContaining(String bankShortName, String search,
			Pageable pageRequest);
	
	Page<Membre> findByPrenomContaining(String search, Pageable pageRequest);

	Page<Membre> findByLienDisAndPrenomContaining(String lienDis, String search, Pageable pageRequest);

	Page<Membre> findByBanqueObjectBankShortNameAndPrenomContaining(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByAddressContaining(String search, Pageable pageRequest);

	Page<Membre> findByLienDisAndAddressContaining(String lienDis, String search, Pageable pageRequest);

	Page<Membre> findByBanqueObjectBankShortNameAndAddressContaining(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByZipStartsWith(String search, Pageable pageRequest);

	Page<Membre> findByLienDisAndZipStartsWith(String lienDis, String search, Pageable pageRequest);

	Page<Membre> findByBanqueObjectBankShortNameAndZipStartsWith(String bankShortName, String search,
			Pageable pageRequest);
	Page<Membre> findByCityContaining(String search, Pageable pageRequest);

	Page<Membre> findByLienDisAndCityContaining(String lienDis, String search, Pageable pageRequest);

	Page<Membre> findByBanqueObjectBankShortNameAndCityContaining(String bankShortName, String search,
			Pageable pageRequest);
}
