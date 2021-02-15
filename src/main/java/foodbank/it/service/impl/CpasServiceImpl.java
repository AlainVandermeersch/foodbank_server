package foodbank.it.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Cpas save(Cpas Cpas) {
		return CpasRepository.save(Cpas);
		
	}
	

	@Override
	public void delete(int cpasId) {
		CpasRepository.deleteByCpasId(cpasId);
		
	}

	@Override
	public Page<Cpas> findAll(Pageable pageable) {
		return CpasRepository.findAll(pageable);
	}

	@Override
	public Page<Cpas> findByCpasNameContaining(String search, Pageable pageRequest) {
		return CpasRepository.findByCpasNameContaining(search, pageRequest);
	}

	@Override
	public Page<Cpas> findByCpasZipContaining(String search, Pageable pageRequest) {
		return CpasRepository.findByCpasZipContaining(search, pageRequest);
	}

}
