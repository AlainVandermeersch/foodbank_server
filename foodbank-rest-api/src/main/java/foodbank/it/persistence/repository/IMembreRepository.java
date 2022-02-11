package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import foodbank.it.persistence.model.Membre;

public interface IMembreRepository  extends PagingAndSortingRepository<Membre, Integer>{
	Optional<Membre> findByBatId(int batId);
    void deleteByBatId(int batId);
    Iterable<Membre> findByLienBanque(Short lienBanque);
    Iterable<Membre> findByActif(Short actif);
    Iterable<Membre> findByLienBanqueAndActif(Short lienBanque, Short actif);
   
}
