package foodbank.it.service;

import foodbank.it.persistence.model.BanqProg;

import java.util.Optional;

public interface IBanqProgService {

	Optional<BanqProg> findByLienBanque(int lienBanque);
	

    BanqProg save(BanqProg BanqProg);

    Iterable<BanqProg> findAll();

    void delete(int lienBanque);
}
