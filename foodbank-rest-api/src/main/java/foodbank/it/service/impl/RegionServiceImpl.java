package foodbank.it.service.impl;

import foodbank.it.persistence.model.Region;
import foodbank.it.persistence.repository.IRegionRepository;
import foodbank.it.service.IRegionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegionServiceImpl implements IRegionService {

	private IRegionRepository RegionRepository;
	
	public RegionServiceImpl(IRegionRepository RegionRepository) {
        this.RegionRepository = RegionRepository;
    }
	@Override
    public Optional<Region> findByRegId(int regId) {
        return RegionRepository.findByRegId(regId);
    }

    @Override
    public Region save(Region Region) {        
        return RegionRepository.save(Region);
    }

    @Override
    public Iterable<Region> findAll() {
                return RegionRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(int regId) {
        RegionRepository.deleteByRegId(regId);
        
    }
   
	
	@Override
	public Iterable<Region> findByBankLink(Short bankLink) {
		return RegionRepository.findByBankLink(bankLink);
	}
	
	@Override
	public Iterable<Region> findByBankShortName(String bankShortName) {
		return RegionRepository.findByBankShortName(bankShortName);
	}
	
	


	
}