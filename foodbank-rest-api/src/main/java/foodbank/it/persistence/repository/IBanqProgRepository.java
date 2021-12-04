package foodbank.it.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodbank.it.persistence.model.BanqProg;

public interface IBanqProgRepository extends CrudRepository<BanqProg, Integer> {
	 Optional<BanqProg> findByLienBanque(int lienBanque);
	    void deleteByLienBanque(int lienBanque);
		
}
