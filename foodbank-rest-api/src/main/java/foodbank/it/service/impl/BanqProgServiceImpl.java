package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import foodbank.it.persistence.model.BanqProg;
import foodbank.it.persistence.repository.IBanqProgRepository;
import foodbank.it.service.IBanqProgService;
import org.springframework.stereotype.Service;

@Service
public class BanqProgServiceImpl implements IBanqProgService {

	private IBanqProgRepository BanqProgRepository;
	
	public BanqProgServiceImpl(IBanqProgRepository BanqProgRepository) {
        this.BanqProgRepository = BanqProgRepository;
    }

	@Override
	public Optional<BanqProg> findByLienBanque(int lienBanque) {
		
		return BanqProgRepository.findByLienBanque(lienBanque);
	}

	@Override
	public BanqProg save(BanqProg BanqProg) {
		
		return BanqProgRepository.save(BanqProg);
	}

	@Override
	public Iterable<BanqProg> findAll() {
		return BanqProgRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(int lienBanque) {
		BanqProgRepository.deleteByLienBanque(lienBanque);
		
	}
	
}
