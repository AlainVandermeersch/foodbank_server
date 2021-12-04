package foodbank.it.service;

import java.util.Optional;

import foodbank.it.persistence.model.BanqProg;

public interface IBanqProgService {

	Optional<BanqProg> findByLienBanque(int lienBanque);
	

    BanqProg save(BanqProg BanqProg);

    Iterable<BanqProg> findAll();

    void delete(int lienBanque);
}
