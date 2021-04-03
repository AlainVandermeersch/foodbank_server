package foodbank.it.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import foodbank.it.persistence.model.Depot;
import foodbank.it.persistence.repository.IDepotRepository;
import org.springframework.stereotype.Service;

import foodbank.it.service.IDepotService;
@Service
public class DepotServiceImpl implements IDepotService{

	private IDepotRepository DepotRepository;
	
	public DepotServiceImpl(IDepotRepository DepotRepository) {
        this.DepotRepository = DepotRepository;
    }
	@Override
    public Optional<Depot> findByIdDepot(String idDepot) {
        return DepotRepository.findByIdDepot(idDepot);
    }

    @Override
    public Depot save(Depot Depot) {        
        return DepotRepository.save(Depot);
    }

    @Override
    public Iterable<Depot> findAll() {
                return DepotRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(String idDepot) {
        DepotRepository.deleteByIdDepot(idDepot);
        
    }
   
	@Override
	public Iterable<Depot> findByNomContaining( String nom) {
		return DepotRepository.findByNomContaining( nom);
	}
	
	


	
}
