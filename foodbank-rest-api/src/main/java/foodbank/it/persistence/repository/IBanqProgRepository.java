package foodbank.it.persistence.repository;

import foodbank.it.persistence.model.BanqProg;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IBanqProgRepository extends CrudRepository<BanqProg, Integer> {
	 Optional<BanqProg> findByLienBanque(int lienBanque);
	    void deleteByLienBanque(int lienBanque);
		
}
