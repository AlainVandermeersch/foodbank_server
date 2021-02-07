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
    Page<Membre> findByLienDis( String lienDis,Pageable pageable);
}
