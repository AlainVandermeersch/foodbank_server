package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Banque;
import foodbank.it.persistence.repository.IBanqueRepository;
import foodbank.it.service.IBanqueService;
@Service
public class BanqueServiceImpl implements IBanqueService{
    
    private IBanqueRepository BanqueRepository;

    public BanqueServiceImpl(IBanqueRepository BanqueRepository) {
        this.BanqueRepository = BanqueRepository;
    }

    @Override
    public Optional<Banque> findByBankId(int bankId) {
        return BanqueRepository.findByBankId(bankId);
    }

    @Override
    public Banque save(Banque Banque) {
        // TODO Auto-generated method stub
        return BanqueRepository.save(Banque);
    }

    @Override
    public Iterable<Banque> findAll() {
        // TODO Auto-generated method stub
        return BanqueRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(int bankId) {
        BanqueRepository.deleteByBankId(bankId);
        
    }

	@Override
	public Optional<Banque> findByBankShortName(String bankShortName) {
		// TODO Auto-generated method stub
		return BanqueRepository.findByBankShortName(bankShortName);
	}
    

}
