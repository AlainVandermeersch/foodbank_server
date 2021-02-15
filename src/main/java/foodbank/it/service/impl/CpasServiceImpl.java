package foodbank.it.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import foodbank.it.persistence.model.Cpas;
import foodbank.it.persistence.repository.ICpasRepository;
import foodbank.it.service.ICpasService;
@Service
public class CpasServiceImpl implements ICpasService{
    
    private ICpasRepository CpasRepository;

    public CpasServiceImpl(ICpasRepository CpasRepository) {
        this.CpasRepository = CpasRepository;
    }

	@Override
	public Optional<Cpas> findByCpasId(int cpasId) {
		return CpasRepository.findByCpasId(cpasId);
	}

	@Override
	public Iterable<Cpas> findByCpasZip(String cpasZip) {
		return CpasRepository.findByCpasZip(cpasZip);
	}

	@Override
	public Cpas save(Cpas Cpas) {
		return CpasRepository.save(Cpas);
		
	}

	@Override
	public Iterable<Cpas> findAll() {
		return CpasRepository.findAll();
	}

	@Override
	public void delete(int cpasId) {
		CpasRepository.deleteByCpasId(cpasId);
		
	}

}
