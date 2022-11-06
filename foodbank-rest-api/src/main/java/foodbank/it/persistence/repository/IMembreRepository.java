package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.Membre;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IMembreRepository  extends PagingAndSortingRepository<Membre, Integer>{
	Optional<Membre> findByBatId(int batId);
    void deleteByBatId(int batId);
    Iterable<Membre> findByLienBanque(Short lienBanque);
	Iterable<Membre> findByLienDis(Integer lienDisInteger);
   
   
}
