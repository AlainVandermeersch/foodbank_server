package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Membre;
import foodbank.it.persistence.repository.IMembreRepository;
import foodbank.it.service.IMembreService;
import org.springframework.data.domain.Pageable;
@Service
public class MembreServiceImpl implements IMembreService{

	private IMembreRepository MembreRepository;
	
	public MembreServiceImpl(IMembreRepository MembreRepository) {
        this.MembreRepository = MembreRepository;
    }
	@Override
    public Optional<Membre> findByBatId(int batId) {
        return MembreRepository.findByBatId(batId);
    }

    @Override
    public Membre save(Membre Membre) {        
        return MembreRepository.save(Membre);
    }

    @Override
    public Page<Membre> findAll(Pageable pageable) {
         return MembreRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void delete(int batId) {
        MembreRepository.deleteByBatId(batId);
        
    }
   
	@Override
	public Page<Membre> findByBanqueObjectBankShortName( String bankShortName, Pageable pageable) {
		return MembreRepository.findByBanqueObjectBankShortName( bankShortName, pageable);
	
	}
	@Override
	public Page<Membre> findByLienDis(String lienDis, Pageable pageable) {
		return MembreRepository.findByLienDis(lienDis, pageable);
	}
}
	
